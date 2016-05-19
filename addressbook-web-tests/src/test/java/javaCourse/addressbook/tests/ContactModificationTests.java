package javaCourse.addressbook.tests;

import javaCourse.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Nadejda.Fedorova on 26.04.2016.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Nadejda3", "Fedorova3", "NF3", "Peter-Service", "Шпалерная ул., дом 36, оф. 503",
               "921-791-1113", "921-791-1114", "nadejda2.fedorova2@peter-service.com", /*8, 6, "1983"*,*/ "test1"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().initContactModification(before.size() - 1);
    ContactData contact = new ContactData("Nadejda9", "Fedorova5", "NF5", "Peter-Service", "Шпалерная ул., дом 36, оф. 503",
            "921-791-1113", "921-791-1114", "nadejda2.fedorova2@peter-service.com", /*8, 6, "1983",*/ null);
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());
    // удаляем последний контакт и на его место добавляем новый
    before.remove(before.size() - 1);
    before.add(contact);

    // Сортировка только по именам
    Comparator<? super ContactData> byName = (c1, c2) -> c1.getFirstName().compareTo(c2.getFirstName());
    before.sort(byName);
    after.sort(byName);
    Assert.assertEquals(before, after);
  }

  @Test
  public void testContactModification1() {
    app.getNavigationHelper().gotoHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Nadejda3", "Fedorova3", "NF3", "Peter-Service", "Шпалерная ул., дом 36, оф. 503",
              "921-791-1113", "921-791-1114", "nadejda2.fedorova2@peter-service.com",/* 8, 6, "1983",*/ "test41"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().initContactModification(before.size() - 1);
    ContactData contact = new ContactData("Nadejda9", "Fedorova5", "NF5", "Peter-Service", "Шпалерная ул., дом 36, оф. 503",
            "921-791-1113", "921-791-1114", "nadejda2.fedorova2@peter-service.com", /*8, 6, "1983",*/ null);
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());
    // удаляем последний контакт и на его место добавляем новый
    before.remove(before.size() - 1);
    before.add(contact);

    // Сортировка по фамилиям и именам
    Comparator<? super ContactData> byFullName = (c1, c2) -> c1.getFullName().compareTo(c2.getFullName());
    before.sort(byFullName);
    after.sort(byFullName);
    Assert.assertEquals(before, after);
  }
}