package javaCourse.addressbook.tests;

import javaCourse.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.goTo().groupPage();
    List<GroupData> before = app.group().list();
    GroupData group = new GroupData().withName("test41").withHeader("test42").withFooter("test43");
    app.group().create(group);
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), before.size() + 1);
    // добавляем новую группу
    before.add(group);
    // сравнение по идентификатору
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
    before.sort(byId);
    after.sort(byId);
    // можно сравнивать сами списки после сортировки, а не множества
    Assert.assertEquals(before, after);
  }
}
