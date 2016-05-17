package javaCourse.addressbook.tests;

import javaCourse.addressbook.model.ContactData;
import org.testng.annotations.Test;

/**
 * Created by Nadejda.Fedorova on 26.04.2016.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Nadejda3", "Fedorova3", "NF3", "Peter-Service", "Шпалерная ул., дом 36, оф. 503",
               "921-791-1113", "921-791-1114", "nadejda2.fedorova2@peter-service.com", /*8, 6, "1983"*,*/ "test61"));
    }
      // app.getContactHelper().selectContact();
      app.getContactHelper().initContactModification();
      app.getContactHelper().fillContactForm(new ContactData("Nadejda4", "Fedorova4", "NF4", "Peter-Service", "Шпалерная ул., дом 36, оф. 503",
              "921-791-1113", "921-791-1114", "nadejda2.fedorova2@peter-service.com", /*8, 6, "1983",*/ null), false);

     app.getContactHelper().submitContactModification();
     app.getContactHelper().returnToHomePage();
  }

  @Test
  public void testContactModification1() {
    app.getNavigationHelper().gotoHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Nadejda3", "Fedorova3", "NF3", "Peter-Service", "Шпалерная ул., дом 36, оф. 503",
              "921-791-1113", "921-791-1114", "nadejda2.fedorova2@peter-service.com",/* 8, 6, "1983",*/ "test61"));
    }
    // app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Nadejda6", "Fedorova6", "NF6", "Peter-Service", "Шпалерная ул., дом 36, оф. 503",
            "921-791-1113", "921-791-1114", "nadejda2.fedorova2@peter-service.com", /*8, 6, "1983",*/ null), false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
  }

  @Test
  public void testContactModification2() {
    app.getNavigationHelper().gotoHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Nadejda3", "Fedorova3", "NF3", "Peter-Service", "Шпалерная ул., дом 36, оф. 503",
              "921-791-1113", "921-791-1114", "nadejda2.fedorova2@peter-service.com", /*8, 6, "1983",*/ "test61"));
    }

    // app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Nadejda6", "Fedorova6", "NF6", "Peter-Service", "Шпалерная ул., дом 36, оф. 503",
            "921-791-1113", "921-791-1114", "nadejda2.fedorova2@peter-service.com", /*8, 6, "1983",*/ null), false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
  }
}