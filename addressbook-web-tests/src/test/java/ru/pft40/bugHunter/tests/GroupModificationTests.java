package ru.pft40.bugHunter.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft40.bugHunter.model.GroupData;

import java.util.Set;


public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        appMngr.goTo().groupPage();
        if( appMngr.group().all().size() == 0) {
            appMngr.group().create(new GroupData().withName("GroupName").withHeader("Group Header")
                            .withFooter("Group Footer"), 1);
        }
    }

    @Test
    public void testGroupModificationByUpperUpdateBtn() {
        Set<GroupData> before = appMngr.group().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("ChangedGroupName")
                .withHeader("Header").withFooter("Footer");
        appMngr.group().modify(group, 1);
        Set<GroupData> after = appMngr.group().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedGroup);
        before.add(group);
        Assert.assertEquals(before, after);
    }

    @Test
    public void testGroupModificationByLowerUpdateBtn() {
        Set<GroupData> before = appMngr.group().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("ChangedGroupName")
                .withHeader("Header").withFooter("Footer");
        appMngr.group().modify(group, 2);
        Set<GroupData> after = appMngr.group().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedGroup);
        before.add(group);
        Assert.assertEquals(before, after);
    }
}
