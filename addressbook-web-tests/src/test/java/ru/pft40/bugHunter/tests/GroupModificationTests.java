package ru.pft40.bugHunter.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft40.bugHunter.model.GroupData;
import ru.pft40.bugHunter.model.Groups;

import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (appMngr.db().groups().size() == 0) {
            appMngr.goTo().groupPage();
            appMngr.group().create(new GroupData().withName("GroupName").withHeader("Group Header")
                    .withFooter("Group Footer"), 1);
        }

    }

    @Test
    public void testGroupModificationByUpperUpdateBtn() {
        Groups before = appMngr.db().groups();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(modifiedGroup.getId())
                .withName("ChangeName " + new Date(System.currentTimeMillis()))
                .withHeader(modifiedGroup.getHeader())
                .withFooter(modifiedGroup.getFooter());
        appMngr.goTo().groupPage();
        appMngr.group().modify(group, 1);
        //assertThat(appMngr.group().count(), equalTo(before.size()));
        Groups after = appMngr.db().groups();
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
    }

    @Test(enabled = false)
    public void testGroupModificationByLowerUpdateBtn() {
        Groups before = appMngr.group().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("ChangedGroupName2");
        appMngr.group().modify(group, 2);
        assertThat(appMngr.group().count(), equalTo(before.size()));
        Groups after = appMngr.group().all();
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
    }
}
