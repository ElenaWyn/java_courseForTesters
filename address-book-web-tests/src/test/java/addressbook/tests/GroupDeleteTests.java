package addressbook.tests;

import addressbook.model.Group;
import org.testng.annotations.Test;

public class GroupDeleteTests extends TestBase {

    @Test
    public void testGroupDelete(){
        app.getNavigationHelper().goToGroupPage();
        if(! app.getGroupHelper().isThereAnyGroup()) {
            app.getGroupHelper().createGroup(new Group("TEST345", "Test4", "Test5"));
        }
        app.getGroupHelper().chooseGroup();
        app.getGroupHelper().deleteGroup();
        app.getGroupHelper().returnToGroupPage();
    }




}
