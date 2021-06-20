package addressbook.appmanager;

import addressbook.model.Contact;
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

    public void chooseContact(int index) {

        //wd.findElement(By.name("selected[]")).click();
        List<WebElement> contacts = contactsOnPage();
        contacts.get(index).findElement(By.xpath("//a/img[@title=\"Edit\"]")).click();
    }

    public void chooseContact(String id) {
        wd.findElement(By.id(id)).click();
    }


    public void deleteContact() {
        wd.findElement(By.xpath("//input[@value=\"Delete\"]")).click();
    }

    public void initContactModification(int index){
        //String id = wd.findElement(By.name("selected[]")).getAttribute("id");
        chooseContact(index);
        //wd.findElement(By.xpath("//input[@value = '"+id+"']/ancestor ::td[contains(@class, 'center')]/ancestor :: tr[contains(@name, 'entry')]/td[8]/a")).click();
    }

    public void submitChanges() { wd.findElement(By.name("update")).click(); }


    public void createContact(Contact contact) {
        initNewContact();
        fillContactForm(contact);
        submitContactCreation();
    }

    public boolean isThereAnyContact() {
        return isElementPresent(By.xpath("//a/img[@title=\"Edit\"]"));
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
        return new Contact (id, dataForContact.get(1), dataForContact.get(2));
    }

    public List<WebElement> contactsOnPage() {
        return wd.findElements(By.xpath("//tr[@name = 'entry']"));
    }

    public List<Contact> getContactList() {
        List<Contact> contacts = new ArrayList<Contact>();
        List<WebElement> elements = contactsOnPage();
        for (WebElement element : elements) {
            List<String> data = contactData(element);
            Contact contact = makeContactFromList(data);
            contacts.add(contact);
        }
        return contacts;
    }


}
