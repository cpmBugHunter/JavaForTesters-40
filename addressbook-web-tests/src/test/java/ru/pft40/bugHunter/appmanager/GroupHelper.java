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

    public void submitCreation() {
        click(By.name("submit"));
    }

    public void fillForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getGroupName());
        type(By.name("group_header"), groupData.getGroupHeader());
        type(By.name("group_footer"), groupData.getGroupFooter());
    }

    public void initCreation(int btn) {
        String xPath = String.format("//*[@name=\"new\"][%d]", btn); // may be 1 or 2 (upper or lower button)
        click(By.xpath(xPath));
    }

    public void deleteSelected(int btn) {
        String xPath = String.format("//*[@name=\"delete\"][%d]", btn); // may be 1 or 2 (upper or lower button)
        click(By.xpath(xPath));
    }

    public void selectGroup(By locator) {
        wd.findElement(locator).click();
    }

    public void selectGroup(int index) {
            wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void modify(int index, GroupData group, int btn) { // btn may be 1 or 2 (upper or lower button)
        selectGroup(index);
        initGroupModification(btn); // Edit btn
        type(By.xpath("//*[@name=\"group_name\"]"), group.getGroupName());
        click(By.xpath("//*[@value=\"Update\"]"));
        returnToGroupPage();
    }

    public void delete(int index, int btn) {
        selectGroup(index);
        deleteSelected(btn);
        returnToGroupPage();
    }

    public void initGroupModification(int btn) {
        String xPath = String.format("//*[@name=\"edit\"][%d]", btn); // may be 1 or 2 (upper or lower button)
        click(By.xpath(xPath));
    }

    public boolean isThereAgroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public void create(GroupData group, int btn) {
        initCreation(btn); //which button click on
        fillForm(group);
        submitCreation();
        returnToGroupPage();
    }

    public List<GroupData> list() {
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
