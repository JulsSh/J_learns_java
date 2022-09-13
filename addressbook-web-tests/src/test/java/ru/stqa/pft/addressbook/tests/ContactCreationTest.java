package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase{


  @Test (enabled = false)
  public void CreateContactTest() throws Exception {

    app.getContactHelper().gotoHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact =new ContactData("username", "middle", "lastname",
            "comp", "[none]","addrr", "03056789", "julQjul.com");
    app.goTo().gotoCreateContactPage();
    app.getContactHelper().createContact(new ContactData("username", "middle", "lastname",
                        "comp", "[none]","addrr", "03056789", "julQjul.com"));
    app.getContactHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size()+1);
    contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}
