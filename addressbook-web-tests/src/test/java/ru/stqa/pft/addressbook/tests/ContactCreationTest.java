package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase{


  @Test
  public void CreateContactTest() throws Exception {

    app.contact().homePage();
    List<ContactData> before = app.contact().list();
    ContactData contact =new ContactData().withUsername("username").withMiddle("middle").withLastname("lastname")
            .withComp("comp").withGroup("[none]").withAddrr("addrr").withPhonenum1("03056789").withEmail1("julQjul.com");
    app.goTo().createNew();
    app.contact().create(new ContactData().withUsername("username").withMiddle("middle").withLastname("lastname")
            .withComp("comp").withGroup("[none]").withAddrr("addrr").withPhonenum1("03056789").withEmail1("julQjul.com"));
    app.contact().homePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size()+1);
    contact.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}
