package mantis.appmanager;

import mantis.model.MailMessage;
import mantis.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.lanwen.verbalregex.VerbalExpression;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import static org.testng.AssertJUnit.assertTrue;

public class RegistrationHelper {
    private final ApplicationManager app;
    private WebDriver wd;

    public RegistrationHelper(ApplicationManager app) {
        this.app = app;
        wd = app.getDriver();
    }

    public void start(String username, String email) {

        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
        wd.findElement(By.name("username")).sendKeys(username);
        wd.findElement(By.name("email")).sendKeys(email);
        wd.findElement(By.cssSelector("input[value='Зарегистрироваться']")).click();

    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        wd.findElement(By.name("password")).sendKeys(password);
        wd.findElement(By.name("password_confirm")).sendKeys(password);
        wd.findElement(By.cssSelector("input[value='Update User']")).click();


    }

    public void resetPassword(User user, String userNameToResetPass) {
        if (!isElementExists(By.id("logged-in-user"))) {
            wd.findElement(By.id("username")).sendKeys(user.login);
            wd.findElement(By.id("password")).sendKeys(user.password);
            wd.findElement(By.xpath("//input[@type='submit']")).click();
        }

        assertTrue(wd.findElement(By.id("logged-in-user")).isDisplayed());


        wd.findElement(By.linkText("управление")).click();
        wd.findElement(By.linkText("Управление пользователями")).click();
        wd.findElement(By.linkText(userNameToResetPass)).click();
        wd.findElement(By.xpath("//input[@value='Сбросить пароль']")).click();
        wd.findElement(By.id("logout-link")).click();
    }

    public boolean isElementExists(By element) {
        try  {
            wd.findElement(element);
            return true;
        }
        catch (Exception ex) {
            return false;

        }


    }

    public void registrateNewUser(User user) throws IOException {
        String mail =  user.getMail();
        String userName = user.getUserName();
        String password = user.getPassword();
        app.registration().start(userName, mail);
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 30000);
        String confirmationLink = findConfirmationLink(mailMessages, mail);
        app.registration().finish(confirmationLink, password);
        Assert.assertTrue(app.newSession().login(userName, password));
    }
    public String findConfirmationLink(List<MailMessage> mailMessages, String mail) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(mail)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }


}
