package ru.pft40.bugHunter.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft40.bugHunter.model.ContactData;
import ru.pft40.bugHunter.model.Contacts;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDetailsTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        if (appMngr.db().contacts().size() == 0) {
            appMngr.goTo().homePage();
            appMngr.contact().create(new ContactData().withName("Max").withLastName("Ivanov")
                    .withAddress("Country, City, Street")
                    .withWorkPhone("123-12-78").withMobilePhone("+7-912-456-12-93").withHomePhone("456-7896")
                    .withEmail("Ivanov@mail.loc").withEmail2("Ivanov2@mail.loc"). withEmail3("Ivanov3@mail.loc"), 1);
        }
        appMngr.goTo().homePage();
    }

    @Test
    public void testContactDetailsComparison_DB() {
        appMngr.goTo().homePage();
        ContactData contact = appMngr.db().contacts().iterator().next();
        ContactData contactDataFromEditForm = appMngr.contact().contactDataFromEditForm(contact);
        String expected = Arrays.stream(new String[] {mergeNames(contactDataFromEditForm)
                ,contactDataFromEditForm.getAddress()
                ,mergePhones(contactDataFromEditForm)
                ,mergeEmails(contactDataFromEditForm)}).filter((s) -> ! s.equals("")).collect(Collectors.joining("\n"));
        String contactDetails = cleanedPhones(appMngr.contact().contactDatafromDetailsForm(contact));

        assertThat(contactDetails, equalTo(expected));
    }


    @Test(enabled = false)
    public void testContactDetailsComparison() {
        appMngr.goTo().homePage();
        ContactData contact = appMngr.contact().all().iterator().next();
        ContactData contactDataFromEditForm = appMngr.contact().contactDataFromEditForm(contact);
        String expected = Arrays.stream(new String[] {mergeNames(contactDataFromEditForm)
                ,contactDataFromEditForm.getAddress()
                ,mergePhones(contactDataFromEditForm)
                ,mergeEmails(contactDataFromEditForm)}).filter((s) -> ! s.equals("")).collect(Collectors.joining("\n"));
        String contactDetails = cleanedPhones(appMngr.contact().contactDatafromDetailsForm(contact));

        assertThat(contactDetails, equalTo(expected));
    }



    //service methods
    private String mergePhones(ContactData contact) {
        return Arrays.stream(new String[]{contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone()})
                .filter((s) -> ! s.equals(""))
                .map(ContactDetailsTests::cleanedPhones)
                .collect(Collectors.joining("\n"));
    }

    private String mergeEmails(ContactData contact) {
        return Arrays.stream(new String[]{contact.getEmail(), contact.getEmail2(), contact.getEmail3()})
                .filter((s) -> ! s.equals("")).collect(Collectors.joining("\n"));
    }

    private String mergeNames(ContactData contact) {
        return Arrays.stream(new String[]{contact.getName(), contact.getMiddleName(), contact.getLastName()})
                .filter((n) -> ! n.equals("")).collect(Collectors.joining(" "));
    }

    private static String cleanedPhones(String str) {
        return str.replaceAll("[HMW]\\:\\ ", "").replaceAll("[\\n]{2,}", "\n");
    }
}
