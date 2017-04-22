package ru.pft40.bugHunter.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.pft40.bugHunter.model.GroupData;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreationByUpperCreateBtn() {

        appMngr.getNavigationHelper().goToGroupPage();
        appMngr.getGroupHelper().createGroup(new GroupData("GroupName", "Group Header", "Group Footer"),
                By.xpath("//*[@id=\"content\"]/form/input[1]"));
    }

    @Test
    public void testGroupCreationByLowerCreateBtn() {

        appMngr.getNavigationHelper().goToGroupPage();
        appMngr.getGroupHelper().createGroup(new GroupData("New GroupName", "Group Header", "Group Footer"),
                By.xpath("//*[@id=\"content\"]/form/input[4]"));
    }

}
