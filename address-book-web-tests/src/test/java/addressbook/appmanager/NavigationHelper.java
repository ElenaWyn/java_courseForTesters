package addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{
    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void goToGroupPage() {
      wd.findElement(By.xpath("//a[@href='group.php']")).click();
    }

    public void goToHomePage() { wd.findElement(By.xpath("//a[@href='./']")).click(); }
}
