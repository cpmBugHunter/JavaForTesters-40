package ru.pft40.bugHunter.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.pft40.bugHunter.model.GroupData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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

    public void deleteSelected(int btn) {
        String xPath = String.format("//*[@name=\"delete\"][%d]", btn); // may be 1 or 2 (upper or lower button)
        click(By.xpath(xPath));
    }

    private void selectById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id +"']")).click();
    }


    public void modify(GroupData group, int btn) { // btn may be 1 or 2 (upper or lower button)
        selectById(group.getId());
        initModification(btn); // Edit btn
        type(By.xpath("//*[@name=\"group_name\"]"), group.getName());
    }

    public void selectGroup(int index) {
            wd.findElements(By.name("selected[]")).get(index).click();
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
        returnToGroupPage();
    }

    public void initModification(int btn) {
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


    public Set<GroupData> all() {
        Set<GroupData> groups = new HashSet<>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for(WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groups.add(new GroupData().withId(id).withName(name));
        }
        return groups;
    }

    public List<GroupData> list() {
        List<GroupData> groups = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for(WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groups.add(new GroupData().withId(id).withName(name));
        }
        return groups;
    }
}
