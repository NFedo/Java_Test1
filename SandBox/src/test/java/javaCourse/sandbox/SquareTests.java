package javaCourse.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Nadejda.Fedorova on 20.04.2016.
 */
public class SquareTests {

  @Test
  public void testArea() {
    Square s = new Square(5);
    Assert.assertEquals(s.area(), 25.0);
  }

}
