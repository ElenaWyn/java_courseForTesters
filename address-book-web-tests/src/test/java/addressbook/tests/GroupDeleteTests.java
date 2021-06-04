package addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeleteTests extends TestBase {

    @Test
    public void testGroupDelete(){
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().chooseGroup();
        app.getGroupHelper().deleteGroup();
        app.getGroupHelper().returnToGroupPage();
    }




}
