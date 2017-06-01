package ru.pft40.mantis.appmanager;

import org.openqa.selenium.WebDriver;

public class RegistrationHelper {
    private final ApplicationManager appMngr;
    private WebDriver wd;

    public RegistrationHelper(ApplicationManager appMngr) {
        this.appMngr = appMngr;
        this.wd = appMngr.getDriver();
    }

    public void start(String username, String email) {
        wd.get(appMngr.getProperty("web.baseUrl") + "/signup_page.php");
    }
}
