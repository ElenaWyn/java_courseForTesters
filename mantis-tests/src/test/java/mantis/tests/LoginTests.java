package mantis.tests;

import mantis.appmanager.HttpSession;
import mantis.model.MailMessage;
import mantis.model.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class LoginTests extends TestBase{

    @Test
    public void TestLogin() throws IOException {


        HttpSession session = app.newSession();
        boolean a = session.login("administrator", "root");
        assertTrue(session.login("administrator", "login"));
        assertTrue(session.isLoggedInAs("administrator"));


    }

}
