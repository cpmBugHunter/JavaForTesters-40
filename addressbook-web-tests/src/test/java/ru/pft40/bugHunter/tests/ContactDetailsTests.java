package ru.pft40.bugHunter.tests;

import org.testng.annotations.Test;
import ru.pft40.bugHunter.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDetailsTests extends TestBase{


    @Test
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
