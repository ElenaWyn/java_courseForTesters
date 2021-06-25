package addressbook.tests;

import addressbook.model.Group;
import addressbook.model.GroupSet;
import org.testng.annotations.*;


import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {

    app.goTo().GroupPage();
    GroupSet before = app.group().all();
    Group group = new Group().withGroupName("Grupa").withGroupHeader("TestGroupHeader").withGroupFooter("TestGroupFooter");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size() +1));
    GroupSet after = app.group().all();
    assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }

  @Test
  public void testBadGroupCreation() throws Exception {

    app.goTo().GroupPage();
    GroupSet before = app.group().all();
    Group group = new Group().withGroupName("Grupa'").withGroupHeader("TestGroupHeader").withGroupFooter("TestGroupFooter");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size()));
    GroupSet after = app.group().all();
    assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }



}
