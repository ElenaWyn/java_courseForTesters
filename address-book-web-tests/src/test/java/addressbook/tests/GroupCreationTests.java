package addressbook.tests;

import addressbook.model.Group;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {

    app.getNavigationHelper().goToGroupPage();
    List<Group> before = app.getGroupHelper().getGroupList();
    Group group = new Group("TEST345", "Test4", "Test5");
    app.getGroupHelper().createGroup(group);
    List<Group> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() +1);

/*
    int max = 0;
    for (Group g : after) {
      if (g.getId() > max) {
        max = g.getId();
      }
    }
    group.setId(max);
    group.setId(after.stream().max((t2, t1) -> Integer.compare(t2.getId(), t1.getId())).get().getId());

*/




    before.add(group);
    Comparator<? super Group> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }


}
