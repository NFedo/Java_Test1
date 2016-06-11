package javaCourse.addressbook.tests;

import javaCourse.addressbook.model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Nadejda.Fedorova on 25.05.2016.
 */
public class ContactAddressTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData().withFirstName("Nadejda7").withLastName("Fedorova7")
              .withAddress("191123, Санкт-Петербург, Шпалерная ул., дом 36, офис 512")
              .withcGroup("test1"));
    }
  }

  @Test
  public void testContactAddress() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    // надо убрать все пробельные символы из обоих адресов
    assertThat(cleaned(contact.getAddress()), equalTo(cleaned(contactInfoFromEditForm.getAddress())));
  }

  public static String cleaned(String email) {
    return email.replaceAll("\\s", "");
  }
}
