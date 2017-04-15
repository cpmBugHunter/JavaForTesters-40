package ru.pft40.bugHunter.tests;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase{

    @Test
    public void testGroupDeletion() {
        appMngr.getNavigationHelper().goToGroupPage();
        appMngr.getGroupHelper().selectGroup();
        appMngr.getGroupHelper().deleteSelectedGroup();
        appMngr.getGroupHelper().returnToGroupPage();
    }
}
