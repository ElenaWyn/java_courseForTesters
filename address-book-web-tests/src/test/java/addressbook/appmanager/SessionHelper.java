package addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {

    public SessionHelper(WebDriver wd) {
        super(wd);
    }

    public void logIn(String username, String password) {
        type(By.name("user"), username);
        type(By.name("pass"), password);
        wd.findElement(By.xpath("//input[@value='Login']")).click();
    }

    public void acceptAlert() {
        try {
            wd.switchTo().alert().accept();
        } catch (Exception e) {
            return;
        }
    }




}

