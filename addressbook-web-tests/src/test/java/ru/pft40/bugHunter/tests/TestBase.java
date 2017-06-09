package ru.pft40.bugHunter.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.pft40.bugHunter.appmanager.ApplicationManager;
import ru.pft40.bugHunter.model.ContactData;
import ru.pft40.bugHunter.model.Contacts;
import ru.pft40.bugHunter.model.GroupData;
import ru.pft40.bugHunter.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    protected static final ApplicationManager appMngr = new ApplicationManager(
            System.getProperty("browser", BrowserType.CHROME));


    @BeforeSuite
    public void setUp() throws Exception {
        appMngr.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        appMngr.stop();
    }

    @BeforeMethod(alwaysRun = true)
    public void logTestStart(Method m, Object[] p){
        logger.info("Test " + m.getName() + " with parameters " + Arrays.asList(p) + " started");
    }

    @AfterMethod
    public void logTestStop(Method m) {
        logger.info("Test " + m.getName() + " finished");
    }

    protected void verifyGroupListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Groups dbGroups = appMngr.db().groups();
            Groups uiGroups = appMngr.group().all();
            assertThat(uiGroups, equalTo(dbGroups.stream()
                    .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
                    .collect(Collectors.toSet())));
        }
    }

    protected void verifyContactListInUI() {

        if (Boolean.getBoolean("verifyUI")) {
            Contacts dbContacts = appMngr.db().contacts();
            appMngr.goTo().homePage();
            Contacts uiContacts = appMngr.contact().all();
            assertThat(uiContacts, equalTo(dbContacts.stream()
                    .map((c) -> new ContactData().withId(c.getId())
                            .withName(c.getName()).withLastName(c.getLastName()).withAddress(c.getAddress())
                            .withEmails(mergeEmails(c)).withAllPhones(mergePhones(c)))
                    .collect(Collectors.toSet())));
        }
    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3()).stream()
                .filter((s) -> ! s.equals("")).collect(Collectors.joining("\n"));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone()).stream()
                .filter((s) -> ! s.equals(""))
                .map(TestBase::cleaned)
                .collect(Collectors.joining("\n"));
    }

    private static String cleaned(String phone) {
        return phone.replaceAll("[\\s-()]*", "");
    }

}
