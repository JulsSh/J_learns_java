package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.testng.Assert.*;

public class ContactDeletionTest extends TestBase{

  @Test
  public void testContactDeletion() throws Exception {
   app.getContactHelper().gotoHomePage();
   if (!app.getContactHelper().isThereAContact()){
     app.getContactHelper().createContact(new ContactData("username", "middle", "lastname", "comp", "[none]","addrr", "03056789", "julQjul.com"));
   }
    app.getContactHelper().gotoHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().acceptNextAlert = true;
    app.getContactHelper().deleteContact();
    assertTrue(app.getContactHelper().closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
    app.getContactHelper().gotoHomePage();
  }


}
