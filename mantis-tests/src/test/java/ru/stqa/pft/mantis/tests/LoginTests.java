package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.ManagerApp.HttpSession;

import static org.testng.AssertJUnit.assertTrue;

class LoginTest extends TestBase{
  @Test
  public void testLogin() throws Exception {

    HttpSession session = app.newSession();
    assertTrue(session.login("administrator","root"));
    assertTrue(session.isLoggedInAs("administrator"));


  }
}