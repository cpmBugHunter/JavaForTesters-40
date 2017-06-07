package ru.pft40.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.pft40.mantis.model.MailMessage;

import java.util.List;

public class ChangePasswordTests extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        appMngr.mail().start();
    }

    /*@Test
    public void testMantisSendConfirmationLink() throws IOException, MessagingException {



        List<MailMessage> mailMessages = appMngr.mail().waitForMail(2, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        appMngr.registration().finish(confirmationLink, password);

        assertTrue(appMngr.newSession().login(user, password));
    }*/

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
