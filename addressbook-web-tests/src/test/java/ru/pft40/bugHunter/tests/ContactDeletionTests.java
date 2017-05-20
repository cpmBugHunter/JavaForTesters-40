package ru.pft40.bugHunter.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft40.bugHunter.model.ContactData;
import ru.pft40.bugHunter.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactDeletionTests extends TestBase {

    @Test
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

    @Test
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
