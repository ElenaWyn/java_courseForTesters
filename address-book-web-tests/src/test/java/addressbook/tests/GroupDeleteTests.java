package addressbook.tests;

import addressbook.model.Group;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GroupDeleteTests extends TestBase {

    @Test
    public void testGroupDelete(){
        app.getNavigationHelper().goToGroupPage();
        if(! app.getGroupHelper().isThereAnyGroup()) {
            app.getGroupHelper().createGroup(new Group("TEST345", "Test4", "Test5"));
        }
        List<Group> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().chooseGroup(before.size() - 1);
        app.getGroupHelper().deleteGroup();
        app.getGroupHelper().returnToGroupPage();
        List<Group> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() - 1);
        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);

    }




}
