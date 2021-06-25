package addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{
    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void GroupPage() {

        if(isElementPresent(By.tagName("h1"))
                && wd.findElement(By.tagName("h1")).getText().equals("Groups")
                && isElementPresent(By.name("new"))) {
            return;
        }
        wd.findElement(By.xpath("//a[@href='group.php']")).click();
    }

    public void goToHomePage() {
        if(isElementPresent(By.id("maintable"))) {
            return;
        }
        wd.findElement(By.xpath("//a[@href='./']")).click(); }
}
