package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

public class ContactModificationTest extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().contact().size()==0){
      app.contact().homePage();
      app.contact().create(new ContactData().withUsername("username").withMiddle("middle")
              .withLastname("lastname").withComp("comp").withAddrr("addres").withPhonenum1("1111")
              .withPhonenum2("222").withPhonenum3("3333")
              .withEmail1("jyahoo.com"));
    }
  }
  @Test
  public void testContactModification() {
    app.contact().homePage();
   Contacts before = app.db().contact();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact =new ContactData().withId(modifiedContact.getId()).withUsername("username").withMiddle("middle")
            .withLastname("lastname").withComp("comp").withAddrr("addres").withPhonenum1("1111")
            .withPhonenum2("222").withPhonenum3("3333")
            .withEmail1("julia1.com");
    app.contact().modify(contact);
    Contacts after = app.db().contact();
    Assert.assertEquals(after.size(), before.size());
    //assertThat(after, equalTo(before));
  }


}
