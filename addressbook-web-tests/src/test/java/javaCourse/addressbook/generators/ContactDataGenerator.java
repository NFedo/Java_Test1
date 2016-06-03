package javaCourse.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
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
    save(contacts, new File(file));
  }

  private void save(List<ContactData> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file);
    for (ContactData contact : contacts) {
      writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n"
              , contact.getFirstName(),contact.getMiddleName(), contact.getLastName()
              , contact.getPhoneHome(), contact.getPhoneMobile(), contact.getPhoneWork()
              , contact.getEmail(), contact.getEmail3(), contact.getEmail3()
              , contact.getAddress()
              , contact.getcGroup()
              , contact.getCompany(),contact.getTitle()
      ));
    }
    writer.close(); // всегда надо закрывать файл
  }

  private List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData().withFirstName(String.format("Name %s", i))
              .withMiddleName(String.format("Middle Name %s", i)).withLastName(String.format("Surname %s", i))
              .withPhoneHome(String.format("Ph.+7 921 791 111%s", i)).withPhoneMobile(String.format("Ph.+7 921 791 111%s", i))
              .withPhoneWork(String.format("Ph.+7 921 791 111%s", i))
              .withEmail(String.format("Email %s", i)).withEmail2(String.format("Email2 %s", i))
              .withEmail3(String.format("Email3 %s", i))
              .withAddress(String.format("Address %s", i))
              .withcGroup(String.format("test %s", i))
              .withCompany(String.format("Company %s", i)).withTitle(String.format("Title %s", i))
      );
    }
    return contacts;
  }
}
