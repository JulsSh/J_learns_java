package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {
  public ContactHelper contactHelper;

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }
  public void gotoGroupPage() {
    click(By.linkText("groups"));
  }
  public void returnToGroupPage() {
    click(By.linkText("group page"));
  }

  public void returnToHome() {
    click(By.linkText("home"));
  }

  public void gotoHomePage() {
    wd.findElement(By.linkText("home")).click();
  }


  public void gotoCreateContactPage() {
    wd.findElement(By.linkText("add new")).click();
  }
}
