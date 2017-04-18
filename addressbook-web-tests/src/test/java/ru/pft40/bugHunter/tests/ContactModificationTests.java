package ru.pft40.bugHunter.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

/**
 * Created by BugHunter on 17.04.2017.
 */
public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModificationByUpperUpdateBtn() throws Exception {
        appMngr.getContactHelper().initContactModification(By.xpath("//*[@id=\"maintable\"]/tbody/tr[2]/td[8]/a"));
        appMngr.getContactHelper().type(By.name("address"), "Moscow, Pushkin str., 12/1 ap.34");
        appMngr.getContactHelper().click(By.xpath("//*[@value=\"Update\"][1]")); // upper update button
    }

    @Test
    public void testContactModificationByLowerUpdateBtn() throws Exception {
        appMngr.getContactHelper().initContactModification(By.xpath("//*[@id=\"maintable\"]/tbody/tr[2]/td[8]/a"));
        appMngr.getContactHelper().type(By.name("address"), "Vladivostok, Lenin str., 15 ap.56");
        appMngr.getContactHelper().click(By.xpath("//*[@value=\"Update\"][2]")); // lower update button
    }
}
