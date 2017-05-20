package ru.pft40.bugHunter.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.pft40.bugHunter.model.ContactData;
import ru.pft40.bugHunter.model.Contacts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ContactHelper extends HelperBase {
    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void initCreation() {
        initModification(By.linkText("add new"));
    }

    public void fillForm(ContactData contactData, boolean isCreation) {
        type(By.name("firstname"), contactData.getName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("company"), contactData.getCompany());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("email"), contactData.getEmail());

        if(isCreation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void fillForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("company"), contactData.getCompany());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("email"), contactData.getEmail());
    }

    public void pressSubmit(int number) {
        click(By.xpath(String.format("//*[@name=\"submit\"][%d]", number)));
    }

    public void initModification(By locator) {
        click(locator);
    }

    public void initModification(ContactData contact) {
        wd.findElement(By.xpath(String.format("//a[contains(@href, 'edit.php?id=%d')]", contact.getId()))).click();
    }

    public void pressUpdateButton(int number) {
        click(By.xpath(String.format("//*[@value=\"Update\"][%d]", number)));
    }

    public void create (ContactData contact, int buttonNumber) {
        initCreation();
        fillForm(contact);
        pressSubmit(buttonNumber); //'Submit' button number in such buttons list
    }

    public void modify(ContactData contact, int buttonNumber) {
        initModification(contact);
        type(By.name("firstname"), contact.getName());
        type(By.name("lastname"), contact.getLastName());
        pressUpdateButton(buttonNumber);
    }

    public void delete(ContactData contactData) {
        selectById(contactData.getId());
        click(By.xpath("//input[@value=\"Delete\"]"));
    }

    private void selectById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id +"']")).click();
    }

    public boolean isThereAcontact() {
        return (isElementPresent(By.xpath("//tr[@name=\"entry\"]")));
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String lastName = element.findElements(By.tagName("td")).get(1).getText();
            String firstName = element.findElements(By.tagName("td")).get(2).getText();
            ContactData contact = new ContactData().withId(id).withName(firstName).withLastName(lastName);
            contacts.add(contact);
        }
        return contacts;
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String lastName = element.findElements(By.tagName("td")).get(1).getText();
            String firstName = element.findElements(By.tagName("td")).get(2).getText();
            ContactData contact = new ContactData().withId(id).withName(firstName).withLastName(lastName);
            contacts.add(contact);
        }
        return contacts;
    }


}
