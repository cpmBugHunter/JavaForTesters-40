package ru.pft40.bugHunter.tests;

import org.testng.annotations.Test;
import ru.pft40.bugHunter.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailsTests extends TestBase{


    //Загрузка списка контактов из БД реализована в тесте ContactDetailTest (где проверяется всё полностью)
    @Test(enabled = false)
    public void testContactEmailsComparison() {
        appMngr.goTo().homePage();
        ContactData contact = appMngr.contact().all().iterator().next();
        ContactData contactDataFromEditForm = appMngr.contact().contactDataFromEditForm(contact);

        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactDataFromEditForm)));
    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3()).stream()
                .filter((s) -> ! s.equals("")).collect(Collectors.joining("\n"));
    }

}
