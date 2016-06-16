package javaCourse.addressbook.appmanager;

import javaCourse.addressbook.model.ContactData;
import javaCourse.addressbook.model.Contacts;
import javaCourse.addressbook.model.GroupData;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.io.File;
import java.util.List;


/**
 * Created by Nadejda.Fedorova on 25.04.2016.
 */
public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToHomePage() {
    click(By.linkText("home"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"),contactData.getFirstName());
    type(By.name("lastname"),contactData.getLastName());
    type(By.name("nickname"),contactData.getNickName());
    type(By.name("company"),contactData.getCompany());
    type(By.name("address"),contactData.getAddress());
    type(By.name("home"),contactData.getPhoneHome());
    type(By.name("mobile"),contactData.getPhoneMobile());
    type(By.name("work"),contactData.getPhoneWork());
    type(By.name("email"),contactData.getEmail());
    type(By.name("email2"),contactData.getEmail2());
    type(By.name("email3"),contactData.getEmail3());
    // фото обязательно абсолютный путь !
    if (contactData.getPhotoPath() != null) {
      File photo = new File(contactData.getPhotoPath());
      attach(By.name("photo"), photo);
    }
    else if (contactData.getPhoto() != null) {
      attach(By.name("photo"), contactData.getPhoto());
    }

    // день
    if (!wd.findElement(By.xpath("//div[@id='content']/form/select[1]//option[" + (contactData.getiDay() + 2)+ "]")).isSelected()) {
      click(By.xpath("//div[@id='content']/form/select[1]//option[" + (contactData.getiDay() + 2) + "]"));
    }
    // месяц
    if (!wd.findElement(By.xpath("//div[@id='content']/form/select[2]//option[" + (contactData.getiMonth() + 1) + "]")).isSelected()) {
      click(By.xpath("//div[@id='content']/form/select[2]//option[" + (contactData.getiMonth() + 1) + "]"));
    }
    // год
    type(By.name("byear"),contactData.getYear());
    // группа контактов
    /*if (!wd.findElement(By.xpath("//div[@id='content']/form/select[5]//option[" + contactData.getcGroup() + "]")).isSelected()) {
      click(By.xpath("//div[@id='content']/form/select[5]//option[" + contactData.getcGroup() + "]"));
    }*/

    if (creation) {
      if (contactData.getGroups().size() > 0) {
        Assert.assertTrue(contactData.getGroups().size() == 1); // не больше одной группы при создании
        new Select(wd.findElement(By.name("new_group")))
                .selectByVisibleText(contactData.getGroups().iterator().next().getName());
      }
    } else {
       // проверяем, что это не форма добавления нового контакта, а форма редактирования старого
        Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void selectAnyContact() {
    wd.findElement(By.name("selected[]")).click();
  }

  public void initContactModificationById(int id) {
    wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
    /*  другие варианты
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();

    wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
    wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click();
    */
  }

  public void openProfileFormById(int id) { // с главной страницы
    wd.findElement(By.cssSelector("a[href='view.php?id=" + id + "']")).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void submitContactCreation() {
    click(By.name("submit"));
  }

  // кнопка Delete
  public void initContactDeletion() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  // закрывает окно для подтверждения удаления
  public void submitContactDeletion() {
    wd.switchTo().alert().accept();
  }

  public void create(ContactData contact) {
    initContactCreation();
    fillContactForm(contact, true);
    submitContactCreation();
    contactCache = null;
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    // selectContactById(contact.getId());
    initContactModificationById(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    contactCache = null;
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    initContactDeletion();
    submitContactDeletion();
    contactCache = null;
    returnToHomePage();
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath("//table[@id='maintable']//tr[@name='entry']"));
    for (WebElement element : elements) {
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String firstName = element.findElement(By.xpath("td[3]")).getText();
      String lastName = element.findElement(By.xpath("td[2]")).getText();
      String address = element.findElement(By.xpath("td[4]")).getText();
      String allEmails = element.findElement(By.xpath("td[5]")).getText();
      String allPhones = element.findElement(By.xpath("td[6]")).getText();
      // String[] phones = element.findElement(By.xpath("td[6]")).getText().split("\n");
      String[] emails = element.findElement(By.xpath("td[5]")).getText().split("\n");
      ContactData contact = new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
              .withAddress(address)
              .withAllEmails(allEmails)
              //.withEmail(emails[0]).withEmail2(emails[1]).withEmail3(emails[2])
              .withAllPhones(allPhones);
      //      .withPhoneHome(phones[0]).withPhoneMobile(phones[1]).withPhoneWork(phones[2]);
      contactCache.add(contact);
    }
    return new Contacts(contactCache);
  }
  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() { return wd.findElements(By.name("selected[]")).size(); }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
    String middleName = wd.findElement(By.name("middlename")).getAttribute("value");
    String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
    String nickName = wd.findElement(By.name("nickname")).getAttribute("value");
    String company = wd.findElement(By.name("company")).getAttribute("value");
    String title = wd.findElement(By.name("title")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    String homePage = wd.findElement(By.name("homepage")).getAttribute("value");
    int bDay = Integer.parseInt(wd.findElement(By.xpath("//select[@name='bday']/option[@selected='selected']"))
               .getAttribute("value"));
    String bMonthStr = wd.findElement(By.xpath("//select[@name='bmonth']/option[1]")).getAttribute("value");
    String bYear = wd.findElement(By.name("byear")).getAttribute("value");

    // wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
    /*  другие варианты
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();

    wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
    wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click();
    */

    wd.navigate().back();
    return new ContactData().withId(contact.getId())
            .withFirstName(firstName).withMiddleName(middleName).withLastName(lastName).withNickName(nickName)
            .withCompany(company).withTitle(title).withAddress(address)
            .withPhoneHome(home).withPhoneMobile(mobile).withPhoneWork(work)
            .withEmail(email).withEmail2(email2).withEmail3(email3)
            .withHomePage(homePage)
            .withiDay(bDay)
            .withMonthStr(bMonthStr)
            .withYear(bYear)
            ;
  }

  public String infoFromProfileForm(ContactData contact) {
    openProfileFormById(contact.getId());

    String profile = wd.findElement(By.cssSelector("div[id='content']")).getText();

    System.out.println(profile);

    wd.navigate().back();
    return profile;
  }

  public void addToAnyGroup(ContactData contact) {
    selectContactById(contact.getId());
    if (!wd.findElement(By.xpath("//div[@class='right']//select[1]//option[1]")).isSelected()) {
      wd.findElement(By.xpath("//div[@class='right']//select[1]//option[1]")).click();
    }
    wd.findElement(By.name("add")).click();
    contactCache = null;
    returnToHomePage();
  }

  public void addToGroup(ContactData contact, GroupData group) {
    selectContactById(contact.getId());
    new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(group.getName());
    wd.findElement(By.name("add")).click();
    contactCache = null;
    returnToHomePage();
  }

  public void removeFromGroup() {
    wd.findElement(By.name("remove")).click();
    contactCache = null;
    returnToHomePage();
  }
}
