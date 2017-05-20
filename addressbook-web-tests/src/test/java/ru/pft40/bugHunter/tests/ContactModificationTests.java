package ru.pft40.bugHunter.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft40.bugHunter.model.ContactData;
import ru.pft40.bugHunter.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactModificationTests extends TestBase {

    @BeforeMethod
    private void ensurePrecondition() {
        appMngr.goTo().homePage();
        ContactData contactData = new ContactData()
                .withName("Max")
                .withLastName("Ivanov")
                .withMobilePhone("+7(909)123-45-89")
                .withEmail("chupakabra@mail.com");
        if (! appMngr.contact().isThereAcontact()) {
            appMngr.contact().create(contactData, 1); //upper Enter btn
        }
        appMngr.goTo().homePage();
    }

    @Test
    public void testContactModificationByUpperUpdateBtn() {
        Contacts before = appMngr.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withName("Oleg").withLastName("Petrov");
        appMngr.contact().modify(contact, 1);
        appMngr.goTo().homePage();
        Contacts after = appMngr.contact().all();

        assertThat(after.size(), equalTo(before.size()));
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }

    @Test
    public void testContactModificationByLowerUpdateBtn() {
        Contacts before = appMngr.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withName("Maria").withLastName("Petrova");
        appMngr.contact().modify(contact, 2);
        appMngr.goTo().homePage();
        Contacts after = appMngr.contact().all();

        assertThat(after.size(), equalTo(before.size()));
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }



}
