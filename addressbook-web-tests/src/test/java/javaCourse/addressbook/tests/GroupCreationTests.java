package javaCourse.addressbook.tests;

import org.testng.annotations.Test;
import javaCourse.addressbook.model.GroupData;
import javaCourse.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("test41").withHeader("test42").withFooter("test43");
    app.group().create(group);
    Groups after = app.group().all();
    assertThat(app.group().count(), equalTo(before.size() + 1)); // быстрая проверка
    // добавляем новую группу
    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test
  public void testBadGroupCreation() {
    app.goTo().groupPage();
    Groups before = app.group().all(); // запрещенный символ
    GroupData group = new GroupData().withName("test4'").withHeader("test42").withFooter("test43");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size())); // быстрая проверка
    Groups after = app.group().all();
    // добавляем новую группу
    assertThat(after, equalTo(before));
  }
}
