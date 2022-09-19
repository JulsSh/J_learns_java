package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase{


  @Test
  public void CreateContactTest() throws Exception {
    app.contact().homePage();
    Contacts before = app.contact().all();
    ContactData contact =new ContactData().withUsername("username").withMiddle("middle").withLastname("lastname")
            .withComp("comp").withGroup("[none]").withAddrr("addrr").withPhonenum1("03056789").withEmail1("julQjul.com");
    app.goTo().createNew();
    app.contact().create(contact);
    app.contact().homePage();
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size()+1));

    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }


}
