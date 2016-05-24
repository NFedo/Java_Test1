package javaCourse.addressbook.tests;

import javaCourse.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

/**
 * Created by Nadejda.Fedorova on 26.04.2016.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstName("Nadejda7").withLastName("Fedorova7"));
    }
  }

  @Test
  public void testContactModification() {
    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstName("Nadejda7").withLastName("Fedorova7").withNickName("NF7")
            .withCompany("Peter-Service").withAddress("Шпалерная ул., дом 36, оф. 503")
            .withPhoneHome("921-791-1113").withPhoneMobile("921-791-1114")
            .withEmail("nadejda7.fedorova7@peter-service.com")
            /* 8, 6, "1983",*/
            ;
    app.contact().modify(contact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());
    // удаляем выбранный контакт и добавляем новый со старым идентификатором
    before.remove(modifiedContact);
    before.add(contact);
    Assert.assertEquals(before, after);
  }

  @Test
  public void testContactModification1() {
    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstName("Nadejda7").withLastName("Fedorova7").withNickName("NF7")
            .withCompany("Peter-Service").withAddress("Шпалерная ул., дом 36, оф. 503")
            .withPhoneHome("921-791-1113").withPhoneMobile("921-791-1114")
            .withEmail("nadejda7.fedorova7@peter-service.com")
            /* 8, 6, "1983",*/
            ;
    app.contact().modify(contact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());
    // удаляем выбранный контакт и добавляем новый
    before.remove(modifiedContact);
    before.add(contact);
    Assert.assertEquals(before, after);
  }
}