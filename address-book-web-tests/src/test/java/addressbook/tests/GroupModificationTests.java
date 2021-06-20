package addressbook.tests;

import addressbook.model.Group;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase{
    @Test
    public void testGroupModification(){
        app.getNavigationHelper().goToGroupPage();
        if(! app.getGroupHelper().isThereAnyGroup()) {
            app.getGroupHelper().createGroup(new Group("TEST345", "Test4", "Test5"));
        }
        List<Group> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().chooseGroup(before.size() - 1);
        app.getGroupHelper().initGroupModification();
        Group group = new Group(before.get(before.size() - 1).getId(),  "Edited Group", "New Header", "New Footer");
        app.getGroupHelper().fillGroupForm(group);
        app.getGroupHelper().submitChanges();
        app.getGroupHelper().returnToGroupPage();
        List<Group> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(group);

        Comparator<? super Group> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
