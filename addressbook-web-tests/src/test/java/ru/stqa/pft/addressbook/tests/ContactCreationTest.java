package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase{

  @Test

  public void CreateContactTest() throws Exception {
    app.contact().homePage();
    app.goTo().createNew();
    File photo=new File("src/test/resources/photo.jpg");
    app.contact().create(new ContactData().withUsername("username").withMiddle("middle").withLastname("lastname")
            .withPhoto(photo).withComp("comp").withGroup("[none]").withAddrr("addrr").withPhonenum1("03056789")
                    .withEmail1("julQjul.com"));
    app.contact().homePage();

  }


}
