package javaCourse.addressbook.tests;

import javaCourse.addressbook.model.ContactData;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Nadejda.Fedorova on 25.05.2016.
 */
public class ContactPhoneTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstName("Nadejda7").withLastName("Fedorova7")
                    .withPhoneHome("921-791-1113").withPhoneMobile("921-791-1114").withPhoneWork("921-791-1115"));
    }
  }

  @Test
  public void testContactPhones() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getPhoneHome(), equalTo(cleaned(contactInfoFromEditForm.getPhoneHome())));
    assertThat(contact.getPhoneMobile(), equalTo(cleaned(contactInfoFromEditForm.getPhoneMobile())));
    assertThat(contact.getPhoneWork(), equalTo(cleaned(contactInfoFromEditForm.getPhoneWork())));
  }

  public String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]","");
  }

}
