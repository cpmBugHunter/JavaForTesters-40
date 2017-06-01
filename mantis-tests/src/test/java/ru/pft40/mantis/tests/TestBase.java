package ru.pft40.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.pft40.mantis.appmanager.ApplicationManager;


public class TestBase {

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

}
