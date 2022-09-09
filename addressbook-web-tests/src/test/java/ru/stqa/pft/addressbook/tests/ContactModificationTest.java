package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTest extends TestBase {
  @Test
  public void testContactModification() {
    app.getContactHelper().gotoHomePage();
    if (!app.getContactHelper().isThereAContact()){

      app.getContactHelper().createContact(new ContactData("username", null, null,
              null, null,null, null, null));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().gotoHomePage();
    app.getContactHelper().initModification();
    ContactData contact = new ContactData(before.get(before.size() -1 ).getId(),"username", null, null,
            null, null,null, null, null);
    app.getContactHelper().fillContactDetails(contact, false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());
    before.remove(before.size()-1);
    before.add(contact);
    Comparator<? super ContactData> byId =(c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
 Assert.assertEquals(before, after);
  }
}
