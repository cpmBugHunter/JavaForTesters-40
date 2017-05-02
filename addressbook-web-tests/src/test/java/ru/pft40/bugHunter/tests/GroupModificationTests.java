package ru.pft40.bugHunter.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft40.bugHunter.model.GroupData;

import java.util.HashSet;
import java.util.List;


public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModificationByUpperUpdateBtn() {
        appMngr.getNavigationHelper().goToGroupPage();
        precondition();
        List<GroupData> before = appMngr.getGroupHelper().getGroupList();
        appMngr.getGroupHelper().selectGroup(before.size() - 1);
        appMngr.getGroupHelper().initGroupModification(By.xpath("//*[@id=\"content\"]/form/input[3]")); //upper Edit btn
        GroupData group = new GroupData(before.get(before.size() - 1).getId(), "ChangedGroupName", null, null);
        appMngr.getGroupHelper().type(By.xpath("//*[@name=\"group_name\"]"), group.getGroupName());
        appMngr.getGroupHelper().click(By.xpath("//*[@value=\"Update\"]"));
        appMngr.getGroupHelper().returnToGroupPage();
        List<GroupData> after = appMngr.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(group);
        Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
    }

    @Test
    public void testGroupModificationByLowerUpdateBtn() {
        appMngr.getNavigationHelper().goToGroupPage();
        precondition();
        List<GroupData> before = appMngr.getGroupHelper().getGroupList();
        appMngr.getGroupHelper().selectGroup(before.size() - 1);
        appMngr.getGroupHelper().initGroupModification(By.xpath("//*[@id=\"content\"]/form/input[6]")); //lower Edit btn
        GroupData group = new GroupData(before.get(before.size() - 1).getId(), "ChangedGroupName", null, null);
        appMngr.getGroupHelper().type(By.xpath("//*[@name=\"group_name\"]"), group.getGroupName());
        appMngr.getGroupHelper().click(By.xpath("//*[@value=\"Update\"]"));
        appMngr.getGroupHelper().returnToGroupPage();
        List<GroupData> after = appMngr.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(group);
        Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
    }

    private void precondition() {
        if(! appMngr.getGroupHelper().isThereAgroup()) {
            appMngr.getGroupHelper().createGroup(new GroupData("GroupName", "Group Header", "Group Footer"),
                    By.xpath("//*[@id=\"content\"]/form/input[1]"));
        }
    }
}
