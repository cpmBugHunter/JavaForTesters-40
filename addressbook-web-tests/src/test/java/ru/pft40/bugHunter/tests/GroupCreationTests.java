package ru.pft40.bugHunter.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft40.bugHunter.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreationByUpperCreateBtn() {

        appMngr.goTo().groupPage();
        List<GroupData> before = appMngr.group().list();
        GroupData group = new GroupData("GroupName", null, null);
        appMngr.group().create(group, 1);
        List<GroupData> after = appMngr.group().list();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

    @Test
    public void testGroupCreationByLowerCreateBtn() {

        appMngr.goTo().groupPage();
        List<GroupData> before = appMngr.group().list();
        GroupData group = new GroupData("GroupName", null, null);
        appMngr.group().create(group, 2);
        List<GroupData> after = appMngr.group().list();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(group);
        Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
