package javaCourse.addressbook.tests;

import javaCourse.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().gotoAddNewContact();
    app.getContactHelper().createContact(new ContactData("Nadejda3", "Fedorova3", "NF3", "Peter-Service", "Шпалерная ул., дом 36, оф. 503",
            "921-791-1113", "921-791-1114", "nadejda2.fedorova2@peter-service.com",/* 8, 6, "1983",*/ "test61"), true);
  }

   @Test // тест будет падать, т.к. нет такой группы "test81" в выпадающем списке
  public void testContactCreation1() {
    app.getNavigationHelper().gotoAddNewContact();
    app.getContactHelper().createContact(new ContactData("Nadejda7", "Fedorova7", "NF3", "Peter-Service", "Шпалерная ул., дом 36, оф. 503",
            "921-791-1113", "921-791-1114", "nadejda2.fedorova2@peter-service.com", /*8, 6, "1983",*/ "test31"), true);
  }
}
