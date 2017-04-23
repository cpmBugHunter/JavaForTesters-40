package ru.pft40.bugHunter.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.pft40.bugHunter.model.ContactData;

/**
 * Created by BugHunter on 17.04.2017.
 */
public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletionViaModificationForm() throws Exception {
        if (! appMngr.getContactHelper().isThereAcontact()) {
            appMngr.getContactHelper().initContactCreation();
            appMngr.getContactHelper().fillContactForm(new ContactData("Max", "Ivanov", "Some Company LTD",
                    "+7(909)123-45-89", "madMax@mail.com", "New group name"), true);
        }
        appMngr.getContactHelper().initContactModification(By.xpath("//tr[2]//a[contains (@href, 'edit')]"));
        appMngr.getContactHelper().click(By.xpath("//input[@value=\"Delete\"]")); //delete button
    }

    @Test
    public void testContactDeletionOnHomePage() throws Exception {
        if (! appMngr.getContactHelper().isThereAcontact()) {
            appMngr.getContactHelper().initContactCreation();
            appMngr.getContactHelper().fillContactForm(new ContactData("Max", "Ivanov", "Some Company LTD",
                    "+7(909)123-45-89", "madMax@mail.com", "New group name"), true);
        }
        appMngr.getContactHelper().click(By.xpath("//tr[2]//input[@type=\"checkbox\"]")); //first contact
        appMngr.getContactHelper().click(By.xpath("//input[@value=\"Delete\"]")); //delete button
        appMngr.getContactHelper().alertAccept();
    }
}
