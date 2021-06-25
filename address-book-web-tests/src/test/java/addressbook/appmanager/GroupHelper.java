package addressbook.appmanager;

import addressbook.model.Group;
import addressbook.model.GroupSet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public void clickToDelete() {
        wd.findElement(By.xpath("(//input[@name='delete'])[2]")).click();
    }

    public void choose(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void chooseById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void initGroupModification() {
        wd.findElement(By.name("edit")).click();

    }

    public void submitChanges() {
        wd.findElement(By.name("update")).click();
    }

    public void create(Group group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupPage();
        groupCash = null;
    }


    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<Group> list() {
        List<Group> groups = new ArrayList <Group>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groups.add(new Group().withId(id).withGroupName(name));
        }
        return groups;
    }

    private GroupSet groupCash = null;

    public GroupSet all() {
        if (!(groupCash == null)){
            return new GroupSet(groupCash);
        }
        groupCash = new GroupSet();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groupCash.add(new Group().withId(id).withGroupName(name));
        }
        return new GroupSet(groupCash);
    }


    public void modify(Group group) {
        chooseById(group.getId());
        initGroupModification();
        fillGroupForm(group);
        submitChanges();
        returnToGroupPage();
        groupCash = null;

    }

    public void delete(Group deletedGroup) {
        chooseById(deletedGroup.getId());
        clickToDelete();
        returnToGroupPage();
        groupCash = null;
    }


}
