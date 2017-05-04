package ru.pft40.bugHunter.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft40.bugHunter.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreationByUpperEnterBtn() {
        appMngr.goTo().goToHomePage();
        List<ContactData> before = appMngr.getContactHelper().getContactsList();
        appMngr.getContactHelper().initContactCreation();
        ContactData contact = new ContactData("Max", "Ivanov", "Some Company LTD",
                "+7(909)123-45-89", "madMax@mail.com");
        appMngr.getContactHelper().fillContactForm(contact);
        appMngr.getContactHelper().submitUserCreation(By.xpath("//*[@name=\"submit\"][1]")); //upper Enter btn
        appMngr.goTo().goToHomePage(); //переход на главную для проверки создания контакта
        List<ContactData> after = appMngr.getContactHelper().getContactsList();
        Assert.assertEquals(after.size(), before.size() + 1);


        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

    @Test
    public void testContactCreationByLowerEnterBtn() {
        appMngr.goTo().goToHomePage();
        List<ContactData> before = appMngr.getContactHelper().getContactsList();
        appMngr.getContactHelper().initContactCreation();
        ContactData contact = new ContactData("Max", "Ivanov", "Some Company LTD",
                "+7(909)123-45-89", "madMax@mail.com");
        appMngr.getContactHelper().fillContactForm(contact);
        appMngr.getContactHelper().submitUserCreation(By.xpath("//*[@name=\"submit\"][2]")); //lower Enter btn
        appMngr.goTo().goToHomePage(); //переход на главную для проверки создания контакта
        List<ContactData> after = appMngr.getContactHelper().getContactsList();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
