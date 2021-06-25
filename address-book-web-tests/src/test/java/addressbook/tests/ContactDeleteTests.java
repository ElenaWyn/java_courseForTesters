package addressbook.tests;

import addressbook.model.Contact;
import addressbook.model.ContactSet;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class ContactDeleteTests extends  TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.contact().create(new Contact().withFirstname("Elena").withLastname("Doe").withAddress("Uliczna 5").withTel_home("1234567"));
        }
    }

    @Test
    public void testContactDelete(){

        ContactSet before = app.contact().all();
        Contact deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.getSessionHelper().acceptAlert();
        app.contact().returnToHomePage();

        ContactSet after = app.contact().all();

        MatcherAssert.assertThat(after.size(), equalTo(before.size() - 1));
        MatcherAssert.assertThat(after, equalTo(before.without(deletedContact)));


    }


}
/* modification i delete index na ChooseContact and then remove (&add after modification) are different;
   chooseContact = remove-1
 */