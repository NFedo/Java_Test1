package javaCourse.addressbook.tests;

import javaCourse.addressbook.model.ContactData;
import org.testng.annotations.Test;

/**
 * Created by Nadejda.Fedorova on 26.04.2016.
 */
public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {

    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Nadejda3", "Fedorova3", "NF3", "Peter-Service", "Шпалерная ул., дом 36, оф. 503",
              "921-791-1113", "921-791-1114", "nadejda2.fedorova2@peter-service.com",/* 8, 6, "1983",*/ "test31"), true);
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactDeletion();
    app.getContactHelper().submitContactDeletion();
    app.getContactHelper().returnToHomePage();
  }

  @Test
  public void testContactDeletion1() {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Nadejda3", "Fedorova3", "NF3", "Peter-Service", "Шпалерная ул., дом 36, оф. 503",
              "921-791-1113", "921-791-1114", "nadejda2.fedorova2@peter-service.com",/* 8, 6, "1983",*/ "test61"), true);
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactDeletion();
    app.getContactHelper().submitContactDeletion();
    app.getContactHelper().returnToHomePage();
  }
}
