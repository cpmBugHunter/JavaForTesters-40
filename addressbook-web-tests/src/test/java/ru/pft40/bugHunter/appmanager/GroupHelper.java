package ru.pft40.bugHunter.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.pft40.bugHunter.model.GroupData;

import java.util.ArrayList;
import java.util.List;


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
        wd.findElement(locator).click();
    }

    public void selectGroup(int index) {
        List<WebElement> groups = wd.findElements(By.name("selected[]"));
        if (index >= 0 && index < groups.size()) {
            wd.findElements(By.name("selected[]")).get(index).click();
        } else if (index < 0){
            wd.findElements(By.name("selected[]")).get(0).click();
        } else {
            wd.findElements(By.name("selected[]")).get(groups.size() - 1).click();
        }
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

    public List<GroupData> getGroupList() {
        List<GroupData> groups = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for(WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            GroupData group = new GroupData(id, name, null, null);
            groups.add(group);
        }
        return groups;
    }
}
