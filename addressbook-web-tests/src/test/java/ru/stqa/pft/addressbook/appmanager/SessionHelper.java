package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase{
   public SessionHelper(WebDriver wd) {
    super(wd);
  }
  public void login(String userName, String userPwd) {
     type(By.name("user"), userName);
     type(By.name("pass"),userPwd);
     click(By.xpath("//input[@value='Login']"));
  }
  public void logout() {
    click(By.linkText("Logout"));
  }
}

