package ru.pft40.bugHunter.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft40.bugHunter.generators.ContactDataGenerator;
import ru.pft40.bugHunter.model.ContactData;
import ru.pft40.bugHunter.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactModificationTests extends TestBase {


    @BeforeMethod
    public void ensurePreconditions() {
        if (appMngr.db().contacts().size() == 0) {
            appMngr.goTo().homePage();
            appMngr.contact().create(new ContactData().withName("Max").withLastName("Ivanov"), 1);
        }
        appMngr.goTo().homePage();
    }

    @Test
    public void testContactModification_GetContactsListFromDB() {
        Contacts before = appMngr.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withName(String.format("Name%s", System.currentTimeMillis()))
                .withLastName(String.format("LastName%s", System.currentTimeMillis()))
                .withAddress(modifiedContact.getAddress())
                .withEmail(modifiedContact.getEmail()).withEmail2(modifiedContact.getEmail2())
                .withEmail3(modifiedContact.getEmail3())
                .withHomePhone(modifiedContact.getHomePhone())
                .withMobilePhone(ContactDataGenerator.generatePhone(modifiedContact.getId()))
                .withWorkPhone(modifiedContact.getWorkPhone());
        appMngr.contact().modify(contact, 1);
        appMngr.goTo().homePage();
        Contacts after = appMngr.db().contacts();

        //assertThat(after.size(), equalTo(before.size()));
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        verifyContactListInUI();
    }

    @Test(enabled = false)
    public void testContactModification_GetContactsListFromUI() {
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
