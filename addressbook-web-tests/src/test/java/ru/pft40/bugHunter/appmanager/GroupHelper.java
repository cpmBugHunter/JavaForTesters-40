package ru.pft40.bugHunter.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.pft40.bugHunter.model.GroupData;
import ru.pft40.bugHunter.model.Groups;

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
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void initCreation(int btn) {
        String xPath = String.format("//*[@name=\"new\"][%d]", btn); // may be 1 or 2 (upper or lower button)
        click(By.xpath(xPath));
    }

    public void create(GroupData group, int btn) {
        initCreation(btn); //which button click on
        fillForm(group);
        submitCreation();
        groupCache = null;
        returnToGroupPage();
    }

    public void deleteSelected(int btn) {
        String xPath = String.format("//*[@name=\"delete\"][%d]", btn); // may be 1 or 2 (upper or lower button)
        click(By.xpath(xPath));
    }

    public void selectGroup(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    private void selectById(int id) {
        wd.findElement(By.cssSelector(String.format("input[value='%s']", id))).click();
    }

    public void modify(GroupData group, int btn) { // btn may be 1 or 2 (upper or lower button)
        selectById(group.getId());
        initModification(btn); // Edit btn
        type(By.xpath("//*[@name=\"group_name\"]"), group.getName());
        click(By.xpath("//*[@value=\"Update\"]"));
        groupCache = null;
        returnToGroupPage();
    }

    public void modify(int index, GroupData group, int btn) { // btn may be 1 or 2 (upper or lower button)
        selectGroup(index);
        initModification(btn); // Edit btn
        type(By.xpath("//*[@name=\"group_name\"]"), group.getName());
        click(By.xpath("//*[@value=\"Update\"]"));
        returnToGroupPage();
    }

    public void delete(GroupData group, int btn) {
        selectById(group.getId());
        deleteSelected(btn);
        groupCache = null;
        returnToGroupPage();
    }

    public void initModification(int btn) {
        String xPath = String.format("//*[@name=\"edit\"][%d]", btn); // may be 1 or 2 (upper or lower button)
        click(By.xpath(xPath));
    }

    public boolean isThereAgroup() {
        return isElementPresent(By.name("selected[]"));
    }

    private Groups groupCache = null;

    public Groups all() {
        if(groupCache != null) {
            return new Groups(groupCache);
        }
        groupCache = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for(WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groupCache.add(new GroupData().withId(id).withName(name));
        }
        return new Groups(groupCache);
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }
}
