package ru.pft40.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.pft40.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        appMngr.mail().start();
    }

    @Test
    public void testRegisterNewUser() throws IOException, MessagingException {

        long now = System.currentTimeMillis();
        String user = String.format("account%s", now);
        String password = "password";
        String email = String.format("account%s@localhost.localhostdomain", now);
        appMngr.registration().start(user, email);
        List<MailMessage> mailMessages = appMngr.mail().waitForMail(2, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        appMngr.registration().finish(confirmationLink, password);

        assertTrue(appMngr.newSession().login(user, password));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        appMngr.mail().stop();
    }
}
