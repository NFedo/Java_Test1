package javaCourse.addressbook.appmanager;

import javaCourse.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Nadejda.Fedorova on 25.04.2016.
 */
public class ContactHelper {
  private FirefoxDriver wd;

  public ContactHelper(FirefoxDriver wd) {
    this.wd = wd;
  }

  public void returnToHomePage() {
    wd.findElement(By.linkText("home")).click();
  }

  public void submitContactCreation() {
    wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
  }

  public void fillContactForm(ContactData contactData) {
    wd.findElement(By.name("firstname")).click();
    wd.findElement(By.name("firstname")).sendKeys(contactData.getFirstName());
    wd.findElement(By.name("firstname")).clear();
    wd.findElement(By.name("lastname")).click();
    wd.findElement(By.name("lastname")).clear();
    wd.findElement(By.name("lastname")).sendKeys(contactData.getLastName());
    wd.findElement(By.name("nickname")).clear();
    wd.findElement(By.name("nickname")).click();
    wd.findElement(By.name("company")).click();
    wd.findElement(By.name("nickname")).sendKeys(contactData.getNickName());
    wd.findElement(By.name("company")).clear();
    wd.findElement(By.name("company")).sendKeys(contactData.getCompany());
    wd.findElement(By.name("address")).click();
    wd.findElement(By.name("address")).clear();
    wd.findElement(By.name("address")).sendKeys(contactData.getAddress());
    wd.findElement(By.name("home")).click();
    wd.findElement(By.name("home")).clear();
    wd.findElement(By.name("home")).sendKeys(contactData.getPhoneHome());
    wd.findElement(By.name("mobile")).click();
    wd.findElement(By.name("mobile")).clear();
    wd.findElement(By.name("mobile")).sendKeys(contactData.getPhoneMobile());
    wd.findElement(By.name("email")).click();
    wd.findElement(By.name("email")).clear();
    wd.findElement(By.name("email")).sendKeys(contactData.getEmail());
    // день
    if (!wd.findElement(By.xpath("//div[@id='content']/form/select[1]//option[" + contactData.getiDay() + "]")).isSelected()) {
      wd.findElement(By.xpath("//div[@id='content']/form/select[1]//option[" + contactData.getiDay() + "]")).click();
    }
    // месяц
    if (!wd.findElement(By.xpath("//div[@id='content']/form/select[2]//option[" + contactData.getiMonth() + "]")).isSelected()) {
      wd.findElement(By.xpath("//div[@id='content']/form/select[2]//option[" + contactData.getiMonth() + "]")).click();
    }
    // год
    wd.findElement(By.name("byear")).click();
    wd.findElement(By.name("byear")).clear();
    wd.findElement(By.name("byear")).sendKeys(contactData.getYear());
    // группа контактов
    if (!wd.findElement(By.xpath("//div[@id='content']/form/select[5]//option[" + contactData.getiGroup() + "]")).isSelected()) {
      wd.findElement(By.xpath("//div[@id='content']/form/select[5]//option[" + contactData.getiGroup() + "]")).click();
    }
  }
}
