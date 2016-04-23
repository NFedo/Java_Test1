package javaCourse.addressbook;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() {
    gotoAddNewContact();
    fillContactForm (new ContactData("Nadejda3", "Fedorova3", "NF3", "Peter-Service", "Шпалерная ул., дом 36, оф. 503",
                    "921-791-1113", "921-791-1114", "nadejda2.fedorova2@peter-service.com", 8, 6, "1983", 2));
    submitContactCreation();
    returnToHomePage();
  }

}
