package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase {
  @Test
  public void testContactModification() {
    app.getContactHelper().gotoHomePage();
    if (!app.getContactHelper().isThereAContact()){

      app.getContactHelper().createContact(new ContactData("username", null, null,
              null, null,null, null, null));
    }

    app.getContactHelper().gotoHomePage();
    app.getContactHelper().initModification();
    app.getContactHelper().fillContactDetails(new ContactData("EDIT", "EDIT", "EDIT", "EDIT", null,"EDIT", "03056789_", "EDIT"), false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().gotoHomePage();

  }
}
