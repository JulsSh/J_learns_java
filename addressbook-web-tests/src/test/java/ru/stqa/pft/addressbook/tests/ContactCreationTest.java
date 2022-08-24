package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase{


  @Test
  public void CreateContactTest() throws Exception {

    app.getNavigationHelper().gotoCreateContactPage();
    app.getContactHelper().fillContactDetails(new ContactData("username", "middle", "lastname", "comp", "addrr", "03056789", "julQjul.com"));
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().gotoHomePage();
  }


}
