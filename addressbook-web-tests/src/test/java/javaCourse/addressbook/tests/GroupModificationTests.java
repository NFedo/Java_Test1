package javaCourse.addressbook.tests;

import javaCourse.addressbook.model.GroupData;
import javaCourse.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by Nadejda.Fedorova on 25.04.2016.
 */
public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("test61").withHeader("test62").withFooter("test63"));
    }
  }

  @Test
  public void testGroupModification() {
    Groups before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData()
            .withId(modifiedGroup.getId()).withName("test51").withHeader("test31_new2").withFooter("test31_new2");
    app.group().modify(group);
    assertEquals(app.group().count(), before.size());
    Groups after = app.group().all();
    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
  }
}
