package javaCourse.addressbook.tests;

import javaCourse.addressbook.model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Nadejda.Fedorova on 28.05.2016.
 */
public class ContactProfileTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstName("Nadejda7").withLastName("Fedorova7")
              .withPhoneHome("+7(921)-791-1113").withPhoneMobile("+7(921)-791-1114").withPhoneWork("+7(921)-791-1115")
              .withEmail("123@mail.ru").withcGroup("test1"));
    }
  }

  @Test
  public void testContactProfile() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    String contactStr = app.contact().infoFromProfileForm(contact);
    ContactData contactEdFrm = app.contact().infoFromEditForm(contact);
    // группу клиента получить с формы редактирования невозможно, так что все начиная со слов "Member of:" отрезаем
    // все пробельные символы тоже убираем
    if (contactStr.indexOf("Member of:") > 0) {
      contactStr = contactStr.substring(0, contactStr.indexOf("Member of:") - 1);}
    assertThat(cleaned(contactStr), equalTo(mergeInfo(contactEdFrm)));
  }

  private String mergeInfo(ContactData contact) {
    String mergeContactInfo = "";
    if (contact.getFirstName().length() > 0) {
      mergeContactInfo = mergeContactInfo + contact.getFirstName();
    }

    if (contact.getMiddleName().length() > 0) {
      mergeContactInfo = mergeContactInfo + contact.getMiddleName();
    }

    if (contact.getLastName().length() > 0) {
      mergeContactInfo = mergeContactInfo + contact.getLastName();
    }

    if (contact.getNickName().length() > 0) {
      mergeContactInfo = mergeContactInfo + contact.getNickName();
    }

    if (contact.getTitle().length() > 0) {
      mergeContactInfo = mergeContactInfo + contact.getTitle();
    }

    if (contact.getNickName().length() > 0) {
      mergeContactInfo = mergeContactInfo + contact.getCompany();
    }

    if (contact.getNickName().length() > 0) {
      mergeContactInfo = mergeContactInfo + contact.getAddress();
    }

    if (cleaned(contact.getPhoneHome()).length() > 0) {
      mergeContactInfo = mergeContactInfo + "H: " + contact.getPhoneHome();
    }

    if (cleaned(contact.getPhoneMobile()).length() > 0) {
      mergeContactInfo = mergeContactInfo + "M: " + contact.getPhoneMobile();
    }

    if (cleaned(contact.getPhoneWork()).length() > 0) {
      mergeContactInfo = mergeContactInfo + "W: " + contact.getPhoneWork();
    }

    if (cleaned(contact.getEmail()).length() > 0) {
      mergeContactInfo = mergeContactInfo + contact.getEmail() + " (www." + contact.getEmail().substring(contact.getEmail().indexOf("@") + 1) + ")";
    }

    if (cleaned(contact.getEmail2()).length() > 0) {
      mergeContactInfo = mergeContactInfo + contact.getEmail2() + " (www." + contact.getEmail2().substring(contact.getEmail2().indexOf("@") + 1) + ")";
    }

    if (contact.getEmail3().length() > 0) {
      mergeContactInfo = mergeContactInfo + contact.getEmail3() + " (www." + contact.getEmail3().substring(contact.getEmail3().indexOf("@") + 1) + ")";
    }

    if (cleaned(contact.getHomePage()).length() > 0) {
      mergeContactInfo = mergeContactInfo + "Homepage:" + contact.getHomePage() + "";
    }

    if (cleaned(contact.getYear()).length() > 0) {
      Calendar c = new GregorianCalendar();
      int ages = (c.get(Calendar.YEAR) - Integer.parseInt(contact.getYear()));
      mergeContactInfo = mergeContactInfo + "Birthday " + contact.getiDay() + ". " + contact.getStrMonth() + " " + contact.getYear() + " (" + ages + ")";
    }
    //  System.out.println(mergeContactInfo);
    return cleaned(mergeContactInfo);
  }

  /* Не пригодилось
  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getPhoneHome(), contact.getPhoneMobile(), contact.getPhoneWork())
            .stream().filter((s) -> ! s.equals("")).map(ContactPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));
  }*/

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "");
  }

}
