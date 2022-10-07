package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

public class RedistrationTest extends TestBase{
  @Test

  public void testRegistration(){
    app.registraion().start("user1", "user1@localhost.localdomain");

  }
}
