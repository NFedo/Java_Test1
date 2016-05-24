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
public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (!app.contact().isThereAContact()) {
      app.contact().create(new ContactData().withFirstName("Nadejda7").withLastName("Fedorova7"));
    }
  }

  @Test
  public void testContactDeletion() {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().delete(index);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), index);
    // удаляем последний контакт
    before.remove(index);
    // Сортировка по фамилиям и именам
    Comparator<? super ContactData> byFullName = (c1, c2) -> c1.getFullName().compareTo(c2.getFullName());
    before.sort(byFullName);
    after.sort(byFullName);
    Assert.assertEquals(before, after);
  }

  @Test
  public void testContactDeletion1() {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().delete(index);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), index);
    // удаляем последний контакт
    before.remove(index);
    // Сортировка по фамилиям и именам
    Comparator<? super ContactData> byFullName = (c1, c2) -> c1.getFullName().compareTo(c2.getFullName());
    before.sort(byFullName);
    after.sort(byFullName);
    Assert.assertEquals(before, after);
  }
}
