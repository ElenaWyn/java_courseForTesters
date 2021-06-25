package addressbook.tests;

import addressbook.model.Group;
import addressbook.model.GroupSet;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;

public class GroupDeleteTests extends TestBase {


    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().GroupPage();
        if(app.group().all().size() == 0) {
            app.group().create(new Group().withGroupName("Grupa").withGroupHeader("TestGroupHeader").withGroupFooter("TestGroupFooter"));
        }
    }

    @Test
    public void testGroupDelete(){
        GroupSet before = app.group().all();
        Group deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        GroupSet after = app.group().all();
        Assert.assertEquals(after.size(), before.size() - 1);
        MatcherAssert.assertThat(after, equalTo(before.without(deletedGroup)));

    }




}
