package addressbook.tests;

import addressbook.model.Contact;
import addressbook.model.ContactSet;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class ContactModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.contact().create(new Contact().withFirstname("Elena").withLastname("Doe").withAddress("Uliczna 5").withTel_home("1234567"));
        }
    }

    @Test
    public void testContactModification(){
        app.goTo().homePage();
        ContactSet before = app.contact().all();
        Contact modifiedContact = before.iterator().next();
        Contact newContactData = new Contact().withId(modifiedContact.getId()).withFirstname("Adam").withLastname("Adam").withAddress("Nowogrodek").withTel_home("09876543");
        app.contact().modify(modifiedContact, newContactData);
        app.goTo().homePage();
        ContactSet after = app.contact().all();


        MatcherAssert.assertThat(after.size(), CoreMatchers.equalTo(before.size()));
        MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.without(modifiedContact).withAdded(newContactData)));





    }


}
