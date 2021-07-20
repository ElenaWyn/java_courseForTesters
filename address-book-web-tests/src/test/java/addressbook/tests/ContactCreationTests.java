package addressbook.tests;


import addressbook.model.Contact;
import addressbook.model.ContactSet;
import addressbook.model.Group;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
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
    List<Contact> contacts = gson.fromJson(json, new TypeToken<List<Contact>>(){}.getType());
    return contacts.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();

  }
  @DataProvider
  public Iterator<Object[]> validContactsXML() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));
    String xml = "";
    String line  = reader.readLine();
    while (line != null)
    {
      xml += line;
      line  = reader.readLine();
    }
    XStream xstream = new XStream();
    xstream.processAnnotations(Contact.class);
    List <Contact> contacts = (List <Contact>) xstream.fromXML(xml);
    return contacts.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();

  }



  @Test(dataProvider = "validContactsXML")
  public void testContactCreationTests(Contact contact) throws Exception {
    app.goTo().homePage();
    ContactSet before = app.db().contacts();
    //Contact con = new Contact().withFirstname("ABCD").withLastname("EFGHIJ");
    app.contact().create(contact);
    app.contact().returnToHomePage();
    ContactSet after = app.db().contacts();

    assertThat(after.size(), equalTo(before.size() +1));
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

  }



}
