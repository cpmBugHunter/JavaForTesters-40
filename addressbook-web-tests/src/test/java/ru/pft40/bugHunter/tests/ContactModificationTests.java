package ru.pft40.bugHunter.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft40.bugHunter.model.ContactData;

import java.util.Comparator;
import java.util.List;


public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModificationByUpperUpdateBtn() {
        precondition();
        List<ContactData> before = appMngr.getContactHelper().getContactsList();
        appMngr.getContactHelper().initContactModification(before.size() - 1);
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Max", "Ivanov");
        appMngr.getContactHelper().type(By.name("firstname"), contact.getName());
        appMngr.getContactHelper().type(By.name("lastname"), contact.getLastName());
        appMngr.getContactHelper().click(By.xpath("//*[@value=\"Update\"][1]")); // upper update button
        appMngr.goTo().goToHomePage();
        List<ContactData> after = appMngr.getContactHelper().getContactsList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

    @Test
    public void testContactModificationByLowerUpdateBtn() {
        precondition();
        List<ContactData> before = appMngr.getContactHelper().getContactsList();
        appMngr.getContactHelper().initContactModification(before.size() - 1);
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Max", "Ivanov");
        appMngr.getContactHelper().type(By.name("firstname"), contact.getName());
        appMngr.getContactHelper().type(By.name("lastname"), contact.getLastName());
        appMngr.getContactHelper().click(By.xpath("//*[@value=\"Update\"][2]")); // lower update button
        appMngr.goTo().goToHomePage();
        List<ContactData> after = appMngr.getContactHelper().getContactsList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

    private void precondition() {
        appMngr.goTo().goToHomePage();
        if (! appMngr.getContactHelper().isThereAcontact()) {
            appMngr.getContactHelper().initContactCreation();
            appMngr.getContactHelper().fillContactForm(new ContactData("Max", "Ivanov", "Some Company LTD",
                    "+7(909)123-45-89", "madMax@mail.com"));
            appMngr.getContactHelper().submitUserCreation(By.xpath("//*[@name=\"submit\"][1]")); //upper Enter btn
        }
        appMngr.goTo().goToHomePage();
    }
}
