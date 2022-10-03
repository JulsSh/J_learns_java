package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class testCreateContWoGeneration extends TestBase{
  @Test
  public void testCreateContWoGeneration() throws Exception {
    Groups groups = app.db().groups();
    if(app.db().groups().size()==0){
      app.group().create(new GroupData().withName("test juls"));
    }
    app.contact().homePage();
    Contacts before = app.db().contacts();

    app.contact().initContactCreation();

    ContactData con =new ContactData().withUsername("username").withMiddle("middle").withLastname("lastname")
            .withComp("companyj").withAddrr("addr").withPhonenum1("home").withPhonenum2("mobile").withPhonenum3("work")
            .withEmail1("email1@juls.com").withEmail2("email2@juls.com").withEmail3("email3@juls.com");
    //app.contact().initContactCreation();
    app.contact().fillContactDetails(con, false);
    app.contact().submitContactCreation();
    app.contact().homePage();
    Contacts after = app.db().contacts();
    //Assert.assertEquals(after.size(), before.size() + 1);
    //assertThat(after, equalTo(before));
  }
}
