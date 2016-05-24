package javaCourse.addressbook.tests;

import javaCourse.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().homePage();
    Set<ContactData> before = app.contact().all();
    app.goTo().addNewContact();
    ContactData contact = new ContactData().withFirstName("Nadejda4").withLastName("Fedorova4").withNickName("NF3")
            .withCompany("Peter-Service").withAddress("Шпалерная ул., дом 36, оф. 503")
            .withPhoneHome("921-791-1113").withPhoneMobile("921-791-1114")
            .withEmail("nadejda2.fedorova2@peter-service.com")
            /* 8, 6, "1983",*/
            .withiGroup("test1");
    app.contact().create(contact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);
    // получаем идентификатор нового контакта
    contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    // добавляем новый контакт
    before.add(contact);
    Assert.assertEquals(before, after);
  }
}
