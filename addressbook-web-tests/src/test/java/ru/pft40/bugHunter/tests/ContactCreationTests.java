package ru.pft40.bugHunter.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.pft40.bugHunter.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreationByUpperEnterBtn() {
        appMngr.getContactHelper().initContactCreation();
        appMngr.getContactHelper().fillContactForm(new ContactData("Max", "Ivanov", "Some Company LTD",
                    "+7(909)123-45-89", "madMax@mail.com"));
        appMngr.getContactHelper().submitUserCreation(By.xpath("//*[@name=\"submit\"][1]")); //upper Enter btn
        appMngr.getNavigationHelper().goToHomePage(); //переход на главную для проверки создания контакта
    }

    @Test
    public void testContactCreationByLowerEnterBtn() {
        appMngr.getContactHelper().initContactCreation();
        appMngr.getContactHelper().fillContactForm(new ContactData("Ivan", "Petrov", "Other Company LTD",
                "+7(909)123-45-89", "madIvan@mail.com"));
        appMngr.getContactHelper().submitUserCreation(By.xpath("//*[@name=\"submit\"][2]")); //lower Enter btn
        appMngr.getNavigationHelper().goToHomePage();
    }

}
