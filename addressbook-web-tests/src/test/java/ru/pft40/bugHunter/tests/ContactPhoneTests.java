package ru.pft40.bugHunter.tests;

import org.testng.annotations.Test;
import ru.pft40.bugHunter.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

    @Test
    public void testContactPhonesComparison() {
        appMngr.goTo().homePage();
        ContactData contact = appMngr.contact().all().iterator().next();
        ContactData contactDataFromEditForm = appMngr.contact().contactDataFromEditForm(contact);

        assertThat(contact, equalTo(contactDataFromEditForm));
    }
}
