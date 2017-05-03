package ru.pft40.bugHunter.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft40.bugHunter.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase{

    @Test
    public void testGroupDeletionByUpperDeleteBtn() {
        appMngr.getNavigationHelper().goToGroupPage();
        precondition();
        List<GroupData> before = appMngr.getGroupHelper().getGroupList();
        appMngr.getGroupHelper().selectGroup(before.size() - 1);
        appMngr.getGroupHelper().deleteSelectedGroup(By.xpath("//*[@id=\"content\"]/form/input[2]")); //upper delete btn
        appMngr.getGroupHelper().returnToGroupPage();
        List<GroupData> after = appMngr.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }

    @Test
    public void testGroupDeletionByLowerDeleteBtn() {
        appMngr.getNavigationHelper().goToGroupPage();
        precondition();
        List<GroupData> before = appMngr.getGroupHelper().getGroupList();
        appMngr.getGroupHelper().selectGroup(before.size() - 1);
        appMngr.getGroupHelper().deleteSelectedGroup(By.xpath("//*[@id=\"content\"]/form/input[5]")); //upper delete btn
        appMngr.getGroupHelper().returnToGroupPage();
        List<GroupData> after = appMngr.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }

    private void precondition() {
        if(! appMngr.getGroupHelper().isThereAgroup()) {
            appMngr.getGroupHelper().createGroup(new GroupData("GroupName", "Group Header", "Group Footer"),
                    By.xpath("//*[@id=\"content\"]/form/input[1]"));
        }
    }
}
