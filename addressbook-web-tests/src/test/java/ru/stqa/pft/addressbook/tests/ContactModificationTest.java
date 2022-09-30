package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTest extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){

    if(app.db().groups().size()==0){
      app.group().create(new GroupData().withName("test juls"));
    }
    if (app.db().contacts().size()==0){
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
   Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact =new ContactData().withId(modifiedContact.getId()).withUsername("username").withMiddle("middle")
            .withLastname("lastname").withComp("comp").withAddrr("addres").withPhonenum1("1111")
            .withPhonenum2("222").withPhonenum3("3333")
            .withEmail1("julia1.com");
    app.contact().modify(contact);
    Contacts after = app.db().contacts();
    Assert.assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before));
  }


}
