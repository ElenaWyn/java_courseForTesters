package mantis.tests;

import mantis.appmanager.HttpSession;
import mantis.model.MailMessage;
import mantis.model.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import java.io.IOException;
import java.util.List;

public class ChangePasswordTests extends TestBase  {

    User user = new User().withUserName("TEST1").withLogin("test").withPassword("test").withMail("test1");

    @BeforeMethod
    public void startMailServer() throws IOException {
        app.mail().start();
        app.registration().registrateNewUser(user);
    }

    @Test
    public void changeUsersPassword () throws IOException {
        HttpSession session = app.newSession();
        session.login("administrator", "root");
        app.registration().resetPassword(user.getUserName());
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 30000);
        String confirmationLink = app.registration().findConfirmationLink(mailMessages, user.getMail());
        String newPassword = "12345";
        user = user.withPassword(newPassword);
        app.registration().finish(confirmationLink, newPassword);
        Assert.assertTrue(app.newSession().login(user.getUserName(), user.getPassword()));
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }





}
