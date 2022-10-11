package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RedistrationTest extends TestBase {

  @BeforeTest
  public void startMailServer() {
    app.mail().start();

  }

  @Test

  public void testRegistration() throws MessagingException, IOException {
    long now=System.currentTimeMillis();
    String email = String.format("user%s@localhost.localdomain", now);
    String user =String.format("user%s",now) ;
    String password = "password";
    app.registraion().start(user, email);
    List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.registraion().finish(confirmationLink, password);
    assertTrue( app.newSession().login(user, password));

    app.registraion().start("user2", "user1@localhost.localdomain");
    app.mail().waitForMail(2, 10000);

  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod
  public void stopMailServer() {
    app.mail().stop();
  }
}
