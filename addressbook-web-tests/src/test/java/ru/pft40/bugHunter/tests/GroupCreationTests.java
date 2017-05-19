package ru.pft40.bugHunter.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft40.bugHunter.model.GroupData;
import ru.pft40.bugHunter.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreationByUpperCreateBtn() {

        appMngr.goTo().groupPage();
        Groups before = appMngr.group().all();
        GroupData group = new GroupData().withName("GroupName");
        appMngr.group().create(group, 1);
        Groups after = appMngr.group().all();
        assertThat(after.size(), equalTo(before.size() + 1));

        assertThat(after, equalTo(before
                .withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test
    public void testGroupCreationByLowerCreateBtn() {

        appMngr.goTo().groupPage();
        Groups before = appMngr.group().all();
        GroupData group = new GroupData().withName("GroupName");
        appMngr.group().create(group, 2);
        Groups after = appMngr.group().all();
        assertThat(after.size(), equalTo(before.size() + 1));

        assertThat(after, equalTo(before
                .withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }
}
