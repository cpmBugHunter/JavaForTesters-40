package ru.pft40.bugHunter.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.pft40.bugHunter.model.GroupData;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreationByUpperCreateBtn() {

        appMngr.getNavigationHelper().goToGroupPage();
        appMngr.getGroupHelper().initGroupCreation(By.xpath("//*[@id=\"content\"]/form/input[1]")); //upper Create btn
        appMngr.getGroupHelper().fillGroupForm(new GroupData("GroupName", "Group Header", "Group Footer"));
        appMngr.getGroupHelper().submitGroupCreation();
        appMngr.getGroupHelper().returnToGroupPage();
    }

    @Test
    public void testGroupCreationByLowerCreateBtn() {

        appMngr.getNavigationHelper().goToGroupPage();
        appMngr.getGroupHelper().initGroupCreation(By.xpath("//*[@id=\"content\"]/form/input[4]")); //lower Create btn
        appMngr.getGroupHelper().fillGroupForm(new GroupData("New GroupName", null, null));
        appMngr.getGroupHelper().submitGroupCreation();
        appMngr.getGroupHelper().returnToGroupPage();
    }

}
