package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactPhoneTest extends TestBase {

  @Test

  public void testContactPhones() throws Exception {
    app.contact().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    assertThat(contact.getAddrr(), equalTo(contactInfoFromEditForm.getAddrr()));

  }

  private String mergeEmails(ContactData contact) {
    return  Arrays.asList(contact.getEmail1(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactPhoneTest::cleanedEmail)
            .collect(Collectors.joining("\n"));

  }

  private String mergePhones(ContactData contact) {
    return  Arrays.asList(contact.getPhonenum1(), contact.getPhonenum2(), contact.getPhonenum3(), contact.getSec_phonehone4())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactPhoneTest::cleanedPhone)
            .collect(Collectors.joining("\n"));
  }
  public static String cleanedPhone(String phone){
    return phone.replaceAll("\\s", "").replaceAll("[-.()]", "");
  }

  public static String cleanedEmail(String phone){
    return phone.replaceAll("\\s", "");
  }
}
