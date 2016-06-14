package javaCourse.addressbook.tests;

/**
 * Created by Nadejda.Fedorova on 12.06.2016.
 */

import javaCourse.addressbook.model.ContactData;
import javaCourse.addressbook.model.Contacts;
import javaCourse.addressbook.model.GroupData;
import javaCourse.addressbook.model.Groups;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactToGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    // удаляем все группы до начала теста вместе со всеми их связями
    // чтобы не проверять потом наличие связей
    // удаляем все, чтобы гарантировать, что среди групп нет групп с тем же именем,
    // по которому будем выбирать потом контакты
    Groups before = app.db().groups();
    int ind = before.size();
    if (ind > 0) {
      app.goTo().groupPage();
      while(ind > 0) { // отмечаем все группы
        app.group().selectAnyGroupByInd(ind);
        ind -- ;
      } // удаляем все группы
      app.group().deleteSelectedGroups();
    }

    // создаем новую группу, чтобы было куда добавить контакт
    // который можно идентифицировать по имени
    app.goTo().groupPage();
    app.group().create(new GroupData().withName("test 10")
            .withHeader("header 10").withFooter("footer 10"));

    // создаем новый контакт, чтобы точно был хотя бы один контакт, который ни в одной группе
    app.goTo().homePage();
    app.contact().create(new ContactData().withFirstName("FirstName 10").withLastName("LastName 10"));
  }

  @Test
  public void testContactToGroup() {
    Groups groups = app.db().groups();
    // находим id новой группы (ее номер максимальный)
    int newGroupId = (groups.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    // находим саму новую группу
    GroupData newGroup = app.db().group(newGroupId).iterator().next();

    app.goTo().homePage();
    // находим все контакты не включенные в группы
    app.group().filterContactsNoGroup();
    Contacts noGroupBefore = app.contact().all();

    // выбираем любой контакт
    ContactData modifiedContact = noGroupBefore.iterator().next();
    // добавляем контакт в новую группу
    app.contact().addToGroup(modifiedContact, newGroup);
    // находим все контакты включенные в новую группу в интерфейсе
    app.group().filterContactsByGroup(newGroup);
    // находим все контакты включенные в новую группу в базе данных
    GroupData newGroupAfter = app.db().group(newGroupId).iterator().next();
    Contacts newGroupMembersAfter = newGroupAfter.getContacts();

    // В новой группе должен быть один контакт
    Assert.assertTrue(newGroupMembersAfter.size() == 1);
    // В новую группу входит именно измененный контакт
    Assert.assertTrue(newGroupMembersAfter.contains(modifiedContact));

    // находим все контакты не включенные в группы через UI
    // проверяю условие не по БД, ибо не умею еще находить контакты не включенные
    // ни в одну группу по БД при помощи Hibernate ORM
    app.group().filterContactsNoGroup();
    Contacts noGroupAfter = app.contact().all();

    // среди контактов без групп стало на один контакт меньше
    assertThat(noGroupBefore.size()-1, equalTo(noGroupAfter.size()));
    // пропал имененно измененный контакт
    assertThat(noGroupBefore.without(modifiedContact), equalTo(noGroupAfter));

    verifyContactListInUI();
  }
}