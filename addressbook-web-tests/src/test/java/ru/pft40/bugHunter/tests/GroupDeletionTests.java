package ru.pft40.bugHunter.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft40.bugHunter.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        appMngr.goTo().groupPage();
        if(appMngr.group().list().size() == 0) {
            appMngr.group().create(new GroupData("GroupName", "Group Header", "Group Footer"), 1);
        }
        appMngr.goTo().groupPage();
    }

    @Test
    public void testGroupDeletionByUpperDeleteBtn() {
        List<GroupData> before = appMngr.group().list();
        int index = before.size() - 1;
        appMngr.group().delete(index, 1);
        List<GroupData> after = appMngr.group().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }

    @Test
    public void testGroupDeletionByLowerDeleteBtn() {
        List<GroupData> before = appMngr.group().list();
        int index = before.size() - 1;
        appMngr.group().delete(index, 2);
        List<GroupData> after = appMngr.group().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }
}
