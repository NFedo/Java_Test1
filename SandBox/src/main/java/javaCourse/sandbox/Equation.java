package javaCourse.sandbox;

/**
 * Created by Nadejda.Fedorova on 27.04.2016.
 */
public class Equation {

  private double a;
  private double b;
  private double c;

  private int n;

  public Equation (double a, double b, double c) {

    this.a = a;
    this.b = b;
    this.c = c;

    double d = b*b - 4*a*c;

   /* if (a == 0) {
      System.out.println("Это вырожденное уравнение");      // полная форма
      // if (a == 0) System.out.println("Это вырожденное уравнение"); можно и так, но не рекомендуется - неполная форма
      if (b == 0) {
        if (c == 0) {
          n = -1;
        } else {
          n = 0;
        }
      } else {
        n = 1;
      }
    } else {
      if (d > 0) { // свернутая форма
        n = 2;
      } else if (d == 0) {
        n = 1;
      } else {
        n = 0;
      }
    } */

   // более читабельно
    if (a != 0) {
      if (d > 0) { // свернутая форма
        n = 2;
      } else if (d == 0) {
        n = 1;
      } else {
        n = 0;
      }

    } else if (b != 0) {
      n = 1;

    } else if (c != 0) {
      n = 0;

    } else {
      n = -1;
    }

  }
  public int rootNumber() {
    return n;
  }
}
