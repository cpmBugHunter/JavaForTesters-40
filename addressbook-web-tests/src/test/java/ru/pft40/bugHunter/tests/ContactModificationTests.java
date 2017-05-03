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
        appMngr.getContactHelper().type(By.name("firstname"), "Oleg");
        appMngr.getContactHelper().type(By.name("address"), "Moscow, Pushkin str., 12/1 ap.34");
        appMngr.getContactHelper().click(By.xpath("//*[@value=\"Update\"][1]")); // upper update button
        appMngr.getNavigationHelper().goToHomePage();
        List<ContactData> after = appMngr.getContactHelper().getContactsList();
        Assert.assertEquals(after.size(), before.size() - 1);

//        before.remove(before.size() - 1);
//        before.add(group);
//        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
//        before.sort(byId);
//        after.sort(byId);
//        Assert.assertEquals(before, after);
    }

    @Test
    public void testContactModificationByLowerUpdateBtn() throws Exception {
        precondition();
        appMngr.getContactHelper().initContactModification(By.xpath("//*[@id=\"maintable\"]/tbody/tr[2]/td[8]/a"));
        appMngr.getContactHelper().type(By.name("address"), "Vladivostok, Lenin str., 15 ap.56");
        appMngr.getContactHelper().click(By.xpath("//*[@value=\"Update\"][2]")); // lower update button
    }

    @Test
    public void testContactModificationByFillContactData() {
        precondition();
        appMngr.getContactHelper().initContactModification(By.xpath("//*[@id=\"maintable\"]/tbody/tr[2]/td[8]/a"));
        appMngr.getContactHelper().fillContactForm(new ContactData("Max", "Ivanov", "Some Company LTD",
                "+7(909)123-45-89", "madMax@mail.com", null), false);
        appMngr.getContactHelper().click(By.xpath("//*[@value=\"Update\"][2]")); // lower update button
        appMngr.getNavigationHelper().goToHomePage();
    }

    private void precondition() {
        appMngr.getNavigationHelper().goToHomePage();
        if (! appMngr.getContactHelper().isThereAcontact()) {
            appMngr.getContactHelper().initContactCreation();
            appMngr.getContactHelper().fillContactForm(new ContactData("Max", "Ivanov", "Some Company LTD",
                    "+7(909)123-45-89", "madMax@mail.com", null), false);
        }
    }
}
