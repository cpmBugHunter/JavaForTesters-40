package ru.pft40.bugHunter;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {

        goToGroupPage();
        initGroupCreation();
        fillGroupForm(new GroupData("FirstGroup", "Group Header", "Group Footer"));
        submitGroupCreation();
        returnToGroupPage();
    }

}
