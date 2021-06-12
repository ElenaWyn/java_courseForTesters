package addressbook.tests;

import addressbook.model.Contact;
import org.testng.annotations.Test;

public class ContactDeleteTests extends  TestBase{
    @Test
    public void testContactDelete(){
        if (! app.getContactHelper().isThereAnyContact()) {
            app.getContactHelper().createContact(new Contact("Elena", "Doe", "Uliczna 5", "1234567"));
        }
        app.getContactHelper().chooseContact();
        app.getContactHelper().deleteContact();
        app.getSessionHelper().acceptAlert();
        app.getNavigationHelper().goToHomePage();

    }
}
