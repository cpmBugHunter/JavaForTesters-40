package ru.pft40.bugHunter.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft40.bugHunter.model.GroupData;
import ru.pft40.bugHunter.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
        Groups before = appMngr.group().all();
        GroupData deletedGroup = before.iterator().next();
        appMngr.group().delete(deletedGroup, 1);
        Groups after = appMngr.group().all();

        assertThat(after.size(), equalTo(before.size()-1));
        assertThat(after, equalTo(before.without(deletedGroup)));
    }

    @Test
    public void testGroupDeletionByLowerDeleteBtn() {
        Groups before = appMngr.group().all();
        GroupData deletedGroup = before.iterator().next();
        appMngr.group().delete(deletedGroup, 2);
        Groups after = appMngr.group().all();

        assertThat(after.size(), equalTo(before.size()-1));
        assertThat(after, equalTo(before.without(deletedGroup)));
    }
}
