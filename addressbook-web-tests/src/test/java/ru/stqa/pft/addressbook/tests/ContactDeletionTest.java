package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTest extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.contact().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withUsername("username").withMiddle("middle").withLastname("lastname")
              .withComp("comp").withGroup("[none]").withAddrr("addrr").withPhonenum1("03056789").withEmail1("julQjul.com"));
    }
  }
  @Test
  public void testContactDeletion() throws Exception {
    app.contact().homePage();
    Contacts before = app.contact().all();
    ContactData deleteContact = before.iterator().next();
    app.contact().delete(deleteContact);
    app.contact().homePage();

    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size()-1);
    assertThat(after, equalTo(before.without(deleteContact)));
  }


}
