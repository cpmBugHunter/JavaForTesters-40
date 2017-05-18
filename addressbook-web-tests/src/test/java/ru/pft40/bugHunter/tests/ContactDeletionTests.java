package ru.pft40.bugHunter.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft40.bugHunter.model.ContactData;

import java.util.Set;


public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletionViaModificationForm() {

        Set<ContactData> before = appMngr.contact().all();
        ContactData deletedContact = before.iterator().next();
        appMngr.contact().initModification(deletedContact);
        appMngr.contact().click(By.xpath("//input[@value=\"Delete\"]")); //delete button
        appMngr.goTo().homePage();
        Set<ContactData> after = appMngr.contact().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletedContact);
        Assert.assertEquals(before, after);
    }

    @Test
    public void testContactDeletionOnHomePage() {

        Set<ContactData> before = appMngr.contact().all();
        ContactData deletedContact = before.iterator().next();
        appMngr.contact().delete(deletedContact);
        appMngr.contact().alertAccept();
        appMngr.goTo().homePage();
        Set<ContactData> after = appMngr.contact().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletedContact);
        Assert.assertEquals(before, after);
    }


}
