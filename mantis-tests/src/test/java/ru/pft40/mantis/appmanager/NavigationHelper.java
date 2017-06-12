package ru.pft40.mantis.appmanager;

import org.openqa.selenium.By;


public class NavigationHelper extends HelperBase {


    public NavigationHelper(ApplicationManager appMngr) {
        super(appMngr);
    }

    public void managePage() {
        if(isElementPresent((By.id("maintable")))) {
            return;
        } else {
            click(By.linkText("Manage"));
        }
    }

    public void homePage() {
        if(isElementPresent((By.id("maintable")))) {
            return;
        } else {
            click(By.linkText("My View"));
        }
    }

    public void page(String endpoint) {
        String baseUrl = appMngr.getProperty("web.baseUrl");
        String url = baseUrl + endpoint;
        wd.get(url);
    }

    public void manageUsersPage() {
        if(isElementPresent((By.xpath("//input[@value=\"Create New Account\"]")))) {
            return;
        } else {
            click(By.linkText("Manage Users"));
        }
    }
}
