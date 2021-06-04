package addressbook.appmanager;

import addressbook.model.Group;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToGroupPage() {
      wd.findElement(By.linkText("group page")).click();
    }

    public void submitGroupCreation() {
      wd.findElement(By.name("submit")).click();
    }

    public void fillGroupForm(Group group) {
        type(By.name("group_name"), group.getGroupName());
        type(By.name("group_header"), group.getGroupHeader());
        type(By.name("group_footer"), group.getGroupFooter());
    }

    public void initGroupCreation() {
      wd.findElement(By.name("new")).click();
    }

    public void deleteGroup() {
        wd.findElement(By.xpath("(//input[@name='delete'])[2]")).click();
    }

    public void chooseGroup() {
        wd.findElement(By.name("selected[]")).click();
    }

    public void initGroupModification() {
        wd.findElement(By.name("edit")).click();

    }

    public void submitChanges() {
        wd.findElement(By.name("update")).click();
    }
}
