package ru.pft40.bugHunter.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.pft40.bugHunter.model.GroupData;


public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getGroupName());
        type(By.name("group_header"), groupData.getGroupHeader());
        type(By.name("group_footer"), groupData.getGroupFooter());
    }

    public void initGroupCreation(By locator) {
        click(locator);
    }

    public void deleteSelectedGroup(By locator) {
        click(locator);
    }

    public void selectGroup(By locator) {
        click(locator);
    }

    public void initGroupModification(By locator) {
        click(locator);
    }

    public boolean isThereAgroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public void createGroup(GroupData group, By locator) {
        initGroupCreation(locator); //which button click on
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupPage();
    }

    public int getGroupCount() {
        return wd.findElements(By.name("selected[]")).size();
    }
}
