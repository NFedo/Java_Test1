package javaCourse.addressbook.appmanager;

import javaCourse.addressbook.model.ContactData;
import javaCourse.addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
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
    type(By.name("email"),contactData.getEmail());

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

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
    //click(By.name("selected[]"));
  }

  public void initContactModification() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
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

  public void createContact(ContactData contact) {
    initContactCreation();
    fillContactForm(contact, true);
    submitContactCreation();
    returnToHomePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.xpath("//table[@id='maintable']//tr[@name='entry']"));
    for (WebElement element : elements) {
      String firstName = element.findElement(By.xpath("td[3]")).getText();
      String lastName = element.findElement(By.xpath("td[2]")).getText();
      ContactData contact = new ContactData(firstName, lastName, null, null, null, null, null, null, null );
      contacts.add(contact);
    }
    return contacts;
  }
}
/*
  public List<GroupData> getGroupList() {
    List<GroupData> groups = new ArrayList<GroupData>();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements) {
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      GroupData group = new GroupData(id, name, null, null);
      groups.add(group);
    }
    return groups;
  }
  */