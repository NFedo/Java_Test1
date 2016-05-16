package javaCourse.addressbook.tests;

import javaCourse.addressbook.model.GroupData;
import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().createGroup(new GroupData("test31", "test32", "test33"));
    // app.getGroupHelper().initGroupCreation();
    // app.getGroupHelper().fillGroupForm(new GroupData("test61", "test62", "test63"));
    // app.getGroupHelper().fillGroupForm(new GroupData("test61", "test21", null)); исключение
    // app.getGroupHelper().submitGroupCreation();
    // app.getNavigationHelper().gotoGroupPage();
  }

}
