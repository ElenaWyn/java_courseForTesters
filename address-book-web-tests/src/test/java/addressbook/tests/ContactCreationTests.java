package addressbook.tests;


import addressbook.model.Contact;
import addressbook.model.Group;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.*;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {
  private WebDriver wd;


  @Test
  public void testGroupCreationTests() throws Exception {
    app.getNavigationHelper().goToHomePage();
    List<Contact> before = app.getContactHelper().getContactList();
    Contact contact = new Contact("Elena", "Doe", "Uliczna 5", "1234567");
    app.getContactHelper().createContact(contact);
    app.getContactHelper().returnToHomePage();
    List<Contact> after = app.getContactHelper().getContactList();



    Comparator<? super Contact> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Contact toAdd = after.get(after.size() -1);
    before.add(toAdd);
    Assert.assertEquals(before, after);


  }



}
