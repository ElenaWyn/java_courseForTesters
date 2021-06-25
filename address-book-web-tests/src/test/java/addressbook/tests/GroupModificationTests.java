package addressbook.tests;

import addressbook.model.Group;
import addressbook.model.GroupSet;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class GroupModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().GroupPage();
        if(app.group().all().size() == 0) {
            app.group().create(new Group().withGroupName("Grupa").withGroupHeader("TestGroupHeader").withGroupFooter("TestGroupFooter"));
        }
    }

    @Test
    public void testGroupModification(){

        GroupSet before = app.group().all();
        Group modifiedGroup = before.iterator().next();
        Group group = new Group().withId(modifiedGroup.getId()).withGroupName("Edited Group").withGroupHeader("New Header").withGroupFooter("New Footer");
        app.group().modify(group);
        GroupSet after = app.group().all();
        
        Assert.assertEquals(after.size(), before.size());
        MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.without(modifiedGroup).withAdded(group)));
    }


}
