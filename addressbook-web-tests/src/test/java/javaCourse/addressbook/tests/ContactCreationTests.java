package javaCourse.addressbook.tests;

import javaCourse.addressbook.model.ContactData;
import javaCourse.addressbook.model.Contacts;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    app.goTo().addNewContact();
    ContactData contact = new ContactData().withFirstName("Nadejda4").withLastName("Fedorova4").withNickName("NF3")
            .withCompany("Peter-Service").withAddress("Шпалерная ул., дом 36, оф. 503")
            .withPhoneHome("921-791-1113").withPhoneMobile("921-791-1114")
            .withEmail("nadejda2.fedorova2@peter-service.com")
            /* 8, 6, "1983",*/
            .withiGroup("test1");
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}
