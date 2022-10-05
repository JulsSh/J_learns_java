package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class contactAddToGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
    if (app.db().contact().size() == 0) {
      app.contact().homePage();
      app.contact().create(new ContactData().withUsername("username").withMiddle("middle")
              .withLastname("lastname").withComp("comp").withAddrr("addres").withPhonenum1("1111")
              .withPhonenum2("222").withPhonenum3("3333")
              .withEmail1("julia1.com"));
    }
  }

  @Test
  public void testContactAddToGroup() throws Exception {
    app.contact().homePage();
    app.contact().clickAllGroup("[all]");
    ContactData contact = app.db().contact().stream().iterator().next();

    if (contact.getGroups().equals(app.db().groups())) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("New group"));
      app.contact().homePage();

    }
    app.contact().selectContactbyId(contact.getId());
    GroupData selectGroup = app.group().selectGroupToAdd(contact, app.db().groups());
    app.contact().clickGroupToAdd(selectGroup);
    app.contact().submitAddContactToGroup();
    Assert.assertTrue(app.db().getContactById(contact.getId()).getGroups().isPresent(selectGroup));
  }

}

