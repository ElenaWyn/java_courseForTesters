package mantis.tests;

import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ProfileDataSearchResult;
import biz.futureware.mantis.rpc.soap.client.ProjectData;
import mantis.appmanager.HttpSession;
import mantis.model.MailMessage;
import mantis.model.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.List;

public class ChangePasswordTests extends TestBase  {

    User user = new User().withUserName("TEST1").withLogin("test").withPassword("test").withMail("test1");

    /*@BeforeMethod
    public void startMailServer() throws IOException {
        app.mail().start();
        app.registration().registrateNewUser(user);
    }*/

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

    @Test
    public void brudnopis() throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = app.soap().getMantisConnect();
        ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator", "root");
        //ProfileDataSearchResult b = mc.mc_user_profiles_get_all("administrator", "root", BigInteger.valueOf(1), BigInteger.valueOf(1));


    }





}
