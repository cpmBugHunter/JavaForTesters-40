package ru.pft40.bugHunter.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.pft40.bugHunter.model.GroupData;

/**
 * Created by BugHunter on 15.04.2017.
 */
public class GroupHelper extends HelperBase {

    public GroupHelper(FirefoxDriver wd) {
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
}