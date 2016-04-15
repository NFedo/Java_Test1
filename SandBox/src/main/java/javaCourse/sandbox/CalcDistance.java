package javaCourse.sandbox;

/**
 * Created by Nadejda.Fedorova on 15.04.2016.
 */

public class CalcDistance {
  public static void main(String[] args) {

    Point p1 = new Point(1.5, 1.99);
    Point p2 = new Point(3, -3);
    Point p3 = new Point(-1, -3);

    //p1.x = -1.35;
    //p1.y = -1.66;

    //p2.x = 10.99;
    //p2.y = -1.99;

    System.out.println("Расстояние между точками (" + p1.x + ", " + p1.y + ") и (" + p2.x + ", " + p2.y + ") = " + distance(p1, p2));
    // расстояние между точками не зависит от порядка переменных в методе
    System.out.println("Расстояние между точками (" + p2.x + ", " + p2.y + ") и (" + p1.x + ", " + p1.y + ") = " + p1.distance(p2, p1));
    // расстояние между совпадающими точками 0
    System.out.println("Расстояние между точками (" + p3.x + ", " + p3.y + ") и (" + p3.x + ", " + p3.y + ") = " + p2.distance(p3, p3));

  }

    public static double distance(Point p1, Point p2){
      // Math.sqrt - квадратный корень
      return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
    }

}