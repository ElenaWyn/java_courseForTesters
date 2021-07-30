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
        //if there is no groups
        if(app.db().groups().size() == 0) {
            app.goTo().GroupPage();
            app.group().create(new Group().withGroupName("Grupa").withGroupHeader("TestGroupHeader").withGroupFooter("TestGroupFooter"));

        }
        //if there is no contacts
        if (app.db().contacts().size() == 0) {
            app.contact().create(new Contact().withFirstname("Elena").withLastname("Doe").withAddress("Uliczna 5").withTel_home("1234567"));
        }

        ContactSet contacts = app.db().contacts();

        int contactCount = contacts.size();

        GroupSet groups = app.db().groups();
        //is all contacts in all groups?


        int contactsInAllGroups = 0;
        int isInAllGroups = 0;

            for (Contact contact : contacts) {
                for (Group group : groups) {
                    if (app.contact().isContactInGroup(contact, group)) {
                        isInAllGroups = isInAllGroups + 1;
                    }
                }
                if (isInAllGroups == groups.size()) {
                    contactsInAllGroups = contactsInAllGroups + 1;
                }
                contactsInAllGroups = 0;
            }
            //if all contacts is in all groups create new contact
            if (contactsInAllGroups == contacts.size()) {
                app.contact().create(new Contact().withFirstname("Olena").withLastname("Doedoe").withAddress("Uliczna 5").withTel_home("1234567"));
            }



    }


    @Test
    public void addContactToGroup(){
        ContactSet contacts = app.db().contacts();
        GroupSet groups = app.db().groups();
        int i = 0;

        Contact modifiedContact = app.contact().randomContact(contacts); //define contact which will be added to selected group

        //assert if contact is already in selected group or contact is in all groups. If yes, choose a new contact
        while (modifiedContact.getGroups().size() == groups.size() && i< contacts.size()){
            modifiedContact = app.contact().randomContact(contacts);
            i++;
        }
        GroupSet contactGroups = modifiedContact.getGroups();

        //choose group to add contact in
        for (Group group : groups) {
            for (Group group1 : contactGroups) {
                if (group1 == group) {
                    groups.without(group1);
                }
            }
        }
        Group groupToAdd = app.contact().randomGroup(groups); // define group for adding


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
