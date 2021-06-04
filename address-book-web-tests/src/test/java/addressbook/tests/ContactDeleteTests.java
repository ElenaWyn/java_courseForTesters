package addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeleteTests extends  TestBase{
    @Test
    public void testContactDelete(){
        app.getContactHelper().chooseContact();
        app.getContactHelper().deleteContact();
        app.getSessionHelper().acceptAlert();
        app.getNavigationHelper().goToHomePage();

    }
}
