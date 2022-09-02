package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class EditGroupTest extends TestBase {
  @Test
  public void testGroupModification() {
    app.getNavigationHelper().gotoGroupPage();

    if (!app.getGroupHelper().IsThereAGroup()){
      app.getGroupHelper().createGroup (new GroupData("test1", null, null));
    }
    List<GroupData> before= app.getGroupHelper().getGroupList();
    app.getGroupHelper().initModification();
    app.getGroupHelper().fillGroupForm(new GroupData("jul", "jul2", "jul3"));
    app.getGroupHelper().submitGroupModification();
    app.getNavigationHelper().returnToHome();
    List<GroupData> after= app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size());

  }
}
