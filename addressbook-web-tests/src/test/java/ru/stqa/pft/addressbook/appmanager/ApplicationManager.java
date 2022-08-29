package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  WebDriver wd;
  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private ContactHelper contactHelper;
  private StringBuffer verificationErrors = new StringBuffer();
  private String browser;

  public ApplicationManager(String browser) {
    this.browser = browser;
  }

  public void init() {
       if (browser.equals(BrowserType.FIREFOX)){
      wd = new FirefoxDriver();
    } else if(browser.equals(BrowserType.CHROME)){
      wd=new ChromeDriver();
    } else if (browser.equals(BrowserType.IE))
  wd = new FirefoxDriver();
  wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
  wd.get("http://localhost/addressbook/group.php");
    groupHelper = new GroupHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper= new SessionHelper(wd);
    contactHelper =new ContactHelper(wd);
    sessionHelper.login("admin", "secret");
  }


  public void stop() {
   wd.quit();
  }



  public boolean isElementPresent(By by) {
    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }


  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public ContactHelper getContactHelper() {
    return contactHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }
}
