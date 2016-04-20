package javaCourse.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Nadejda.Fedorova on 20.04.2016.
 */
public class PointTests {

  @Test
  public void testPoint1() {
    Point p1 = new Point(1,5);
    Point p2 = new Point(6,5);

    Assert.assertEquals(p1.distance(p1,p2), 5.0);
  }

  @Test
  public void testPoint2() {
    Point p1 = new Point(1,5);
    Point p2 = new Point(6,5);

    Assert.assertEquals(p1.distance(p2,p1), 5.0);
     //Assert.assertEquals(p1.distance(p2,p1), 5.1);
  }

  @Test
  public void testPoint3() {
    Point p1 = new Point(1,5);
    Point p2 = new Point(6,5);

    Assert.assertEquals(p1.distance(p1,p1), 0.0);
    // Assert.assertEquals(p1.distance(p1,p1), 0);
  }

}


