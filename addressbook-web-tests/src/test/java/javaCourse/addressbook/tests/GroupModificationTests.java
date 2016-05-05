package javaCourse.addressbook.tests;

import javaCourse.addressbook.model.GroupData;
import org.testng.annotations.Test;

/**
 * Created by Nadejda.Fedorova on 25.04.2016.
 */
public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification() {
    app.getNavigationHelper().gotoGroupPage();
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test61", "test62", "test63"));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("test1", "test31_new2", "test31_new2"));
    // app.getGroupHelper().fillGroupForm(new GroupData("test1", null, null)); // исключение
    app.getGroupHelper().submitGroupModification();
    app.getNavigationHelper().gotoGroupPage();
  }

}
