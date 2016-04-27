package javaCourse.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by Nadejda.Fedorova on 26.04.2016.
 */
public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion() {

    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().initContactModification();
    app.getContactHelper().initContactDeletion();
    // app.getContactHelper().submitContactDeletion();
    app.getContactHelper().returnToHomePage();

   // app.getNavigationHelper().gotoHomePage();
   // app.getContactHelper().selectContact();
   // app.getContactHelper().initContactDeletion();
   // app.getContactHelper().submitContactDeletion();
   // app.getContactHelper().returnToHomePage();
  }
}
