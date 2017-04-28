package ru.pft40.bugHunter.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft40.bugHunter.model.GroupData;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreationByUpperCreateBtn() {

        appMngr.getNavigationHelper().goToGroupPage();
        int before = appMngr.getGroupHelper().getGroupCount();
        appMngr.getGroupHelper().createGroup(new GroupData("GroupName", "Group Header", "Group Footer"),
                By.xpath("//*[@id=\"content\"]/form/input[1]"));
        int after = appMngr.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before + 1);
    }

    @Test
    public void testGroupCreationByLowerCreateBtn() {

        appMngr.getNavigationHelper().goToGroupPage();
        int before = appMngr.getGroupHelper().getGroupCount();
        appMngr.getGroupHelper().createGroup(new GroupData("New GroupName", "Group Header", "Group Footer"),
                By.xpath("//*[@id=\"content\"]/form/input[4]"));
        int after = appMngr.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before + 1);
    }

}
