package addressbook.appmanager;

import addressbook.model.Contact;
import addressbook.model.ContactSet;
import addressbook.model.Group;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

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


    public void chooseContact(String id) {
        wd.findElement(By.id(id)).click();
    }

    public void initContactEdition(String id) {
        wd.findElement(By.xpath("//td//a[@href=\"edit.php?id=" + id + "\"]")).click();
    }

    public void modify(Contact modifiedContact, Contact newContactData) {
        initContactEdition(String.valueOf(modifiedContact.getId()));
        fillContactForm(newContactData);
        submitChanges();
    }


    public void deleteContact() {
        wd.findElement(By.xpath("//input[@value=\"Delete\"]")).click();
    }


    public void submitChanges() { wd.findElement(By.name("update")).click(); }


    public void create(Contact contact) {
        initNewContact();
        fillContactForm(contact);
        submitContactCreation();
    }


    //ContactData: 0 - id;
    //             1 - Last name
    //             2 - First Name
    //             3 - Address
    //             4 - All email
    //             5  - All phones
    public List<String> contactData (WebElement elementTR) {
        List<String> contactData = new ArrayList<String>();

        List<WebElement> data = elementTR.findElements(By.tagName("td"));
        contactData.add(data.get(0).findElement(By.tagName("input")).getAttribute("id"));
        for (WebElement text : data) {
            if (!(text == data.get(0))) {
                contactData.add(text.getText());
            }

        }

        return contactData;
    }

    public Contact makeContactFromList(List<String> dataForContact) {
        int id = Integer.parseInt(dataForContact.get(0));
        return new Contact().withId(id).withFirstname(dataForContact.get(2)).withLastname(dataForContact.get(1));
    }

    public List<WebElement> contactsOnPage() {
        return wd.findElements(By.xpath("//tr[@name = 'entry']"));
    }

    public ContactSet all() {
        ContactSet contacts = new ContactSet();
        List<WebElement> elements = contactsOnPage();
        for (WebElement element : elements) {
            List<String> data = contactData(element);
            Contact contact = makeContactFromList(data);
            contacts.add(contact);
        }
        return contacts;
    }

    public void delete(Contact contact) {
        chooseContact(String.valueOf(contact.getId()));
        deleteContact();
    }


}
