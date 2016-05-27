package javaCourse.addressbook.appmanager;

import javaCourse.addressbook.model.ContactData;
import javaCourse.addressbook.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

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

    // день
   /* if (!wd.findElement(By.xpath("//div[@id='content']/form/select[1]//option[" + contactData.getiDay() + "]")).isSelected()) {
      click(By.xpath("//div[@id='content']/form/select[1]//option[" + contactData.getiDay() + "]"));
    }
    // месяц
    if (!wd.findElement(By.xpath("//div[@id='content']/form/select[2]//option[" + contactData.getiMonth() + "]")).isSelected()) {
      click(By.xpath("//div[@id='content']/form/select[2]//option[" + contactData.getiMonth() + "]"));
    }
    // год
    type(By.name("byear"),contactData.getYear()); */
    // группа контактов
   // if (!wd.findElement(By.xpath("//div[@id='content']/form/select[5]//option[" + contactData.getiGroup() + "]")).isSelected()) {
   //   click(By.xpath("//div[@id='content']/form/select[5]//option[" + contactData.getiGroup() + "]"));
   // }

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getiGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
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
    String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstName(firstName).withLastName(lastName)
            .withAddress(address)
            .withPhoneHome(home).withPhoneMobile(mobile).withPhoneWork(work)
            .withEmail(email).withEmail2(email2).withEmail3(email3);
  }
}
