package ru.pft40.bugHunter.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft40.bugHunter.model.GroupData;

import java.util.Set;

public class GroupDeletionTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        appMngr.goTo().groupPage();
        if(appMngr.group().all().size() == 0) {
            appMngr.group().create(new GroupData().withName("GroupName").withHeader("Group Header")
                            .withFooter("Group Footer"), 1);
        }
        appMngr.goTo().groupPage();
    }

    @Test
    public void testGroupDeletionByUpperDeleteBtn() {
        Set<GroupData> before = appMngr.group().all();
        GroupData deletedGroup = before.iterator().next();
        appMngr.group().delete(deletedGroup, 1);
        Set<GroupData> after = appMngr.group().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletedGroup);
        Assert.assertEquals(before, after);
    }

    @Test
    public void testGroupDeletionByLowerDeleteBtn() {
        Set<GroupData> before = appMngr.group().all();
        GroupData deletedGroup = before.iterator().next();
        appMngr.group().delete(deletedGroup, 2);
        Set<GroupData> after = appMngr.group().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletedGroup);
        Assert.assertEquals(before, after);
    }
}
