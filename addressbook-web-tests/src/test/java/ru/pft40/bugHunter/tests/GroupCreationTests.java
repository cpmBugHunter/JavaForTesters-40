package ru.pft40.bugHunter.tests;

import org.testng.annotations.Test;
import ru.pft40.bugHunter.model.GroupData;
import ru.pft40.bugHunter.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreationByUpperCreateBtn() {

        appMngr.goTo().groupPage();
        Groups before = appMngr.group().all();
        GroupData group = new GroupData().withName("GroupName");
        appMngr.group().create(group, 1);
        assertThat(appMngr.group().count(), equalTo(before.size() + 1));
        Groups after = appMngr.group().all();
        assertThat(after, equalTo(before
                .withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test
    public void testGroupCreationByLowerCreateBtn() {

        appMngr.goTo().groupPage();
        Groups before = appMngr.group().all();
        GroupData group = new GroupData().withName("GroupName");
        appMngr.group().create(group, 2);
        assertThat(appMngr.group().count(), equalTo(before.size() + 1));
        Groups after = appMngr.group().all();
        assertThat(after, equalTo(before
                .withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test
    public void testBadGroupCreation() {

        appMngr.goTo().groupPage();
        Groups before = appMngr.group().all();
        GroupData group = new GroupData().withName("GroupName'");
        appMngr.group().create(group, 1);
        assertThat(appMngr.group().count(), equalTo(before.size()));
        Groups after = appMngr.group().all();
        assertThat(after, equalTo(before));
    }
}
