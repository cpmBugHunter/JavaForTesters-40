package ru.pft40.bugHunter.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.pft40.bugHunter.model.ContactData;


public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModificationByUpperUpdateBtn() throws Exception {
        if (! appMngr.getContactHelper().isThereAcontact()) {
            appMngr.getContactHelper().initContactCreation();
            appMngr.getContactHelper().fillContactForm(new ContactData("Max", "Ivanov", "Some Company LTD",
                    "+7(909)123-45-89", "madMax@mail.com", null), false);
        }
        appMngr.getContactHelper().initContactModification(By.xpath("//*[@id=\"maintable\"]/tbody/tr[2]/td[8]/a"));
        appMngr.getContactHelper().type(By.name("firstname"), "Oleg");
        appMngr.getContactHelper().type(By.name("address"), "Moscow, Pushkin str., 12/1 ap.34");
        appMngr.getContactHelper().click(By.xpath("//*[@value=\"Update\"][1]")); // upper update button
    }

    @Test
    public void testContactModificationByLowerUpdateBtn() throws Exception {
        if (! appMngr.getContactHelper().isThereAcontact()) {
            appMngr.getContactHelper().initContactCreation();
            appMngr.getContactHelper().fillContactForm(new ContactData("Max", "Ivanov", "Some Company LTD",
                    "+7(909)123-45-89", "madMax@mail.com", null), false);
        }
        appMngr.getContactHelper().initContactModification(By.xpath("//*[@id=\"maintable\"]/tbody/tr[2]/td[8]/a"));
        appMngr.getContactHelper().type(By.name("address"), "Vladivostok, Lenin str., 15 ap.56");
        appMngr.getContactHelper().click(By.xpath("//*[@value=\"Update\"][2]")); // lower update button
    }

    @Test
    public void testContactModificationByFillContactData() throws Exception {

        if (! appMngr.getContactHelper().isThereAcontact()) {
            appMngr.getContactHelper().initContactCreation();
            appMngr.getContactHelper().fillContactForm(new ContactData("Max", "Ivanov", "Some Company LTD",
                    "+7(909)123-45-89", "madMax@mail.com"), false);
        }
        appMngr.getContactHelper().initContactModification(By.xpath("//*[@id=\"maintable\"]/tbody/tr[2]/td[8]/a"));
        appMngr.getContactHelper().fillContactForm(new ContactData("Max", "Ivanov", "Some Company LTD",
                "+7(909)123-45-89", "madMax@mail.com", null), false);
        appMngr.getContactHelper().click(By.xpath("//*[@value=\"Update\"][2]")); // lower update button
        appMngr.getNavigationHelper().goToHomePage();
    }
}
