package javaCourse.addressbook.tests;

import javaCourse.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().homePage();
    List<ContactData> before = app.contact().list();
    app.goTo().addNewContact();
    ContactData contact = new ContactData().withFirstName("Nadejda3").withLastName("Fedorova3").withNickName("NF3")
            .withCompany("Peter-Service").withAddress("Шпалерная ул., дом 36, оф. 503")
            .withPhoneHome("921-791-1113").withPhoneMobile("921-791-1114")
            .withEmail("nadejda2.fedorova2@peter-service.com")
            /* 8, 6, "1983",*/
            .withiGroup("test1");
    app.contact().create(contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    // добавляем новый контакт
    before.add(contact);

    // Сортировка по фамилиям и именам
    Comparator<? super ContactData> byFullName = (c1, c2) -> c1.getFullName().compareTo(c2.getFullName());
    before.sort(byFullName);
    after.sort(byFullName);
    Assert.assertEquals(before, after);
    // Сравнение множеств
    // Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

   @Test // Если при создании группы передавать несуществующий номер группы, то тест будет падать
  public void testContactCreation1() {
    app.goTo().homePage();
    List<ContactData> before = app.contact().list();
    // int before = app.contact().getContactCount();
    app.goTo().addNewContact();
    ContactData contact = new ContactData().withFirstName("Nadejda7").withLastName("Fedorova7").withNickName("NF7")
             .withCompany("Peter-Service").withAddress("Шпалерная ул., дом 36, оф. 503")
             .withPhoneHome("921-791-1113").withPhoneMobile("921-791-1114")
             .withEmail("nadejda7.fedorova7@peter-service.com")
            /* 8, 6, "1983",*/
             .withiGroup("test1");
    app.contact().create(contact);
    List<ContactData> after = app.contact().list();
    //int after = app.contact().getContactCount();
    Assert.assertEquals(after.size(), before.size() + 1);

    // добавляем новый контакт
    before.add(contact);

    // Сортировка по фамилиям и именам
    Comparator<? super ContactData> byFullName = (c1, c2) -> c1.getFullName().compareTo(c2.getFullName());
    before.sort(byFullName);
    after.sort(byFullName);
    Assert.assertEquals(before, after);
    // Сравнение множеств
    // Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
   }
}
