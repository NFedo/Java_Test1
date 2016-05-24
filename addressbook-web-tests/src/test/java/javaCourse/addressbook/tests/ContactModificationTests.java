package javaCourse.addressbook.tests;

import javaCourse.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Nadejda.Fedorova on 26.04.2016.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (!app.contact().isThereAContact()) {
      app.contact().create(new ContactData().withFirstName("Nadejda7").withLastName("Fedorova7"));
    }
  }

  @Test
  public void testContactModification() {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    ContactData contact = new ContactData().withFirstName("Nadejda7").withLastName("Fedorova7").withNickName("NF7")
            .withCompany("Peter-Service").withAddress("Шпалерная ул., дом 36, оф. 503")
            .withPhoneHome("921-791-1113").withPhoneMobile("921-791-1114")
            .withEmail("nadejda7.fedorova7@peter-service.com")
            /* 8, 6, "1983",*/
            ;
    app.contact().modify(index, contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());
    // удаляем последний контакт и на его место добавляем новый
    before.remove(index);
    before.add(contact);

    // Сортировка только по именам
    Comparator<? super ContactData> byName = (c1, c2) -> c1.getFirstName().compareTo(c2.getFirstName());
    before.sort(byName);
    after.sort(byName);
    Assert.assertEquals(before, after);
  }

  @Test
  public void testContactModification1() {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    ContactData contact = new ContactData().withFirstName("Nadejda7").withLastName("Fedorova7").withNickName("NF7")
            .withCompany("Peter-Service").withAddress("Шпалерная ул., дом 36, оф. 503")
            .withPhoneHome("921-791-1113").withPhoneMobile("921-791-1114")
            .withEmail("nadejda7.fedorova7@peter-service.com")
            /* 8, 6, "1983",*/
            ;
    app.contact().modify(index, contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());
    // удаляем последний контакт и на его место добавляем новый
    before.remove(index);
    before.add(contact);

    // Сортировка по фамилиям и именам
    Comparator<? super ContactData> byFullName = (c1, c2) -> c1.getFullName().compareTo(c2.getFullName());
    before.sort(byFullName);
    after.sort(byFullName);
    Assert.assertEquals(before, after);
  }
}