package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
private final Properties properties;
  WebDriver wd;
  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private ContactHelper contactHelper;
  private StringBuffer verificationErrors = new StringBuffer();

  private String browser;
  private DbHelper dbHelper;

  public ApplicationManager(String browser) throws IOException {

    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target=System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

if ("".equals(properties.getProperty("selenium.server"))) {

  if (browser.equals(BrowserType.FIREFOX)) {
    wd = new FirefoxDriver();
  } else if (browser.equals(BrowserType.CHROME)) {
    wd = new ChromeDriver();
  } else if (browser.equals(BrowserType.EDGE)) {
    wd = new EdgeDriver();
  }
}else {
  DesiredCapabilities capabilities= new DesiredCapabilities();
  capabilities.setBrowserName(browser);
  capabilities.setPlatform(Platform.fromString(System.getProperty("target", "Win10")));
  wd=new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
}
    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    wd.get(properties.getProperty("web.baseURL"));
    groupHelper = new GroupHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    contactHelper = new ContactHelper(wd);
    sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
    dbHelper= new DbHelper();
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

  public GroupHelper group() {
    return groupHelper;
  }
  public ContactHelper contact() {
    return contactHelper;
  }
  public NavigationHelper goTo() {
    return navigationHelper;
  }
  public DbHelper db(){ return dbHelper; }
}

