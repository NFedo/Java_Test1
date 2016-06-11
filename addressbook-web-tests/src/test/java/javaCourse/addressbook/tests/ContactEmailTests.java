package javaCourse.addressbook.tests;

import javaCourse.addressbook.model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Nadejda.Fedorova on 25.05.2016.
 */
public class ContactEmailTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData().withFirstName("Nadejda7").withLastName("Fedorova7")
                    .withEmail("Nadejda1@mail.ru").withEmail2("Nadejda2@mail.ru").withEmail3("Nadejda3@mail.ru")
                    .withcGroup("test1"));
    }
  }

  @Test
  public void testContactEmails() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
     // надо удалить все пробельные символы перед сравнением
     assertThat(cleaned(contact.getAllEmails()), equalTo(cleaned(mergeEmails(contactInfoFromEditForm))));

//     assertThat(contact.getEmail(), equalTo(cleaned(contactInfoFromEditForm.getEmail())));
//     assertThat(contact.getEmail2(), equalTo(cleaned(contactInfoFromEditForm.getEmail2())));
//     assertThat(contact.getEmail3(), equalTo(cleaned(contactInfoFromEditForm.getEmail3())));
  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> ! s.equals("")).map(ContactEmailTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String email) {
    return email.replaceAll("\\s", "");
  }
}
