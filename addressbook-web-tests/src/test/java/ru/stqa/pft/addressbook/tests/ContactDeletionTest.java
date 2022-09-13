package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

import static org.testng.Assert.*;

public class ContactDeletionTest extends TestBase {

  @Test(enabled = false)
  public void testContactDeletion() throws Exception {
    app.getContactHelper().gotoHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("username", "middle", "lastname", "comp", "[none]", "addrr", "03056789", "julQjul.com"));
    }
    app.getContactHelper().gotoHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData("username", "middle", "lastname",
            "comp", "[none]", "addrr", "03056789", "julQjul.com");

    app.getContactHelper().gotoHomePage();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteContact();
    app.getContactHelper().acceptNextAlert = true;
    app.getContactHelper().closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$");
    app.getContactHelper().gotoHomePage();

    List<ContactData> after = app.getContactHelper().getContactList();


    Assert.assertEquals(after.size(), before.size() - 1);

    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());



    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}
