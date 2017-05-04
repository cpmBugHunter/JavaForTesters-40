package ru.pft40.bugHunter.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft40.bugHunter.model.GroupData;

import java.util.Comparator;
import java.util.List;


public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        appMngr.goTo().groupPage();
        if( appMngr.group().list().size() == 0) {
            appMngr.group().create(new GroupData("GroupName", "Group Header", "Group Footer"), 1);
        }
    }

    @Test
    public void testGroupModificationByUpperUpdateBtn() {
        List<GroupData> before = appMngr.group().list();
        int index = before.size() - 1;
        GroupData group = new GroupData(before.get(index).getId(), "ChangedGroupName", null, null);
        appMngr.group().modify(index, group, 1);
        List<GroupData> after = appMngr.group().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

    @Test
    public void testGroupModificationByLowerUpdateBtn() {
        List<GroupData> before = appMngr.group().list();
        int index = before.size() - 1;
        GroupData group = new GroupData(before.get(index).getId(), "ChangedGroupName", null, null);
        appMngr.group().modify(index, group, 2);
        List<GroupData> after = appMngr.group().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
