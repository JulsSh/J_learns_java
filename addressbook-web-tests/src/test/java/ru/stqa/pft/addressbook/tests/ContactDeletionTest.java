package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactDeletionTest extends TestBase {
@BeforeMethod
public void ensurePreconditions(){
        app.contact().homePage();
    if (app.contact().list().size()==0) {
    app.contact().create(new ContactData().withUsername("username").withMiddle("middle").withLastname("lastname")
            .withComp("comp").withGroup("[none]").withAddrr("addrr").withPhonenum1("03056789").withEmail1("julQjul.com"));
  }
    }

  @Test
  public void testContactDeletion() throws Exception {

    app.contact().homePage();
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData().withUsername("username").withMiddle("middle").withLastname("lastname")
            .withComp("comp").withGroup("[none]").withAddrr("addrr").withPhonenum1("03056789").withEmail1("julQjul.com");
int index=before.size() - 1;
    app.contact().delete(index);

    List<ContactData> after = app.contact().list();


    Assert.assertEquals(after.size(), index);

    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());


    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }




}
