package ru.pft40.bugHunter.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.pft40.bugHunter.model.ContactData;

import java.util.ArrayList;
import java.util.List;


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

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("company"), contactData.getCompany());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("email"), contactData.geteMail());
    }

    public void submitUserCreation(By locator) {
        click(locator);
    }

    public void initContactModification(By locator) {
        click(locator);
    }

    public void initContactModification(int index) {
        List<WebElement> elements = wd.findElements(By.xpath("//img[@title=\"Edit\"]"));
        if (index >= 0 && index < elements.size()) {
            elements.get(index).click();
        } else if (index < 0){
            elements.get(0).click();
        } else {
            elements.get(elements.size() - 1).click();
        }
    }

    public boolean isThereAcontact() {
        return (isElementPresent(By.xpath("//input[@type=\"checkbox\"]")));
    }

    public List<ContactData> getContactsList() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String lastName = element.findElements(By.tagName("td")).get(1).getText();
            String firstName = element.findElements(By.tagName("td")).get(2).getText();
            ContactData contact = new ContactData(id, firstName, lastName);
            contacts.add(contact);
        }
        return contacts;
    }
}
