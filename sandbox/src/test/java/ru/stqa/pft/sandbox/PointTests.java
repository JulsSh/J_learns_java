package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
  @Test
   public void testDistance1(){
    Point p1 = new Point(5, 8);
    Point p2 = new Point(3,4);
    Assert.assertEquals(p1.distance(p2), 4.47213595499958);
  }
@Test
  public void testDistance2(){
    Point p1 = new Point(0, 8);
    Point p2 = new Point(3,0);
    Assert.assertEquals(p1.distance(p2), 8.54400374531753);
  }
@Test
  public void testDistance3(){
    Point p1 = new Point(-17, 8);
    Point p2 = new Point(-1,0);
    Assert.assertEquals(p1.distance(p2), 17.88854381999832);
  }
}
