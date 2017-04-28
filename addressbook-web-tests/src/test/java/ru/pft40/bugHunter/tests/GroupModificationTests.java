package ru.pft40.bugHunter.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.pft40.bugHunter.model.GroupData;


public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModificationByUpperUpdateBtn() throws Exception {
        appMngr.getNavigationHelper().goToGroupPage();
        if(! appMngr.getGroupHelper().isThereAgroup()) {
            appMngr.getGroupHelper().createGroup(new GroupData("GroupName", "Group Header", "Group Footer"),
                    By.xpath("//*[@id=\"content\"]/form/input[1]"));
        }
        appMngr.getGroupHelper().selectGroup(By.xpath("//span[1]/input[@type=\"checkbox\"]"));
        appMngr.getGroupHelper().initGroupModification(By.xpath("//*[@id=\"content\"]/form/input[3]")); //upper Edit btn
        appMngr.getGroupHelper().type(By.xpath("//*[@name=\"group_name\"]"), "New group name");
        appMngr.getGroupHelper().click(By.xpath("//*[@value=\"Update\"]"));
    }

    @Test
    public void testGroupModificationByLowerUpdateBtn() throws Exception {
        appMngr.getNavigationHelper().goToGroupPage();
        if(! appMngr.getGroupHelper().isThereAgroup()) {
            appMngr.getGroupHelper().createGroup(new GroupData("GroupName", "Group Header", "Group Footer"),
                    By.xpath("//*[@id=\"content\"]/form/input[1]"));
        }
        appMngr.getGroupHelper().selectGroup(By.xpath("//span[1]/input[@type=\"checkbox\"]"));
        appMngr.getGroupHelper().initGroupModification(By.xpath("//*[@id=\"content\"]/form/input[6]")); //lower Edit btn
        appMngr.getGroupHelper().type(By.xpath("//*[@name=\"group_name\"]"), "New group name");
        appMngr.getGroupHelper().click(By.xpath("//*[@value=\"Update\"]"));
    }
}
