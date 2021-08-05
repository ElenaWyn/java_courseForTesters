package addressbook.tests;

import addressbook.appmanager.ApplicationManager;
import addressbook.model.Group;
import addressbook.model.GroupSet;
import org.hamcrest.CoreMatchers;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.stream.Collectors;

import static com.google.common.base.Predicates.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {

    protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.logout();
        app.stop();
    }

    public void verifyGroupListInUI() {
        if(Boolean.getBoolean("verifyUI")) {
            app.goTo().GroupPage();
            GroupSet dbGroups = app.db().groups();
            GroupSet uiGroups = app.group().all();
            assertThat(uiGroups, CoreMatchers.equalTo(dbGroups.stream()
                    .map((g) -> new Group().withId(g.getId()).withGroupName(g.getGroupName()))
                    .collect(Collectors.toSet())));
        }
    }



}
