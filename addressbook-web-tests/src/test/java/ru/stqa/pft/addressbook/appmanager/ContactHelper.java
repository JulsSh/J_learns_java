package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase{
  public boolean acceptNextAlert = true;


  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("//input[@name='submit']"));
  }

  public void fillContactDetails(ContactData contactData, boolean creation) {
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
      if (creation) {
        new Select(wd.findElement(By.name("new group"))).selectByVisibleText(contactData.getGroup());
      } else{
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

  public void submitContactModification() {
    click(By.xpath("//input[@value='Update']"));
  }
}
