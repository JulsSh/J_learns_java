package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class GroupDeletionTest extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    if (!app.getGroupHelper().IsThereAGroup()){
      app.getGroupHelper().createGroup (new GroupData("test1", null, null));
    }
    List<GroupData> before= app.getGroupHelper().getGroupList();

    app.getGroupHelper().selectGroup(before.size()-1);

    app.getGroupHelper().deleteSelectedGroup();
    GroupData group= new GroupData(before.get(before.size() -1 ).getId(), "test1", "test1", "test2");
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after= app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size()-1);

    before.remove(before.size()-1);
    Comparator<? super GroupData> byId= (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }
}

