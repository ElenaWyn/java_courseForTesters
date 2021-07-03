package addressbook.tests;

import addressbook.model.Contact;
import addressbook.model.ContactSet;
import addressbook.model.Group;
import com.google.gson.Gson;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.json.TypeToken;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;


public class ContactModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.contact().create(new Contact().withFirstname("Elena").withLastname("Doe").withAddress("Uliczna 5").withTel_home("1234567"));
        }
    }

    @DataProvider
    public Iterator<Object[]> validContactsJSON() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
        String json = "";
        String line  = reader.readLine();
        while (line != null)
        {
            json += line;
            line  = reader.readLine();
        }
        Gson gson = new Gson();
        List<Contact> contacts = gson.fromJson(json, new TypeToken<List<Group>>(){}.getType());
        return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();

    }


    @Test(dataProvider = "validContactsJSON")
    public void testContactModification(Contact newContactData){
        app.goTo().homePage();
        ContactSet before = app.contact().all();
        Contact modifiedContact = before.iterator().next();
        newContactData.withId(modifiedContact.getId());
        app.contact().modify(modifiedContact, newContactData);
        app.goTo().homePage();
        ContactSet after = app.contact().all();


        MatcherAssert.assertThat(after.size(), CoreMatchers.equalTo(before.size()));
        MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.without(modifiedContact).withAdded(newContactData)));





    }


}
