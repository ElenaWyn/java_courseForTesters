package addressbook.appmanager;

import addressbook.model.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToHomePage() {
        wd.findElement(By.linkText("home")).click();
    }

    public void submitContactCreation() {
        wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
    }

    public void fillContactForm(Contact contact) {
        type(By.name("firstname"), contact.getFirstname());
        type(By.name("lastname"), contact.getLastname());
        type(By.name("address"), contact.getAddress());
        type(By.name("home"), contact.getTel_home());
    }

    public void initNewContact() {
        wd.findElement(By.linkText("add new")).click();
    }

    public void chooseContact() {
        wd.findElement(By.name("selected[]")).click();
    }

    public void chooseContact(String id) {
        wd.findElement(By.id(id)).click();
    }


    public void deleteContact() {
        wd.findElement(By.xpath("//input[@value=\"Delete\"]")).click();
    }

    public void initContactModification(){
        String id = wd.findElement(By.name("selected[]")).getAttribute("id");
        chooseContact(id);
        wd.findElement(By.xpath("//input[@value = '"+id+"']/ancestor ::td[contains(@class, 'center')]/ancestor :: tr[contains(@name, 'entry')]/td[8]/a")).click();
    }

    public void submitChanges() { wd.findElement(By.name("update")).click(); }





}
