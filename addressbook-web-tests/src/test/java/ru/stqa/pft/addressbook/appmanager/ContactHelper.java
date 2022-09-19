package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
      int id =Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData().withId(id).withUsername(username).withLastname(lastname);
      contacts.add(contact);
    }
    return contacts;
  }


}
