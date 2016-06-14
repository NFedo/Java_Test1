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

public class ContactFromGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    // удаляем все группы до начала теста вместе со всеми их связями
    // чтобы не проверять потом наличие связей
    // и также чтобы гарантировать, что среди групп нет групп с одинаковыми именами,
    // т.к. выбирать потом контакты в форме можно только по имени
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
    app.goTo().groupPage();
    app.group().create(new GroupData().withName("test 11")
            .withHeader("header 11").withFooter("footer 11"));

    // находим саму новую группу, она всего одна
    GroupData newGroup = app.db().groups().iterator().next();

    // создаем новый контакт, сразу в новой группе, чтобы точно был контакт,
    // который можно будет найти по имени группы и удалить
    app.goTo().homePage();
    app.contact().create(new ContactData().withFirstName("FirstName 11").withLastName("LastName 11")
            .inGroup(newGroup));
  }

  @Test
  public void testContactFromGroup() {
    Groups groups = app.db().groups();
    // находим группу, она всего одна, так что это та же группа newGroup из предусловия
    GroupData testGroup = groups.iterator().next();
    // выбираем все контакты включенные в новую группу в БД
    Contacts inGroupBefore = app.db().group(testGroup.getId()).iterator().next().getContacts();
    // Проверяем, что в группе есть контакты (т.е. предусловия выполнились)
    Assert.assertFalse(inGroupBefore.isEmpty());

    app.goTo().homePage();
    // выбираем все контакты включенные в новую группу в интерфейсе
    app.group().filterContactsByGroup(testGroup);
    // должен быть один контакт - выбираем его id по БД
    ContactData modifiedContact = inGroupBefore.iterator().next();
    // выделяем найденный контакт на форме по id
    app.contact().selectContactById(modifiedContact.getId());
    // удаляем контакт из группы через UI
    app.contact().removeFromGroup();

    // находим измененный контакт по его id по БД
    ContactData modifiedContactAfter = app.db().contact(modifiedContact.getId()).iterator().next();
    // Измененный контакт не входит больше ни в одну группу
    Assert.assertTrue(modifiedContactAfter.getGroups().isEmpty());
    // находим группу из предусловия по БД
    GroupData testGroupAfter = app.db().group(testGroup.getId()).iterator().next();
    // Contacts newGroupMembersAfter = inGroupAfter.getContacts();
    Assert.assertTrue(testGroupAfter.getContacts().isEmpty());

    verifyContactListInUI();
  }
}