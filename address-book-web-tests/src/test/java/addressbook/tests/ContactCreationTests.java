package addressbook.tests;


import addressbook.model.Contact;
import org.testng.annotations.*;
import org.openqa.selenium.*;

public class ContactCreationTests extends TestBase {
  private WebDriver wd;


  @Test
  public void testGroupCreationTests() throws Exception {
    app.getContactHelper().initNewContact();
    app.getContactHelper().fillContactForm(new Contact("Elena", "Doe", "Uliczna 5", "1234567"));
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToHomePage();

  }



}
