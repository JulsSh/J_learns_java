package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class removeContactFromGroup extends TestBase  {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().groupPage();
    if (app.db().groups().size()==0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("groupjul"));
      if(app.db().contact().size()==0){
        app.contact().homePage();
        app.contact().create(new ContactData().withUsername("username").withMiddle("middle").withLastname("lastname")
                .withComp("comp").withAddrr("addrr").withPhonenum1("03056789")
                .withEmail1("julQjul.com"));
      }
    }
  }
  @Test
  public void testRemoveContactFromGroup() throws Exception {
 app.contact().homePage();
    GroupData selectGroup = app.db().groups().stream().iterator().next();
    ContactData selectContact = app.contact().selectContactToAdd(selectGroup, app.db().contact());
    System.out.println(selectContact);
    System.out.println(selectGroup);
    app.contact().clickGroup(app.db().groups().stream().iterator().next());
    app.contact().selectContactbyId(selectContact.getId());
    app.contact().removeFromGroup();
    Assert.assertFalse(app.db().getContactById(selectContact.getId()).getGroups().isPresent(selectGroup));


  }




}