package addressbook.tests;

import addressbook.model.Group;
import addressbook.model.GroupSet;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeleteTests extends TestBase {


    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().GroupPage();
        if(app.db().groups().size() == 0) {
            app.group().create(new Group().withGroupName("Grupa").withGroupHeader("TestGroupHeader").withGroupFooter("TestGroupFooter"));
        }
    }

    @Test
    public void testGroupDelete(){
        GroupSet before = app.db().groups();
        Group deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        assertThat(app.group().count(), equalTo(before.size() - 1));
        GroupSet after = app.db().groups();
        MatcherAssert.assertThat(after, equalTo(before.without(deletedGroup)));

    }




}
