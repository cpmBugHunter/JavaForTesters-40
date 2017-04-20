package ru.pft40.bugHunter.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

/**
 * Created by BugHunter on 17.04.2017.
 */
public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModificationByUpperUpdateBtn() throws Exception {
        appMngr.getNavigationHelper().goToGroupPage();
        appMngr.getGroupHelper().selectGroup(By.xpath("//span[1]/input[@type=\"checkbox\"]"));
        appMngr.getGroupHelper().initGroupModification(By.xpath("//*[@id=\"content\"]/form/input[3]")); //upper Edit btn
        appMngr.getGroupHelper().type(By.xpath("//*[@name=\"group_name\"]"), "New group name");
        appMngr.getGroupHelper().click(By.xpath("//*[@value=\"Update\"]"));
    }

    @Test
    public void testGroupModificationByLowerUpdateBtn() throws Exception {
        appMngr.getNavigationHelper().goToGroupPage();
        appMngr.getGroupHelper().selectGroup(By.xpath("//span[1]/input[@type=\"checkbox\"]"));
        appMngr.getGroupHelper().initGroupModification(By.xpath("//*[@id=\"content\"]/form/input[6]")); //lower Edit btn
        appMngr.getGroupHelper().type(By.xpath("//*[@name=\"group_name\"]"), "New group name");
        appMngr.getGroupHelper().click(By.xpath("//*[@value=\"Update\"]"));
    }
}