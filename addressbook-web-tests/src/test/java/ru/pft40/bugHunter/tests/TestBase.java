package ru.pft40.bugHunter.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.pft40.bugHunter.appmanager.ApplicationManager;

/**
 * Created by BugHunter on 15.04.2017.
 */
public class TestBase {

    protected final ApplicationManager appMngr = new ApplicationManager();

    @BeforeMethod
    public void setUp() throws Exception {
        appMngr.init();
    }

    @AfterMethod
    public void tearDown() {
        appMngr.stop();
    }

}
