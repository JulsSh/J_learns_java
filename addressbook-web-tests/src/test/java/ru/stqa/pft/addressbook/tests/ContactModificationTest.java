package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTest extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().contacts().size()==0){
      app.contact().homePage();
      app.contact().create(new ContactData().withUsername("username").withMiddle("middle").withLastname("lastname")
              .withComp("comp").withGroup("[none]").withAddrr("addrr").withPhonenum1("03056789").withEmail1("jul1@Qjul.com").withEmail2("jul1@Qjul.com"));
    }
  }
  @Test
  public void testContactModification() {
    app.contact().homePage();
   Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact =new ContactData().withId(modifiedContact.getId()).withUsername("username").withMiddle("middle")
            .withLastname("lastname").withComp("comp").withGroup("[none]").withAddrr("addrr").withPhonenum1("03056789")
            .withEmail1("julQjul.com");
    app.contact().modify(contact);
    Contacts after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }


}
