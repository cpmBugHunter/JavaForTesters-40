package ru.pft40.bugHunter.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.pft40.bugHunter.model.GroupData;

public class GroupDeletionTests extends TestBase{

    @Test
    public void testGroupDeletionByUpperDeleteBtn() {
        appMngr.getNavigationHelper().goToGroupPage();
        if(! appMngr.getGroupHelper().isThereAgroup()) {
            appMngr.getGroupHelper().createGroup(new GroupData("GroupName", "Group Header", "Group Footer"),
                    By.xpath("//*[@id=\"content\"]/form/input[1]"));
        }
        appMngr.getGroupHelper().selectGroup(By.xpath("//span[1]/input[@type=\"checkbox\"]"));
        appMngr.getGroupHelper().deleteSelectedGroup(By.xpath("//*[@id=\"content\"]/form/input[2]")); //upper delete btn
    }

    @Test
    public void testGroupDeletionByLowerDeleteBtn() {
        appMngr.getNavigationHelper().goToGroupPage();
        if(! appMngr.getGroupHelper().isThereAgroup()) {
            appMngr.getGroupHelper().createGroup(new GroupData("GroupName", null, null),
                    By.xpath("//*[@id=\"content\"]/form/input[1]"));
        }
        appMngr.getGroupHelper().selectGroup(By.xpath("//span[1]/input[@type=\"checkbox\"]"));
        appMngr.getGroupHelper().deleteSelectedGroup(By.xpath("//*[@id=\"content\"]/form/input[5]")); //lower delete btn
    }
}
