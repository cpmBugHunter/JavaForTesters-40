package ru.pft40.bugHunter.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.pft40.bugHunter.appmanager.ApplicationManager;


public class TestBase {

    protected static final ApplicationManager appMngr = new ApplicationManager(BrowserType.FIREFOX);


    @BeforeSuite
    public void setUp() throws Exception {
        appMngr.init();
    }

    @AfterSuite
    public void tearDown() {
        appMngr.stop();
    }

}
