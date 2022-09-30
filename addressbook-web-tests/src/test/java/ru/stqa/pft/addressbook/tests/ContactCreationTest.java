package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {


  @Test
  public void CreateContactTest(ContactData contact) throws Exception {
    //Groups groups = app.db().groups();
    if(app.db().groups().size()==0){
      app.group().create(new GroupData().withName("test juls"));
    }
    //app.contact().homePage();
    //File photo=new File("src/test/resources/photo.jpg");


    app.contact().initContactCreation();
    ContactData newContact= new ContactData().withUsername("username").withMiddle("middle").withLastname("lastname")
            .withComp("comp").withAddrr("addrr").withPhonenum1("03056789")
            .withEmail1("julQjul.com");

    Contacts before = app.db().contacts();
    app.contact().fillContactDetails(newContact, false);
    app.contact().submitContactCreation();
    app.contact().homePage();

    Contacts after = app.db().contacts();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
   //contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) ->
    c.getId()).max().getAsInt()))));


  }



}
