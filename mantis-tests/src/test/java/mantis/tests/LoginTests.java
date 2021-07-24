package mantis.tests;

import mantis.appmanager.HttpSession;
import org.testng.annotations.Test;

import java.io.IOException;

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
