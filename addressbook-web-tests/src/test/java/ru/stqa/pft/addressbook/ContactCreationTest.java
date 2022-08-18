package ru.stqa.pft.addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ContactCreationTest {
  private WebDriver wd;


  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/group.php");
    login("admin", "secret");
  }

  @Test
  public void CreateContactTest() throws Exception {

    gotoCreateContactPage();
    fillContactDetails(new ContactsData("username", "middle", "lastname", "comp", "addrr", "03056789", "julQjul.com"));
    submitContactCreation();
    gotoHomePage();
  }

  private void submitContactCreation() {
    wd.findElement(By.xpath("//input[@name='submit']")).click();
  }

  private void gotoHomePage() {
    wd.findElement(By.linkText("home page")).click();
  }

  private void fillContactDetails(ContactsData contactsData) {
    wd.findElement(By.name("firstname")).click();
    wd.findElement(By.name("firstname")).sendKeys(contactsData.getUsername());
    wd.findElement(By.name("middlename")).click();
    wd.findElement(By.name("middlename")).sendKeys(contactsData.getMiddle());
    wd.findElement(By.name("lastname")).click();
    wd.findElement(By.name("lastname")).sendKeys(contactsData.getLastname());
    wd.findElement(By.name("company")).click();
    wd.findElement(By.name("company")).sendKeys(contactsData.getComp());
    wd.findElement(By.name("address")).click();
    wd.findElement(By.name("address")).sendKeys(contactsData.getAddrr());
    wd.findElement(By.name("home")).click();
    wd.findElement(By.name("home")).sendKeys(contactsData.getPhonenum1());
    wd.findElement(By.name("email")).click();
    wd.findElement(By.name("email")).sendKeys(contactsData.getEmail1());
  }

  private void gotoCreateContactPage() {
    wd.findElement(By.linkText("add new")).click();
  }

  private void login(String userName, String userPwd) {
    wd.findElement(By.name("user")).sendKeys(userName);
    wd.findElement(By.name("pass")).sendKeys(userPwd);
    wd.findElement(By.xpath("//input[@value='Login']")).click();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
    wd.quit();
  }

  private boolean isElementPresent(By by) {
    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

}
