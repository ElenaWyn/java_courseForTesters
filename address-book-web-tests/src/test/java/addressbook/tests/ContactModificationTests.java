package addressbook.tests;

import addressbook.model.Contact;
import addressbook.model.Group;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase{
    @Test
    public void testContactModification(){
        app.getNavigationHelper().goToHomePage();
        List<Contact> before = app.getContactHelper().getContactList();
        app.getContactHelper().chooseContact(1);
        app.getContactHelper().fillContactForm(new Contact("Adam", "Adam", "Nowogrodek", "09876543"));
        app.getContactHelper().submitChanges();
        app.getNavigationHelper().goToHomePage();
        List<Contact> after = app.getContactHelper().getContactList();
        Comparator<? super Contact> byId = (ln1, ln2) -> Integer.compare(ln1.getId(), ln2.getId());
        before.remove(0);
        before.add(after.get(0));
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);




    }
}
