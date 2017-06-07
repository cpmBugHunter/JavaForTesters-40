package ru.pft40.bugHunter.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.pft40.bugHunter.model.ContactData;
import ru.pft40.bugHunter.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContactsJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
            StringBuilder json = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                json.append(line);
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json.toString(), new TypeToken<List<ContactData>>() {}.getType());
            return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
        }
    }

    @Test(dataProvider = "validContactsJson")
    public void testContactCreation_GetContactsListfromDB(ContactData contact) {
        appMngr.goTo().homePage();
        Contacts before = appMngr.db().contacts();
        appMngr.contact().create(contact, 1);
        appMngr.goTo().homePage();
        Contacts after = appMngr.db().contacts();

        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(before
                .withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
        verifyContactListInUI();
    }

    @Test(enabled = false)
    public void testContactCreationWithPhotoAttach() {
        appMngr.goTo().homePage();
        Contacts before = appMngr.contact().all();
        File photo = new File("src/test/resources/photo.jpg");
        ContactData contact = new ContactData().withName("Max").withLastName("Ivanov").withPhoto(photo);
        appMngr.contact().createWithAttach(contact, 2);
        appMngr.goTo().homePage();
        Contacts after = appMngr.contact().all();

        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(before
                .withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

}
