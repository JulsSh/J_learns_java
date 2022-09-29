package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.openqa.selenium.json.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {
  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));
    String xml="";
    String line = reader.readLine();
    while (line != null) {
     xml+=line;
      line = reader.readLine();
    }
    XStream xStream =new XStream();
    xStream.processAnnotations(ContactData.class);
    xStream.allowTypes(new Class[]{ContactData.class});
    List<ContactData> contacts = (List<ContactData>) xStream.fromXML(xml);
    return contacts.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
  }
  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
    String json ="";
    String line = reader.readLine();
    while (line != null) {
      json+=line;
      line=reader.readLine();
    }
    Gson gson= new Gson();
    List<ContactData> contacts= gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType()); //List<GroupData>.class
    return contacts.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();

  }

  @Test(dataProvider = "validContactsFromJson")
  public void CreateContactTest(ContactData contact) throws Exception {
    app.contact().homePage();
    Contacts before = app.db().contacts();
    File photo=new File("src/test/resources/photo.jpg");
    app.contact().create(contact);

    Contacts after = app.db().contacts();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
   //contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) ->
    c.getId()).max().getAsInt()))));


  }



}
