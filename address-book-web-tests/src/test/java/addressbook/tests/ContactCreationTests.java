package addressbook.tests;


import addressbook.model.Contact;
import addressbook.model.ContactSet;
import addressbook.model.Group;
import com.google.gson.Gson;
import org.openqa.selenium.json.TypeToken;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {
  private WebDriver wd;


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
  public void testContactCreationTests() throws Exception {
    app.goTo().homePage();
    ContactSet before = app.contact().all();
    Contact contact = new Contact()
            .withFirstname("Doe").withLastname("Elena").withAddress("Uliczna 5").withTel_home("1234567");
    app.contact().create(contact);
    app.contact().returnToHomePage();
    ContactSet after = app.contact().all();

    assertThat(after.size(), equalTo(before.size() +1));
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

  }



}
