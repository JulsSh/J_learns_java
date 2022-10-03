package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class testCreateContWoGeneration extends TestBase{
  @Test
  public void testCreateContWoGeneration() throws Exception {
    Groups groups = app.db().groups();
    if(app.db().groups().size()==0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test juls"));
    }
    Groups freshGroups = app.db().groups();
    app.contact().initContactCreation();
    ContactData con =new ContactData().withUsername("username").withMiddle("middle").withLastname("lastname")
            .withComp("companyj").withAddrr("addr").withPhonenum1("home").withPhonenum2("mobile").withPhonenum3("work")
            .withEmail1("email1@juls.com").withEmail2("email2@juls.com").withEmail3("email3@juls.com")
            .inGroup(freshGroups.iterator().next());
    app.contact().fillContactDetails(con, true);
    app.contact().submitContactCreation();
    app.contact().homePage();

    int newConID=con.getId();

  }
}
