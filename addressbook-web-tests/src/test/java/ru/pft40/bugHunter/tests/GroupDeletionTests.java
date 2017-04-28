package ru.pft40.bugHunter.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft40.bugHunter.model.GroupData;

public class GroupDeletionTests extends TestBase{

    @Test
    public void testGroupDeletionByUpperDeleteBtn() throws Exception {
        appMngr.getNavigationHelper().goToGroupPage();
        int before = appMngr.getGroupHelper().getGroupCount();
        if(! appMngr.getGroupHelper().isThereAgroup()) {
            appMngr.getGroupHelper().createGroup(new GroupData("GroupName", "Group Header", "Group Footer"),
                    By.xpath("//*[@id=\"content\"]/form/input[1]"));
        }
        appMngr.getGroupHelper().selectGroup(5);
        appMngr.getGroupHelper().deleteSelectedGroup(By.xpath("//*[@id=\"content\"]/form/input[2]")); //upper delete btn
        appMngr.getGroupHelper().returnToGroupPage();
        int after = appMngr.getGroupHelper().getGroupCount();
        if (before == 0) {
            Assert.assertEquals(after, before);
        }
        else {
            Assert.assertEquals(after, before - 1);
        }
    }

    @Test
    public void testGroupDeletionByLowerDeleteBtn() throws Exception {
        appMngr.getNavigationHelper().goToGroupPage();
        int before = appMngr.getGroupHelper().getGroupCount();
        if(! appMngr.getGroupHelper().isThereAgroup()) {
            appMngr.getGroupHelper().createGroup(new GroupData("GroupName", "Group Header", "Group Footer"),
                    By.xpath("//*[@id=\"content\"]/form/input[1]"));
        }
        appMngr.getGroupHelper().selectGroup(before - 1);
        appMngr.getGroupHelper().deleteSelectedGroup(By.xpath("//*[@id=\"content\"]/form/input[5]")); //upper delete btn
        appMngr.getGroupHelper().returnToGroupPage();
        int after = appMngr.getGroupHelper().getGroupCount();
        if (before == 0) {
            Assert.assertEquals(after, before);
        }
        else {
            Assert.assertEquals(after, before - 1);
        }
    }
}
