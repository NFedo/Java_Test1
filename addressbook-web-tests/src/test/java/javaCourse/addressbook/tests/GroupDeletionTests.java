package javaCourse.addressbook.tests;

import javaCourse.addressbook.model.GroupData;
import javaCourse.addressbook.model.Groups;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1").withHeader("test62").withFooter("test63"));
    }
  }

  @Test
  public void testGroupDeletion() {
    Groups before = app.db().groups();
    GroupData deletedGroup = before.iterator().next();
    app.goTo().groupPage();
    app.group().delete(deletedGroup);
    assertEquals(app.group().count(), before.size() - 1); // до загрузки групп
    Groups after = app.db().groups();
    assertThat(after, equalTo(before.without(deletedGroup)));
  }

}
