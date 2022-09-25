package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase{
  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
String line = reader.readLine();
while(line!=null){
  String[] split = line.split(";");
  list.add(new Object[] {new ContactData().withUsername(split[0]).withMiddle(split[1]).withLastname(split[2])});
  line= reader.readLine();
}
return list.iterator();
  }

  @Test(dataProvider = "validContacts")
  public void CreateContactTest(ContactData contact) throws Exception {

      app.contact().homePage();
      Contacts before =app.contact().all();
      //File photo=new File("src/test/resources/photo.jpg");
      app.contact().create(contact);

      assertThat(app.contact().count(), equalTo(before.size()+1));
      Contacts after = app.contact().all();
      //contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
      assertThat(after, equalTo(
              before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));



  }


}
