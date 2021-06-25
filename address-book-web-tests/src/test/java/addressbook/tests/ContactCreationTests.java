package addressbook.tests;


import addressbook.model.Contact;
import addressbook.model.ContactSet;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.*;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {
  private WebDriver wd;


  @Test
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
