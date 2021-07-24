package mantis.tests;

import mantis.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.util.stream.Collectors;

public class TestBase {

    protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.back"); ///home/elena/Documents/Java-WorkSpace/java_courseForTesters/mantis-tests
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        //app.logout();
        app.ftp().restore("config_inc.php.back", "config_inc.php");
        app.stop();
    }




}
