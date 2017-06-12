package ru.pft40.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.io.File;


public class HelperBase {
    protected ApplicationManager appMngr;
    protected WebDriver wd;

    public HelperBase(ApplicationManager appMngr) {
        this.appMngr = appMngr;
        this.wd = appMngr.getDriver();
    }

    public void click(By locator) {
        wd.findElement(locator).click();
    }

    public void type(By locator, String text) {
        click(locator);
        if (text != null) {
            String existingText = wd.findElement(locator).getAttribute("value");
            if (! existingText.equals(text)) {
            wd.findElement(locator).clear();
            wd.findElement(locator).sendKeys(text);
            }
        }
    }

    public void alertAccept(){
        wd.switchTo().alert().accept();
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    protected boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
