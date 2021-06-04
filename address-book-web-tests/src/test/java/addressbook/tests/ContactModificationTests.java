package addressbook.tests;

import addressbook.model.Contact;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase{
    @Test
    public void testContactModification(){
        app.getContactHelper().chooseContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new Contact("Adam", "Mickiewicz", "Nowogrodek", "09876543"));
        app.getContactHelper().submitChanges();
        app.getNavigationHelper().goToHomePage();



    }
}
