package ru.pft40.bugHunter.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft40.bugHunter.model.ContactData;

import java.util.Set;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreationByUpperEnterBtn() {
        appMngr.goTo().homePage();
        Set<ContactData> before = appMngr.contact().all();
        ContactData contact = new ContactData().withName("Max").withLastName("Ivanov");
        appMngr.contact().create(contact, 1);
        appMngr.goTo().homePage();
        Set<ContactData> after = appMngr.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);
    }

    @Test
    public void testContactCreationByLowerEnterBtn() {
        appMngr.goTo().homePage();
        Set<ContactData> before = appMngr.contact().all();
        ContactData contact = new ContactData().withName("Max").withLastName("Ivanov");
        appMngr.contact().create(contact, 2);
        appMngr.goTo().homePage();
        Set<ContactData> after = appMngr.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);
    }

}
