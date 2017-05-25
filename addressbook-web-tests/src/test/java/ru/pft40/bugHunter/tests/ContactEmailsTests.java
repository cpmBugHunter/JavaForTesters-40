package ru.pft40.bugHunter.tests;

import ru.pft40.bugHunter.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ContactEmailsTests {

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3()).stream()
                .filter((s) -> ! s.equals("")).collect(Collectors.joining("\n"));
    }
}
