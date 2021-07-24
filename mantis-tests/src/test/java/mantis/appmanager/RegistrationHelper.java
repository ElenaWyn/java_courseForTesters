package mantis.appmanager;

import mantis.model.MailMessage;
import mantis.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import ru.lanwen.verbalregex.VerbalExpression;

import java.io.IOException;
import java.util.List;

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

    public void resetPassword(String user) {
        wd.findElement(By.linkText("управление")).click();
        wd.findElement(By.linkText("Управление пользователями")).click();
        wd.findElement(By.linkText("TEST")).click();
        wd.findElement(By.xpath("//input[@value='Сбросить пароль']")).click();
        wd.findElement(By.id("logout-link")).click();
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
