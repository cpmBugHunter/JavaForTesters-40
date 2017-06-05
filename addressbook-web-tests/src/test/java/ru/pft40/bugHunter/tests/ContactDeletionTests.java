package ru.pft40.bugHunter.tests;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft40.bugHunter.model.ContactData;
import ru.pft40.bugHunter.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (appMngr.db().contacts().size() == 0) {
            appMngr.goTo().homePage();
            appMngr.contact().create(new ContactData().withName("Max").withLastName("Ivanov"), 1);
        }
        appMngr.goTo().homePage();
    }

    @Test
    public void testContactDeletionViaModificationForm_GetContactListFromDB() {

        Contacts before = appMngr.db().contacts();
        ContactData deletedContact = before.iterator().next();
        appMngr.contact().initModification(deletedContact);
        appMngr.contact().click(By.xpath("//input[@value=\"Delete\"]")); //delete button
        appMngr.goTo().homePage();
        Contacts after = appMngr.db().contacts();

        assertThat(after.size(), equalTo(before.size() - 1));
        assertThat(after, equalTo(before.without(deletedContact)));
    }

    @Test
    public void testContactDeletionOnHomePage_GetContactListFromDB() {

        Contacts before = appMngr.db().contacts();
        ContactData deletedContact = before.iterator().next();
        appMngr.contact().delete(deletedContact);
        appMngr.contact().alertAccept();
        appMngr.goTo().homePage();
        Contacts after = appMngr.db().contacts();

        assertThat(after.size(), equalTo(before.size() - 1));
        assertThat(after, equalTo(before.without(deletedContact)));
    }

    @Test(enabled = false)
    public void testContactDeletionViaModificationForm() {

        Contacts before = appMngr.contact().all();
        ContactData deletedContact = before.iterator().next();
        appMngr.contact().initModification(deletedContact);
        appMngr.contact().click(By.xpath("//input[@value=\"Delete\"]")); //delete button
        appMngr.goTo().homePage();
        Contacts after = appMngr.contact().all();

        assertThat(after.size(), equalTo(before.size() - 1));
        assertThat(after, equalTo(before.without(deletedContact)));
    }

    @Test(enabled = false)
    public void testContactDeletionOnHomePage() {

        Contacts before = appMngr.contact().all();
        ContactData deletedContact = before.iterator().next();
        appMngr.contact().delete(deletedContact);
        appMngr.contact().alertAccept();
        appMngr.goTo().homePage();
        Contacts after = appMngr.contact().all();

        assertThat(after.size(), equalTo(before.size() - 1));
        assertThat(after, equalTo(before.without(deletedContact)));
    }


}
