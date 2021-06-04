package addressbook.tests;

import addressbook.model.Group;
import org.testng.annotations.Test;

public class GroupModificationTests extends TestBase{
    @Test
    public void testGroupModification(){
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().chooseGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new Group("Edited Group", "New Header", "New Footer"));
        app.getGroupHelper().submitChanges();
        app.getGroupHelper().returnToGroupPage();
    }
}
