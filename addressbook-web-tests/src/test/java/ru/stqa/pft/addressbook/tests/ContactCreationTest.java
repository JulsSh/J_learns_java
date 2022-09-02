package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase{


  @Test
  public void CreateContactTest() throws Exception {

    app.getNavigationHelper().gotoCreateContactPage();
    app.getContactHelper().createContact(new ContactData("username", "middle", "lastname",
            "comp", "[none]","addrr", "03056789", "julQjul.com"));

  }


}
