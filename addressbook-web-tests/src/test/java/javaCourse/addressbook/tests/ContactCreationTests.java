package javaCourse.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import javaCourse.addressbook.model.ContactData;
import javaCourse.addressbook.model.Contacts;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContactsFromXml() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(ContactData.class);
      List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
      return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactData> contacts  = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType()); // List<GroupData>.class
      return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
  }

  @Test (dataProvider = "validContactsFromXml")
  public void testContactCreation_1(ContactData contact) {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    app.goTo().addNewContact();
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test (dataProvider = "validContactsFromJson")
  public void testContactCreation_2(ContactData contact) {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    app.goTo().addNewContact();
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test
  public void testContactCreation_3() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    app.goTo().addNewContact();
    File photo = new File("src/test/resources/Syoma.jpg"); // относительный путь
    ContactData contact = new ContactData().withFirstName("Nadejda4").withLastName("Fedorova4").withNickName("NF3")
            .withCompany("Peter-Service").withAddress("Шпалерная ул., дом 36, оф. 503")
            .withPhoneHome("921-791-1113").withPhoneMobile("921-791-1114")
            .withEmail("nadejda2.fedorova2@peter-service.com")
            .withiDay(8).withiMonth(6).withYear("1983")
            .withcGroup("test1")
            .withPhoto(photo);
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
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
