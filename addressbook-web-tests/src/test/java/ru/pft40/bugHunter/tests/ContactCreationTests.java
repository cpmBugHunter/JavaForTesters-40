package ru.pft40.bugHunter.tests;

import org.testng.annotations.Test;
import ru.pft40.bugHunter.model.ContactData;
import ru.pft40.bugHunter.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreationByUpperEnterBtn() {
        appMngr.goTo().homePage();
        Contacts before = appMngr.contact().all();
        ContactData contact = new ContactData().withName("Max").withLastName("Ivanov").withAddress("address")
                .withHomePhone("111").withMobilePhone("222").withWorkPhone("333").withEmail("email@mail.com")
                .withEmail2("email2@mail.com").withEmail3("email3@mail.com");
        appMngr.contact().create(contact, 1);
        appMngr.goTo().homePage();
        Contacts after = appMngr.contact().all();

        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(before
                .withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

    @Test
    public void testContactCreationByLowerEnterBtn() {
        appMngr.goTo().homePage();
        Contacts before = appMngr.contact().all();
        ContactData contact = new ContactData().withName("Max").withLastName("Ivanov");
        appMngr.contact().create(contact, 2);
        appMngr.goTo().homePage();
        Contacts after = appMngr.contact().all();

        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(before
                .withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

}
