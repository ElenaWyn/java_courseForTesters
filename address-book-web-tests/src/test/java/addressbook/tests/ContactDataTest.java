package addressbook.tests;

import addressbook.model.Contact;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;

public class ContactDataTest extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.contact().create(new Contact().withFirstname("Elena").withLastname("Doe").withAddress("Uliczna 5").withTel_home("1234567"));
        }
    }

    @Test
    public void testContactPhones() {
        app.goTo().homePage();
        Contact contact = app.contact().all().iterator().next();
        Contact contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        MatcherAssert.assertThat(contact, equalTo(contactInfoFromEditForm));

        MatcherAssert.assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        MatcherAssert.assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
        MatcherAssert.assertThat(contact.getAllMails(), equalTo(mergeMails(contactInfoFromEditForm)));


    }

    private String mergeMails(Contact contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> ! s.equals(""))
                .collect(Collectors.joining("\n"));
    }

    private String mergePhones(Contact contact) {
        return Arrays.asList(contact.getTel_home(), contact.getTel_mobile(), contact.getTel_work())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactDataTest::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
