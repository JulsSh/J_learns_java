package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
  @Parameter(names = "-l",  description="Contact count")
  public int count;
  @Parameter(names = "-k",  description="Target file")
  public String file;
  @Parameter(names = "-n",  description="Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator=new ContactDataGenerator();
    JCommander jCommander=new JCommander(generator);
    try {
      jCommander.parse(args);
    }catch (ParameterException ex){
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    if (format.equals("csv")) {
      saveAsCsv(contacts, new File(file));
    }else if (format.equals("xml")){
      saveAsXml(contacts, new File(file));
    }else if (format.equals("json")){
      saveAsJson(contacts, new File(file));
    } else  {
      System.out.println("unrecognized format" +format);
    }
  }

  private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
    Gson gson= new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json= gson.toJson(contacts);
    Writer writer =new FileWriter(file);
    writer.write(json);
    writer.close();
  }

  private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    XStream xstream= new XStream();
    xstream.processAnnotations(ContactData.class);
    String xml=xstream.toXML(contacts);
    Writer writer =new FileWriter(file);
    writer.write(xml);
    writer.close();
  }

  private  void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file);
    for (ContactData contact : contacts) {
      writer.write(String.format("%s;%s;%s\n", contact, contact.getUsername(), contact.getLastname(), contact.getAddrr(),
              contact.getPhonenum1(), contact.getPhonenum2(), contact.getPhonenum3(), contact.getSec_phonehone4(),
              contact.getEmail1(), contact.getEmail2(), contact.getEmail3()));
    }
    writer.close();
  }

  private static List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData().withUsername(String.format("username %s", i)).withMiddle(String.format("middle %s", i)).withLastname(String.format("lastname %s", i))
              .withComp(String.format("company %s", i)).withGroup("[none]").withAddrr(String.format("address %s", i)).withPhonenum1(String.format("1111" +"%s", i))
              .withPhonenum2(String.format("2222" + "%s", i)).withPhonenum3(String.format("33333 %s", i)).withSec_phone4(String.format("4444 %s", i))
              .withEmail1(String.format("%s" + "email@yahoo.com", i)).withEmail2(String.format("%s" + "email@yahoo.com", i))
              .withEmail3(String.format("%s" + "email@yahoo.com", i)));
    }
    return contacts;
  }
}
