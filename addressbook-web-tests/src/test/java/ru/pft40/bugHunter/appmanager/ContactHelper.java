package ru.pft40.bugHunter.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.pft40.bugHunter.model.ContactData;
import ru.pft40.bugHunter.model.Contacts;

import java.util.ArrayList;
import java.util.List;


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
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("mobile"), contactData.getWorkPhone());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());

        if(isCreation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void fillForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
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
            String address = element.findElements(By.tagName("td")).get(3).getText();
            String emails = element.findElements(By.tagName("td")).get(4).getText();
            String allPhones = element.findElements(By.tagName("td")).get(5).getText();
            ContactData contact = new ContactData().withId(id).withName(firstName).withLastName(lastName)
                    .withAddress(address).withAllPhones(allPhones).withEmails(emails);
            contacts.add(contact);
        }
        return contacts;
    }


    public ContactData contactDataFromEditForm(ContactData contact) {
        initModification(contact);
        int id = contact.getId();
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String homePhone = wd.findElement(By.name("home")).getAttribute("value");
        String mobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
        String workPhone = wd.findElement(By.name("work")).getAttribute("value");
//        String email = wd.findElement(By.name("email")).getAttribute("value");
//        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
//        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(id).withName(firstName).withLastName(lastName).withAddress(address)
                .withHomePhone(homePhone).withMobilePhone(mobilePhone).withWorkPhone(workPhone);
    }
}
