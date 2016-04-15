package javaCourse.sandbox;

/**
 * Created by Nadejda.Fedorova on 15.04.2016.
 */
public class Point {

  public double x;
  public double y;

  public Point(double x,double y) {
    this.x = x;
    this.y = y;
  }

  public double distance(Point p1, Point p2){
    // Math.sqrt
    return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
  }

}
