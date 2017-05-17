package ru.pft40.bugHunter.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft40.bugHunter.model.ContactData;

import java.util.List;


public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletionViaModificationForm() {

        List<ContactData> before = appMngr.contact().list();
        appMngr.contact().initModification(By.xpath("//tr[2]//a[contains (@href, 'edit')]"));
        appMngr.contact().click(By.xpath("//input[@value=\"Delete\"]")); //delete button
        appMngr.goTo().homePage();
        List<ContactData> after = appMngr.contact().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(0);
        Assert.assertEquals(before, after);
    }

    @Test
    public void testContactDeletionOnHomePage() {

        List<ContactData> before = appMngr.contact().list();
        appMngr.contact().click(By.xpath("//tr[2]//input[@type=\"checkbox\"]")); //first contact
        appMngr.contact().click(By.xpath("//input[@value=\"Delete\"]")); //delete button
        appMngr.contact().alertAccept();
        appMngr.goTo().homePage();
        List<ContactData> after = appMngr.contact().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(0);
        Assert.assertEquals(before, after);
    }


}
