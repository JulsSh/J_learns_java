package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper {
  public WebDriver wd;

  public SessionHelper(WebDriver wd) {
    this.wd=wd;
  }
  public void login(String userName, String userPwd) {
    wd.findElement(By.name("user")).sendKeys(userName);
    wd.findElement(By.name("pass")).sendKeys(userPwd);
    wd.findElement(By.xpath("//input[@value='Login']")).click();
  }
}

