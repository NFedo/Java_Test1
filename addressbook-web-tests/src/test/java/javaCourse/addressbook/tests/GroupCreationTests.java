package javaCourse.addressbook.tests;

import javaCourse.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    // int before = app.getGroupHelper().getGroupCount();
    GroupData group = new GroupData("test41", "test32", "test33");
    app.getGroupHelper().createGroup(group);
    List<GroupData> after = app.getGroupHelper().getGroupList();
    //  int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after.size(), before.size() + 1);
    // app.getGroupHelper().initGroupCreation();
    // app.getGroupHelper().fillGroupForm(new GroupData("test61", "test62", "test63"));
    // app.getGroupHelper().fillGroupForm(new GroupData("test61", "test21", null)); исключение
    // app.getGroupHelper().submitGroupCreation();
    // app.getNavigationHelper().gotoGroupPage();

    /*int max = 0;
    for (GroupData g : after) {
      if (g.getId() > max) {
        max = g.getId();
      }
    }*/
    //Comparator<? super GroupData> byId = (Comparator<GroupData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
    //int max1 = after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();
    //group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
    before.sort(byId);
    after.sort(byId);

   // Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    // можно сравнивать сами списки после сортировки, а не множества
    Assert.assertEquals(before, after);
  }

}
