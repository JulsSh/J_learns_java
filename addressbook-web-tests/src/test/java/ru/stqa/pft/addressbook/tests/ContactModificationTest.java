package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactModificationTest extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    app.contact().homePage();
    if (app.contact().all().size()==0){
      app.contact().create(new ContactData().withUsername("username").withMiddle("middle").withLastname("lastname")
              .withComp("comp").withGroup("[none]").withAddrr("addrr").withPhonenum1("03056789").withEmail1("julQjul.com"));
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
