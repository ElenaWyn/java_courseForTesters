package addressbook.tests;

import addressbook.model.Group;
import org.testng.annotations.*;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {

    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().createGroup(new Group("TEST345", "Test4", "Test5"));
  }


}
