package ru.pft40.bugHunter.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase{

    @Test
    public void testGroupDeletionByUpperDeleteBtn() {
        appMngr.getNavigationHelper().goToGroupPage();
        appMngr.getGroupHelper().selectGroup(By.xpath("//span[1]/input[@type=\"checkbox\"]"));
        appMngr.getGroupHelper().deleteSelectedGroup(By.xpath("//*[@id=\"content\"]/form/input[2]")); //upper delete btn
    }

    @Test
    public void testGroupDeletionByLowerDeleteBtn() {
        appMngr.getNavigationHelper().goToGroupPage();
        appMngr.getGroupHelper().selectGroup(By.xpath("//span[1]/input[@type=\"checkbox\"]"));
        appMngr.getGroupHelper().deleteSelectedGroup(By.xpath("//*[@id=\"content\"]/form/input[5]")); //lower delete btn
    }
}
