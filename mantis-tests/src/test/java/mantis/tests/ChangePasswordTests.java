package mantis.tests;

import mantis.appmanager.HttpSession;
import mantis.model.MailMessage;
import mantis.model.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class ChangePasswordTests extends TestBase  {

    User admin = new User().withUserName("admin").withLogin("administrator").withPassword("root").withMail("test1");
    @BeforeMethod
    public void startMailServer() throws IOException {
        app.mail().start();
    }

    @Test
    public void changeUsersPassword () throws IOException {
        HttpSession session = app.newSession();
        session.login("administrator", "root");
        boolean a = session.login("administrator", "root");
        assertTrue(session.login("administrator", "login"));

        List <User> users = app.db().allUsers();
        User user = app.registration().getUser(admin);



        app.registration().resetPassword(admin, user.getUserName());
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
