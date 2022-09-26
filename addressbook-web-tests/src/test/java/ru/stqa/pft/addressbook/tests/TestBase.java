package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

public class TestBase {
  Logger logger= (Logger) LoggerFactory.getLogger(TestBase.class);

  protected static ApplicationManager app;

  static {
    try {
      app = new
              ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @BeforeSuite

  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite
  public void tearDown() throws Exception {
    app.stop();
  }

  public ApplicationManager getApp() {
    return app;
  }

  @BeforeMethod
  public void logTestStart(Method m, Object[] p) {
    logger.info("Start test " +m.getName() + " with parameters"+ Arrays.asList(p));

  }

  @AfterMethod(alwaysRun = true)
  public void logTestStop(Method m) {
    logger.info("Stop test " +m.getName());
  }
}
