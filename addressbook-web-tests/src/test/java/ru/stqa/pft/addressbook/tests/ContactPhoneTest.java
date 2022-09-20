package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactPhoneTest extends TestBase {

  @Test

  public void testContactPhones() throws Exception {
    app.contact().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getPhonenum1(), equalTo(contactInfoFromEditForm.getPhonenum1()));
    assertThat(contact.getPhonenum2(), equalTo(contactInfoFromEditForm.getPhonenum2()));
    assertThat(contact.getPhonenum3(), equalTo(contactInfoFromEditForm.getPhonenum3()));
    assertThat(contact.getSec_phonehone4(), equalTo(contactInfoFromEditForm.getSec_phonehone4()));


  }
}