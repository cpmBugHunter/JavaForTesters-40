package ru.pft40.bugHunter.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft40.bugHunter.model.GroupData;

import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreationByUpperCreateBtn() {

        appMngr.getNavigationHelper().goToGroupPage();
        List<GroupData> before = appMngr.getGroupHelper().getGroupList();
        appMngr.getGroupHelper().createGroup(new GroupData("GroupName", "Group Header", "Group Footer"),
                By.xpath("//*[@id=\"content\"]/form/input[1]"));
        List<GroupData> after = appMngr.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() + 1);
    }

    @Test
    public void testGroupCreationByLowerCreateBtn() {

        appMngr.getNavigationHelper().goToGroupPage();
        List<GroupData> before = appMngr.getGroupHelper().getGroupList();
        appMngr.getGroupHelper().createGroup(new GroupData("New GroupName", "Group Header", "Group Footer"),
                By.xpath("//*[@id=\"content\"]/form/input[4]"));
        List<GroupData> after = appMngr.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() + 1);
    }

}
