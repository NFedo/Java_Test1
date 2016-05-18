package javaCourse.addressbook.tests;

import javaCourse.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
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
    // int before = app.getContactHelper().getContactCount();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().initContactModification(before.size() - 1);
    ContactData contact = new ContactData("Nadejda9", "Fedorova5", "NF5", "Peter-Service", "Шпалерная ул., дом 36, оф. 503",
            "921-791-1113", "921-791-1114", "nadejda2.fedorova2@peter-service.com", /*8, 6, "1983",*/ null);
    app.getContactHelper().fillContactForm(contact, false);

    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    // int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);

    // Comparator<? super ContactData> byNames = (c1, c2) -> c1.getFirstName().equals(c2.getFirstName());
    // s1.equals(s2)
    Comparator<? super ContactData> byNames = (c1, c2) -> c1.getFirstName().compareTo(c2.getFirstName());
    before.sort(byNames);
    after.sort(byNames);
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
    // int before = app.getContactHelper().getContactCount();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().initContactModification(before.size() - 1);
    app.getContactHelper().fillContactForm(new ContactData("Nadejda6", "Fedorova6", "NF6", "Peter-Service", "Шпалерная ул., дом 36, оф. 503",
            "921-791-1113", "921-791-1114", "nadejda2.fedorova2@peter-service.com", /*8, 6, "1983",*/ null), false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    // int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after.size(), before.size());
  }

  @Test
  public void testContactModification2() {
    app.getNavigationHelper().gotoHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Nadejda3", "Fedorova3", "NF3", "Peter-Service", "Шпалерная ул., дом 36, оф. 503",
              "921-791-1113", "921-791-1114", "nadejda2.fedorova2@peter-service.com", /*8, 6, "1983",*/ "test31"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    // int before = app.getContactHelper().getContactCount();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().initContactModification(before.size() - 1);
    app.getContactHelper().fillContactForm(new ContactData("Nadejda6", "Fedorova6", "NF6", "Peter-Service", "Шпалерная ул., дом 36, оф. 503",
            "921-791-1113", "921-791-1114", "nadejda2.fedorova2@peter-service.com", /*8, 6, "1983",*/ null), false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    // int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after.size(), before.size());
  }
}