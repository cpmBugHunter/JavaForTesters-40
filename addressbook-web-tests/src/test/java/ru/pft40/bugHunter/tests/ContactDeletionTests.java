package ru.pft40.bugHunter.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft40.bugHunter.model.ContactData;

import java.util.List;


public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletionViaModificationForm() {
        precondition();
        List<ContactData> before = appMngr.getContactHelper().getContactsList();
        appMngr.getContactHelper().initContactModification(By.xpath("//tr[2]//a[contains (@href, 'edit')]"));
        appMngr.getContactHelper().click(By.xpath("//input[@value=\"Delete\"]")); //delete button
        appMngr.goTo().goToHomePage();
        List<ContactData> after = appMngr.getContactHelper().getContactsList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(0);
        Assert.assertEquals(before, after);
    }

    @Test
    public void testContactDeletionOnHomePage() {
        precondition();
        List<ContactData> before = appMngr.getContactHelper().getContactsList();
        appMngr.getContactHelper().click(By.xpath("//tr[2]//input[@type=\"checkbox\"]")); //first contact
        appMngr.getContactHelper().click(By.xpath("//input[@value=\"Delete\"]")); //delete button
        appMngr.getContactHelper().alertAccept();
        appMngr.goTo().goToHomePage();
        List<ContactData> after = appMngr.getContactHelper().getContactsList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(0);
        Assert.assertEquals(before, after);
    }

    private void precondition() {
        appMngr.goTo().goToHomePage();
        if (! appMngr.getContactHelper().isThereAcontact()) {
            appMngr.getContactHelper().initContactCreation();
            appMngr.getContactHelper().fillContactForm(new ContactData("Max", "Ivanov", "Some Company LTD",
                    "+7(909)123-45-89", "madMax@mail.com", null), true);
        }
    }
}
