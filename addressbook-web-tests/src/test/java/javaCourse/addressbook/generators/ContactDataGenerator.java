package javaCourse.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import javaCourse.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nadejda.Fedorova on 03.06.2016.
 */
public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file; // напрямую файл нельзя

  @Parameter(names = "-d", description = "Data format")
  public String format;


  public static void main(String[] args ) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    if (format.equals("csv")) {
      saveAsCsv(contacts, new File(file));
    } else if (format.equals("xml")) {
      saveAsXml(contacts, new File(file));
    } else if (format.equals("json")) {
      saveAsJson(contacts, new File(file));
    } else {
      System.out.println("Unrecognized format " + format);
    }
  }

  private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    try (Writer writer = new FileWriter(file)) {
      writer.write(json);
    }
  }

  private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML(contacts);
    try (Writer writer = new FileWriter(file)) {
      writer.write(xml);
    }
  }

  private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    try (Writer writer = new FileWriter(file)) {
      for (ContactData contact : contacts) {
        writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n"
                , contact.getFirstName(), contact.getMiddleName(), contact.getLastName()
                , contact.getPhoneHome(), contact.getPhoneMobile(), contact.getPhoneWork()
                , contact.getEmail(), contact.getEmail2(), contact.getEmail3()
                , contact.getCompany(),contact.getTitle(), contact.getAddress()
                , contact.getiDay(),contact.getiMonth(),contact.getYear()
                , contact.getPhotoPath()
                , contact.getcGroup())
        );
      }
    }
  }

  private List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData().withFirstName(String.format("Name %s", i))
              .withMiddleName(String.format("Middle Name %s", i)).withLastName(String.format("Surname %s", i))
              .withPhoneHome(String.format("Ph.+7 921 791 111%s", i)).withPhoneMobile(String.format("Ph.+7 921 791 111%s", i))
              .withPhoneWork(String.format("Ph.+7 921 791 111%s", i))
              .withEmail(String.format("Email%s@mail.ru", i)).withEmail2(String.format("Email2%s@mail.ru", i))
              .withEmail3(String.format("Email3%s@mail.ru", i))
              .withCompany(String.format("Company %s", i)).withTitle(String.format("Title %s", i))
              .withAddress(String.format("Address %s", i))
              .withiDay(i+1).withiMonth(i+1).withYear((String.valueOf(1970 + i)))
              .withPhotoPath("src/test/resources/Syoma.jpg")
              .withcGroup("test 1")
      );
    }
    return contacts;
  }
}
