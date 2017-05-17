package ru.pft40.bugHunter.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft40.bugHunter.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;


public class ContactModificationTests extends TestBase {

    @BeforeMethod
    private void ensurePrecondition() {
        appMngr.goTo().homePage();
        ContactData contactData = new ContactData()
                .withName("Max")
                .withLastName("Ivanov")
                .withMobilePhone("+7(909)123-45-89")
                .withEmail("chupakabra@mail.com");
        if (! appMngr.contact().isThereAcontact()) {
            appMngr.contact().create(contactData, 1); //upper Enter btn
        }
        appMngr.goTo().homePage();
    }

    @Test
    public void testContactModificationByUpperUpdateBtn() {
        Set<ContactData> before = appMngr.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withName("Oleg").withLastName("Petrov");
        appMngr.contact().modify(contact, 1);
        appMngr.goTo().homePage();
        Set<ContactData> after = appMngr.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before, after);
    }

    @Test
    public void testContactModificationByLowerUpdateBtn() {
        Set<ContactData> before = appMngr.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withName("Maria").withLastName("Petrova");
        appMngr.contact().modify(contact, 2);
        appMngr.goTo().homePage();
        Set<ContactData> after = appMngr.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before, after);
    }



}
