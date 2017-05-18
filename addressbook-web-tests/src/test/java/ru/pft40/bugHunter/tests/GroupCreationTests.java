package ru.pft40.bugHunter.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft40.bugHunter.model.GroupData;

import java.util.Set;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreationByUpperCreateBtn() {

        appMngr.goTo().groupPage();
        Set<GroupData> before = appMngr.group().all();
        GroupData group = new GroupData().withName("GroupName");
        appMngr.group().create(group, 1);
        Set<GroupData> after = appMngr.group().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(group);
        Assert.assertEquals(before, after);
    }

    @Test
    public void testGroupCreationByLowerCreateBtn() {

        appMngr.goTo().groupPage();
        Set<GroupData> before = appMngr.group().all();
        GroupData group = new GroupData().withName("GroupName");
        appMngr.group().create(group, 2);
        Set<GroupData> after = appMngr.group().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(group);
        Assert.assertEquals(before, after);
    }
}
