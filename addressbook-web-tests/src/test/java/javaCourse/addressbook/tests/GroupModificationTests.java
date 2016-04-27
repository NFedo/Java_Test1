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
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("test11_new1", "test21_new1", "test31_new1"));
    app.getGroupHelper().submitGroupModification();
    app.getNavigationHelper().gotoGroupPage();
  }

}
