package addressbook.tests;

import addressbook.model.Group;
import org.testng.annotations.Test;

public class GroupModificationTests extends TestBase{
    @Test
    public void testGroupModification(){
        app.getNavigationHelper().goToGroupPage();
        if(! app.getGroupHelper().isThereAnyGroup()) {
            app.getGroupHelper().createGroup(new Group("TEST345", "Test4", "Test5"));
        }
        app.getGroupHelper().chooseGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new Group("Edited Group", "New Header", "New Footer"));
        app.getGroupHelper().submitChanges();
        app.getGroupHelper().returnToGroupPage();
    }
}
