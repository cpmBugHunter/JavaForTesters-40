package ru.pft40.bugHunter.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

/**
 * Created by BugHunter on 17.04.2017.
 */
public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletionViaModificationForm() throws Exception {
        appMngr.getContactHelper().initContactModification();
        appMngr.getContactHelper().click(By.xpath("//input[@value=\"Delete\"]")); //delete button
    }

    @Test
    public void testContactDeletionOnHomePage() throws Exception {
        appMngr.getContactHelper().click(By.xpath("//tr[2]/td[1]/input[@type=\"checkbox\"]")); //first contact
        appMngr.getContactHelper().click(By.xpath("//input[@value=\"Delete\"]")); //delete button
    }
}
