package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class EditGroupTest extends TestBase {
  @Test
  public void testGroupModification() {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initModification();
    app.getGroupHelper().fillGroupForm(new GroupData("jul", "jul2", "jul3"));
    app.getGroupHelper().submitGroupModofication();
    app.getNavigationHelper().returnToHome();

  }
}
