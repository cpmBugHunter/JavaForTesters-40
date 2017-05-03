package ru.pft40.bugHunter.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft40.bugHunter.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreationByUpperCreateBtn() {

        appMngr.getNavigationHelper().goToGroupPage();
        List<GroupData> before = appMngr.getGroupHelper().getGroupList();
        GroupData group = new GroupData("GroupName", null, null);
        appMngr.getGroupHelper().createGroup(group,
                By.xpath("//*[@id=\"content\"]/form/input[1]"));
        List<GroupData> after = appMngr.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

    @Test
    public void testGroupCreationByLowerCreateBtn() {

        appMngr.getNavigationHelper().goToGroupPage();
        List<GroupData> before = appMngr.getGroupHelper().getGroupList();
        GroupData group = new GroupData("GroupName", null, null);
        appMngr.getGroupHelper().createGroup(group,
                By.xpath("//*[@id=\"content\"]/form/input[4]"));
        List<GroupData> after = appMngr.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() + 1);

        group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(group);
        Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
    }

}
