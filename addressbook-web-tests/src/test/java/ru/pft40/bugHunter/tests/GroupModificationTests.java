package ru.pft40.bugHunter.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft40.bugHunter.model.GroupData;
import ru.pft40.bugHunter.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


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
        Groups before = appMngr.group().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("ChangedGroupName")
                .withHeader("Header").withFooter("Footer");
        appMngr.group().modify(group, 1);
        Groups after = appMngr.group().all();

        assertThat(after.size(), equalTo(before.size()));
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
    }

    @Test
    public void testGroupModificationByLowerUpdateBtn() {
        Groups before = appMngr.group().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("ChangedGroupName")
                .withHeader("Header").withFooter("Footer");
        appMngr.group().modify(group, 2);
        Groups after = appMngr.group().all();

        assertThat(after.size(), equalTo(before.size()));
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
    }
}
