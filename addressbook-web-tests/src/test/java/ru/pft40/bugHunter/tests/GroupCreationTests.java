package ru.pft40.bugHunter.tests;

import org.testng.annotations.Test;
import ru.pft40.bugHunter.model.GroupData;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {

        appMngr.getNavigationHelper().goToGroupPage();
        appMngr.getGroupHelper().initGroupCreation();
        appMngr.getGroupHelper().fillGroupForm(new GroupData("FirstGroup", "Group Header", "Group Footer"));
        appMngr.getGroupHelper().submitGroupCreation();
        appMngr.getGroupHelper().returnToGroupPage();
    }

}
