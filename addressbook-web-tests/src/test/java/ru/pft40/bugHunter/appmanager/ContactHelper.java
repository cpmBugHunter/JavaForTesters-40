package ru.pft40.bugHunter.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.pft40.bugHunter.model.ContactData;


public class ContactHelper extends HelperBase {
    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void initContactCreation() {
        initContactModification(By.linkText("add new"));
    }

    public void fillContactForm(ContactData contactData, boolean isCreation) {
        type(By.name("firstname"), contactData.getName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("company"), contactData.getCompany());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("email"), contactData.geteMail());

        if(isCreation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void submitUserCreation(By locator) {
        click(locator);
    }

    public void initContactModification(By locator) {
        click(locator);
    }

    public boolean isThereAcontact() {
        return (isElementPresent(By.xpath("//tr[2]//input[@type=\"checkbox\"]")));
    }
}
