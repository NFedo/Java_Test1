package javaCourse.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import javaCourse.addressbook.model.GroupData;
import javaCourse.addressbook.model.Groups;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroupsFromXml() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        // String[] split = line.split(";");
        // list.add(new Object[]{new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(GroupData.class);
      List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
      return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validGroupsFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<GroupData> groups  = gson.fromJson(json, new TypeToken<List<GroupData>>(){}.getType()); // List<GroupData>.class
      return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validGroupsFromXml") // validGroupsFromXml
  public void testGroupCreation_1(GroupData group) {
 //   GroupData group = new GroupData().withName(name).withHeader(header).withFooter(footer);
    app.goTo().groupPage();
    Groups before = app.group().all();
    app.group().create(group);
    Groups after = app.group().all();
    assertThat(app.group().count(), equalTo(before.size() + 1)); // быстрая проверка
    // добавляем новую группу
    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test(dataProvider = "validGroupsFromJson") // validGroupsFromXml
  public void testGroupCreation_2(GroupData group) {
    //   GroupData group = new GroupData().withName(name).withHeader(header).withFooter(footer);
    app.goTo().groupPage();
    Groups before = app.group().all();
    app.group().create(group);
    Groups after = app.group().all();
    assertThat(app.group().count(), equalTo(before.size() + 1)); // быстрая проверка
    // добавляем новую группу
    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test (enabled = false)
  public void testBadGroupCreation() {
    app.goTo().groupPage();
    Groups before = app.group().all(); // запрещенный символ
    GroupData group = new GroupData().withName("test4").withHeader("test42").withFooter("test43");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size())); // быстрая проверка
    Groups after = app.group().all();
    // добавляем новую группу
    assertThat(after, equalTo(before));
  }
}
