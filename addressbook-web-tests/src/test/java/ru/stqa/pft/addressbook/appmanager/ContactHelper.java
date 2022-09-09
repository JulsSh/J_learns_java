package ru.stqa.pft.addressbook.appmanager;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.Comparator;
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
  }

  public void deleteContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void selectContact() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td/input"));
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

  public void initModification() {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void initContactCreation() {
    click(By.xpath("//a[contains(text(),'add new')]"));
  }

  public void submitContactModification() {
    click(By.xpath("//input[@value='Update']"));
  }

  public void gotoHomePage() {
    if (isElementPresent(By.id("maintable"))) {
      return;
    }
    click(By.linkText("home"));
  }

  public void createContact(ContactData contact) {
    initContactCreation();
    fillContactDetails(contact, false);
    submitContactCreation();
    gotoHomePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));

  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts =new ArrayList<ContactData>();
    List<WebElement> elements =wd.findElements(By.cssSelector("tr[name=entry]"));
  for (WebElement element : elements) {
    String username =element.findElement(By.xpath(".//td[3]")).getText();
    String lastname=element.findElement(By.xpath(".//td[2]")).getText();
    int id =Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
    ContactData contact = new ContactData(  username, null, lastname, null, null, null, null, null);
    contacts.add(contact);
  }
return contacts;
  }
}
