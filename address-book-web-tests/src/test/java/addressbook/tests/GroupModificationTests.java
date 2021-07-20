package addressbook.tests;

import addressbook.model.Group;
import addressbook.model.GroupSet;
import com.google.gson.Gson;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.json.TypeToken;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        if(app.db().groups().size() == 0) {
            app.goTo().GroupPage();
            app.group().create(new Group().withGroupName("Grupa").withGroupHeader("TestGroupHeader").withGroupFooter("TestGroupFooter"));

        }
    }


    @DataProvider
    public Iterator<Object[]> validGroupsJSON() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups1.json")));
        String json = "";
        String line  = reader.readLine();
        while (line != null)
        {
            json += line;
            line  = reader.readLine();
        }
        Gson gson = new Gson();
        List<Group> groups = gson.fromJson(json, new TypeToken<List<Group>>(){}.getType());
        return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();

    }

    @Test(dataProvider = "validGroupsJSON")
    public void testGroupModification(Group group){

        GroupSet before = app.db().groups();
        Group modifiedGroup = before.iterator().next();
        group.setId(modifiedGroup.getId());
        app.goTo().GroupPage();
        app.group().modify(group);
        assertThat(app.group().count(), equalTo(before.size()));
        GroupSet after = app.db().groups();
        MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.without(modifiedGroup).withAdded(group)));
    }


}
