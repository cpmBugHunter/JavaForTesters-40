package ru.pft40.bugHunter.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft40.bugHunter.model.ContactData;

import java.util.Comparator;
import java.util.List;
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

        contact.withId(after.stream().mapToInt((c) -> contact.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);
    }

    @Test
    public void testContactCreationByLowerEnterBtn() {
        appMngr.goTo().homePage();
        List<ContactData> before = appMngr.contact().list();
        appMngr.contact().initCreation();
        ContactData contact = new ContactData().withName("Max").withLastName("Ivanov").withCompany("Some Company LTD")
                .withMobilePhone("+7(909)123-45-89").withEmail("madMax@mail.com");
        appMngr.contact().fillForm(contact);
        appMngr.contact().submitUserCreation(By.xpath("//*[@name=\"submit\"][2]")); //upper Enter btn
        appMngr.goTo().homePage(); //переход на главную для проверки создания контакта
        List<ContactData> after = appMngr.contact().list();
        Assert.assertEquals(after.size(), before.size() + 1);
    }

}
