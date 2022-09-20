package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {
  public boolean acceptNextAlert = true;


  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("//input[@name='submit']"));
  }

  public void fillContactDetails(ContactData contactData, boolean CreateGroup) {

    click(By.name("firstname"));
    type(By.name("firstname"), contactData.getUsername());
    click(By.name("middlename"));
    type(By.name("middlename"), contactData.getMiddle());
    click(By.name("lastname"));
    type(By.name("lastname"), contactData.getLastname());
    click(By.name("company"));
    type(By.name("company"), contactData.getComp());
    click(By.name("address"));
    type(By.name("address"), contactData.getAddrr());
    click(By.name("home"));
    type(By.name("home"), contactData.getPhonenum1());
    click(By.name("email"));
    type(By.name("email"), contactData.getEmail1());
    if (CreateGroup) {
      new Select(wd.findElement(By.name("new group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new group")));
    }
    new Select(wd.findElement(By.name("new group"))).selectByVisibleText(contactData.getGroup());
  }

  public void delete() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void selectContactbyId(int id) {

    wd.findElement(By.cssSelector("input[value='" +id +"']")).click();
  }

  public void initModification(int index) {
    wd.findElement(By.xpath("//img[@alt='Edit']")).click();
  }

  public String closeAlertAndGetItsText() {
    try {
      Alert alert = wd.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }



  public void initContactCreation() {
    click(By.xpath("//a[contains(text(),'add new')]"));
  }
  public void submitContactModification() {
    click(By.xpath("//input[@value='Update']"));
  }
  public void returnToHomePage() {
    wd.findElement(By.linkText("home page")).click();
  }

  public void homePage() {
    if (isElementPresent(By.id("maintable"))) {
      return;
    }
    click(By.linkText("home"));
  }

  public void create(ContactData contact) {
    initContactCreation();
    fillContactDetails(contact, false);
    submitContactCreation();
    homePage();
  }
  public void modify( ContactData contact) {
   initModification(contact.getId());
   fillContactDetails(contact, false);
   submitContactModification();
   returnToHomePage();
  }


  public void delete(ContactData contact) {
    homePage();
    selectContactbyId(contact.getId());
    delete();
    acceptNextAlert = true;
    closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$");
    homePage();
  }


  public Contacts all() {
    Contacts contacts =new Contacts();
    List<WebElement> elements =wd.findElements(By.cssSelector("tr[name=entry]"));
    for (WebElement element : elements) {
      String username =element.findElement(By.xpath(".//td[3]")).getText();
      String lastname=element.findElement(By.xpath(".//td[2]")).getText();
      String addr = element.findElement(By.xpath(".//td[4]")).getText();
      String phones= element.findElement(By.xpath(".//td[6]")).getText();
      String emails=element.findElement(By.xpath(".//td[5]")).getText();

      String[] allemails= emails.split("\n");

      String[] allPhones = phones.split("\n");
      int id =Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new ContactData().withId(id).withUsername(username).withLastname(lastname).withAddrr(addr).withPhonenum1(allPhones[0])
              .withPhonenum2(allPhones[1]).withPhonenum3(allPhones[2]).withSec_phone4(allPhones[3]).withEmail1(allemails[0])
              .withEmail2(allemails[1]).withEmail3(allemails[2]));
    }
    return contacts;
  }
  public ContactData infoFromEditForm(ContactData contact) {
    initModification(contact.getId());
    String username= wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname= wd.findElement(By.name("lastname")).getAttribute("value");
    String phone1home= wd.findElement(By.name("home")).getAttribute("value");
    String phone2mobile= wd.findElement(By.name("mobile")).getAttribute("value");
    String phone3work= wd.findElement(By.name("work")).getAttribute("value");
    String phone4Homesec=wd.findElement(By.name("phone2")).getAttribute("value");
    String email1= wd.findElement(By.name("email")).getAttribute("value");
    String email2= wd.findElement(By.name("email2")).getAttribute("value");
    String email3= wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withUsername(username).withLastname(lastname)
            .withPhonenum1(phone1home).withPhonenum2(phone2mobile).withPhonenum3(phone3work)
            .withSec_phone4(phone4Homesec).withEmail1(email1).withEmail2(email2).withEmail3(email3);
  }
  public void initContactModificationById(int id){
    WebElement checkbox =wd.findElement(By.cssSelector(String.format("input[value='%s'", id)));
    WebElement row =checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells =row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();
    // wd.findElement(By.xpath(String.format("//input[value='%s']/../..td[8]/a", id))).click();
    //wd.findElement(By.xpath(String.format("//tr[.//input[value='%s']/td[8]/a", id))).click();
    //wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }
}