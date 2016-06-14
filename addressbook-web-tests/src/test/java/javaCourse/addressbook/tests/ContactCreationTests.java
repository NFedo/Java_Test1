package javaCourse.addressbook.tests;

import javaCourse.addressbook.model.ContactData;
import javaCourse.addressbook.model.Contacts;
import javaCourse.addressbook.model.GroupData;
import javaCourse.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1").withHeader("header1").withFooter("footer1"));
    }
  }

  @Test
  public void testContactCreation() {
    Groups groups = app.db().groups();
    File photo = new File("src/test/resources/Syoma.jpg"); // относительный путь
    ContactData newContact = new ContactData().withFirstName("Nadejda4").withLastName("Fedorova4").withNickName("NF3")
            .withCompany("Peter-Service").withAddress("Шпалерная ул., дом 36, оф. 503")
            .withPhoneHome("921-791-1113").withPhoneMobile("921-791-1114")
            .withEmail("nadejda2.fedorova2@peter-service.com")
            .withiDay(8).withiMonth(6).withYear("1983")
            .withPhoto(photo)
            .inGroup(groups.iterator().next());
    app.goTo().homePage();
    Contacts before = app.contact().all();
    app.goTo().addNewContact();
    app.contact().create(newContact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(newContact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    verifyContactListInUI();
  }


/*
  @Test
  public void testCurrentDir() {
    File currentDir = new File("src/test/resources/Syoma.jpg"); // текущая директория
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/Syoma.jpg");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  } */
}
