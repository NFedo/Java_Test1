package javaCourse.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("test11", "test21", "test31"));
    submitGroupCreation();
    gotoGroupPage();
  }

}
