package javaCourse.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Nadejda.Fedorova on 25.04.2016.
 */
public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void gotoHomePage() {
    if (isElementPresent(By.id("maintable"))) {
      return;
    }
    click(By.linkText("home"));
  }

  public void gotoGroupPage() {
    if (isElementPresent(By.tagName("h1"))
            &&  wd.findElement(By.tagName("h1")).getText().equals("Groups")
            && isElementPresent(By.name("new"))) {
      return;
    }
    click(By.linkText("groups"));
  }

  public void gotoAddNewContact() {
    if (isElementPresent(By.tagName("label"))
         &&  wd.findElement(By.tagName("label")).getText().equals("Secondary")) {
        return;
      }
    click(By.linkText("add new"));
  }
}
