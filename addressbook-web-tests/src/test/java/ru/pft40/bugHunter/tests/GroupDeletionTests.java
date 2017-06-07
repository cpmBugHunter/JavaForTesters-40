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
        if (appMngr.db().groups().size() == 0) {
            appMngr.goTo().groupPage();
            appMngr.group().create(new GroupData().withName("GroupName").withHeader("Group Header")
                    .withFooter("GroupFooter"), 1);
        }
    }

    @Test
    public void testGroupDeletionByUpperDeleteBtn() {
        Groups before = appMngr.db().groups();
        GroupData deletedGroup = before.iterator().next();
        appMngr.goTo().groupPage();
        appMngr.group().delete(deletedGroup, 1);
        assertThat(appMngr.group().count(), equalTo(before.size() - 1));
        Groups after = appMngr.db().groups();
        assertThat(after, equalTo(before.without(deletedGroup)));
        verifyGroupListInUI();
    }

    @Test(enabled = false)
    public void testGroupDeletionByLowerDeleteBtn() {
        Groups before = appMngr.group().all();
        GroupData deletedGroup = before.iterator().next();
        appMngr.group().delete(deletedGroup, 2);
        assertThat(appMngr.group().count(), equalTo(before.size() - 1));
        Groups after = appMngr.group().all();
        assertThat(after, equalTo(before.without(deletedGroup)));
    }

    /*@BeforeMethod --оставил, чтобы потом не скакать по коммитам в поисках этого метода
    public void ensurePreconditions() {
        appMngr.goTo().groupPage();
        if(appMngr.group().all().size() == 0) {
            appMngr.group().create(new GroupData().withName("GroupName").withHeader("GroupHeader")
                    .withFooter("GroupFooter"), 1);
        }
        appMngr.goTo().groupPage();
    }*/
}
