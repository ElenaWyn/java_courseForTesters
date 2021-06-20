package addressbook.tests;

import addressbook.model.Contact;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactDeleteTests extends  TestBase{
    @Test
    public void testContactDelete(){
        if (! app.getContactHelper().isThereAnyContact()) {
            app.getContactHelper().createContact(new Contact("Elena", "Doe", "Uliczna 5", "1234567"));
        }
        List<Contact> before = app.getContactHelper().getContactList();
        app.getContactHelper().chooseContact(1);
        app.getContactHelper().deleteContact();
        app.getSessionHelper().acceptAlert();
        app.getNavigationHelper().goToHomePage();
        List<Contact> after = app.getContactHelper().getContactList();

        before.remove(0);
        Assert.assertEquals(before, after);


    }
}
/* modification i delete index na ChooseContact and then remove (&add after modification) are different;
   chooseContact = remove-1
 */