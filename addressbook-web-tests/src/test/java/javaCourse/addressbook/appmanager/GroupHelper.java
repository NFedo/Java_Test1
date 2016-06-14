package javaCourse.addressbook.appmanager;

import javaCourse.addressbook.model.GroupData;
import javaCourse.addressbook.model.Groups;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Created by Nadejda.Fedorova on 25.04.2016.
 */
public class GroupHelper extends HelperBase {

  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  public void initGroupCreation() {
    click(By.name("new"));
  }

  public void submitGroupCreation() {
    click(By.name("submit"));
  }

  public void selectGroupById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }
  public void selectAnyGroup() {
    wd.findElement(By.name("selected[]")).click();
  }

  public void selectAnyGroupByInd(int ind) {
    if (!wd.findElement(By.xpath("//div[@id='content']/form/span[" + ind + "]/input")).isSelected()) {
      wd.findElement(By.xpath("//div[@id='content']/form/span[" + ind + "]/input")).click();
    }
  }


  public void initGroupModification() {
    click(By.name("edit"));
  }

  public void submitGroupModification() {
    click(By.name("update"));
  }

  public void deleteSelectedGroups() {
    click(By.xpath("//div[@id='content']/form/input[5]"));
  }

  public void returnToGroupPage()  {
    click(By.linkText("groups"));
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getName());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }

  public void create(GroupData group) {
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    groupCache = null;
    returnToGroupPage();
  }

  public void modify(GroupData group) {
    selectGroupById(group.getId());
    initGroupModification();
    fillGroupForm(group);
    submitGroupModification();
    groupCache = null;
    returnToGroupPage();
  }

  public void delete(GroupData group) {
    selectGroupById(group.getId());
    deleteSelectedGroups();
    groupCache = null;
    returnToGroupPage();
  }

  private Groups groupCache = null;

  public Groups all() {
    if (groupCache != null) {
      return new Groups(groupCache);
    }
    groupCache = new Groups();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements) {
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String name = element.getText();
      groupCache.add(new GroupData().withId(id).withName(name));
    }
    return new Groups(groupCache);
  }

  public boolean isThereAGroup() { return isElementPresent(By.name("selected[]")); }

  public int count() { return wd.findElements(By.name("selected[]")).size(); }

  public void filterContactsByGroup(GroupData group) {
    new Select(wd.findElement(By.name("group"))).selectByVisibleText(group.getName());
  }

  public void filterContactsNoGroup() {
    new Select(wd.findElement(By.name("group"))).selectByVisibleText("[none]");
  }
}
