package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class testAddContactsToGroups extends TestBase {
int contactID;
int groupID;

String GroupName;
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() != 0) {
      List<ContactData> contacts = new ArrayList<ContactData>(app.db().contacts());
      for (ContactData contact : contacts) {
        System.out.println(contact.getGroups());
        if (contact.getGroups().size() == 0) {
          if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName(String.format("testjuls")));
          }
          int contactID = contact.getId(); //id of created contact
        } else if (contact.getGroups().size() != 0) {
          Groups contactGroups = app.db().getGroupsFromContact(contact.getId());
          List<GroupData> allGroups = new ArrayList<GroupData>((Collection<? extends GroupData>) app.db().groups());
          allGroups.removeAll(contactGroups);
          if (allGroups.size() == 0) { // 3. проверяем есть ли группа в которой нет контакта
            continue;
          } else if (allGroups.size() > 0) {
            contactID = contact.getId(); // 4. запоминаем айди контакта который не состоит в какой-то группе
          }
        }

        contactID = contact.getId();
        break;
      }
      if(contactID==0){
        contactID=app.db().freshContact();
        app.goTo().groupPage();
        app.group().create(new GroupData().withName(String.format("testju")));
      }
              }else {
      app.goTo().createNew();
      app.contact().create(new ContactData().withUsername("usrn").withLastname("lastname")
              .withAddrr("address")
              .withPhonenum2("111").withPhonenum1("22222").withEmail1("123@juls.com"));
      List<ContactData> contacts = new ArrayList<ContactData>(app.db().contacts());
      for (ContactData contact : contacts) {
        contactID = contact.getId();
      }
      if (app.db().groups().size()==0){
        app.goTo().groupPage();
        app.group().create(new GroupData().withName(String.format("testju")));
      }
    }
  }


  @Test
  public void testAddContactToGroup() throws Exception {
    ContactData contact = app.db().getContactWithoutGroup(contactID);
    if (contact.getGroups().size() < app.db().groups().size()) {
      Groups contactGroups = app.db().getGroupsFromContact(contactID);
      List<GroupData> allGroups = new ArrayList<GroupData>((Collection<? extends GroupData>) app.db().groups());
      allGroups.removeAll(contactGroups);
      System.out.println(allGroups.get(0).getId());
      GroupName = allGroups.get(0).getGroupName();
      groupID = allGroups.get(0).getId();
    }
    app.contact().homePage();
    GroupData newGroupFromContact = app.db().newGroupContact(groupID);
    app.contact().insertGroupToContact(contact, newGroupFromContact);
    assertThat(app.db().getContactInGroup(contactID).getGroups().contains(newGroupFromContact), equalTo(true));
    verifyContactsListInUi();

  }
}
