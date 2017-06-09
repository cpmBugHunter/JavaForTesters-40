package ru.pft40.bugHunter.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft40.bugHunter.generators.ContactDataGenerator;
import ru.pft40.bugHunter.model.ContactData;
import ru.pft40.bugHunter.model.Contacts;
import ru.pft40.bugHunter.model.GroupData;
import ru.pft40.bugHunter.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactModificationTests extends TestBase {


    @BeforeMethod
    public void ensurePreconditions() {
        if (appMngr.db().contacts().size() == 0) {
            appMngr.goTo().homePage();
            appMngr.contact().create(new ContactData().withName("Max").withLastName("Ivanov"), 1);
        }
        appMngr.goTo().homePage();
    }

    @Test
    public void testContactModification_GetContactsListFromDB() {
        Contacts before = appMngr.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withName(String.format("Name%s", System.currentTimeMillis()))
                .withLastName(String.format("LastName%s", System.currentTimeMillis()))
                .withAddress(modifiedContact.getAddress())
                .withEmail(modifiedContact.getEmail()).withEmail2(modifiedContact.getEmail2())
                .withEmail3(modifiedContact.getEmail3())
                .withHomePhone(modifiedContact.getHomePhone())
                .withMobilePhone(ContactDataGenerator.generatePhone(modifiedContact.getId()))
                .withWorkPhone(modifiedContact.getWorkPhone());
        appMngr.contact().modify(contact, 1);
        appMngr.goTo().homePage();
        Contacts after = appMngr.db().contacts();

        //assertThat(after.size(), equalTo(before.size()));
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        verifyContactListInUI();
    }

    @Test
    public void testAddContactToGroup() {
        Contacts allContacts = appMngr.db().contacts();
        Groups allGroups = appMngr.db().groups();
        ContactData contact = pickContactForGroupAdding(allContacts, allGroups);
        Groups groupsBefore = contact.getGroups();
        GroupData group = pickGroupForAddingTo(allGroups, groupsBefore);
        appMngr.goTo().homePage();
        appMngr.contact().addToGroup(contact, group);
        Groups groupsAfter = appMngr.db().getContact(contact.getId()).getGroups();

        assertThat(groupsAfter, equalTo(groupsBefore.withAdded(group)));
    }

    @Test
    public void testRemoveContactFromGroup() {
        Contacts allContacts = appMngr.db().contacts();
        ContactData contact = pickContactForGroupRemoving(allContacts);
        Groups before = contact.getGroups();
        GroupData group = contact.getGroups().iterator().next();
        appMngr.goTo().homePage();
        appMngr.contact().removeFromGroup(contact, group);
        Groups after = appMngr.db().getContact(contact.getId()).getGroups();

        assertThat(after, equalTo(before.without(group)));
    }

    @Test(enabled = false)
    public void testContactModification_GetContactsListFromUI() {
        Contacts before = appMngr.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withName("Maria").withLastName("Petrova");
        appMngr.contact().modify(contact, 2);
        appMngr.goTo().homePage();
        Contacts after = appMngr.contact().all();

        assertThat(after.size(), equalTo(before.size()));
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }

    private ContactData pickContactForGroupRemoving(Contacts allContacts) {
        Contacts available = allContacts;
        ContactData contact = available.iterator().next();
        while (contact.getGroups().isEmpty()) {
            available.remove(contact);
            if (!allContacts.isEmpty()) {
                contact = available.iterator().next();
            } else {
                Contacts newContacts = allContacts;
                contact = newContacts.iterator().next();
                Groups allGroups = appMngr.db().groups();
                appMngr.contact().addToGroup(contact, allGroups.iterator().next());
            }
        }
        return contact;
    }

    private ContactData pickContactForGroupAdding(Contacts contacts, Groups allGroups) {
        ContactData contact = contacts.iterator().next();
        Contacts available = contacts;
        //try to exclude contacts which are all groups members
        while (contact.getGroups().size() == allGroups.size()){
            available.remove(contact);
            if (!available.isEmpty()) {
                contact = available.iterator().next();
            }
            else {
                ContactData newContact = new ContactData().withName("Max").withLastName("Ivanov");
                appMngr.goTo().homePage();
                appMngr.contact().create(newContact, 1);
                available = appMngr.db().contacts();
                contact = available.iterator().next();
            }
        }
        return contact;
    }

    private GroupData pickGroupForAddingTo(Groups groups, Groups contactInGroups) {
        GroupData group;
        if (contactInGroups.isEmpty()) {
            group = groups.iterator().next();
        } else {
            for (GroupData gr : contactInGroups) {
                groups.remove(gr);
            }
            group = groups.iterator().next();
        }
        return group;
    }


}
