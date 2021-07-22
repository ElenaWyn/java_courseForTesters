package addressbook.tests;

import addressbook.model.Contact;
import addressbook.model.ContactSet;
import addressbook.model.Group;
import addressbook.model.GroupSet;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class ContactsAndGroups  extends TestBase{
    @BeforeMethod
    public void ensurePreconditions() {
        if(app.db().groups().size() == 0) {
            app.goTo().GroupPage();
            app.group().create(new Group().withGroupName("Grupa").withGroupHeader("TestGroupHeader").withGroupFooter("TestGroupFooter"));

        }
        if (app.db().contacts().size() == 0) {
            app.contact().create(new Contact().withFirstname("Elena").withLastname("Doe").withAddress("Uliczna 5").withTel_home("1234567"));
        }
    }


    @Test
    public void addContactToGroup(){
        ContactSet contacts = app.db().contacts();
        GroupSet groups = app.db().groups();
        Group groupToAdd = app.contact().randomGroup(groups); // define group for adding
        Contact modifiedContact = app.contact().randomContact(contacts); //define contact which will be added to selected group

        //assert if contact is already in selected group or contact is in all groups. If yes, choose a new contact
        if (app.contact().isContactInGroup(modifiedContact, groupToAdd) || modifiedContact.getGroups().size() == groups.size()){
            modifiedContact = app.contact().randomContact(contacts);
        }
        GroupSet contactGroups = modifiedContact.getGroups();
        app.contact().addToGroup(modifiedContact, groupToAdd);
        contactGroups = contactGroups.withAdded(groupToAdd);

        //assertions
        contacts = app.db().contacts();
        for (Contact con : contacts) {
            if (con.getId() == modifiedContact.getId()) {
                Assert.assertEquals(con.getGroups(), contactGroups);
            }
        }

    }


    @Test
    public void deleteContactFromGroup() {
        ContactSet contacts = app.db().contacts();
        Contact modifiedContact = app.contact().randomContact(contacts); //define contact which will be deleted from selected group

        GroupSet contactGroups = modifiedContact.getGroups();
        if (contactGroups.size() == 0){
            GroupSet groups = app.db().groups();
            Group groupToAdd = app.contact().randomGroup(groups);
            app.contact().addToGroup(modifiedContact, groupToAdd);
            contactGroups = contactGroups.withAdded(groupToAdd);
        }

        Group groupDeleteFrom = app.contact().randomGroup(contactGroups);
        app.contact().deleteContactFromGroup(modifiedContact, groupDeleteFrom);
        contactGroups = contactGroups.without(groupDeleteFrom);

        //assertions
        contacts = app.db().contacts();
        for (Contact con : contacts) {
            if (con.getId() == modifiedContact.getId()) {
                Assert.assertEquals(con.getGroups(), contactGroups);
            }
        }




    }


}
