package ru.pft40.bugHunter;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase{

    @Test
    public void testGroupDeletion() {
        goToGroupPage();
        selectGroup();
        deleteSelectedGroup();
        returnToGroupPage();
    }
}
