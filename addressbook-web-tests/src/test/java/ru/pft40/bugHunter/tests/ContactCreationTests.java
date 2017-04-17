package ru.pft40.bugHunter.tests;

import org.testng.annotations.Test;
import ru.pft40.bugHunter.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        appMngr.getContactHelper().initContactCreation();
        appMngr.getContactHelper().fillContactForm(
                new ContactData("Max", "Ivanov", "madMax", "Some Company LTD", "+7(909)123-45-89", "madMax@mail.com"));
        appMngr.getContactHelper().submitUserCreation();
        appMngr.getNavigationHelper().goToHomePage(); //переход на главную для визуального контроля создания контакта
    }

}
