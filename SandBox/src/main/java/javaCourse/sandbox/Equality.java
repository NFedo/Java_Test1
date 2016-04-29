package javaCourse.sandbox;

/**
 * Created by Nadejda.Fedorova on 27.04.2016.
 */
public class Equality {

  public static void main(String[] args) {
    String s1 = "firefox";
    // String s2 = s1;
    // String s2 = new String(s1);
    String s2 = "firefox"; // оптимизация компилятора
    String s3 = "fire" + "fox"; // оптимизация компилятора
    String s4 = "firefox" + "2.0"; // оптимизация компилятора
    String s5 = "firefox" + Math.sqrt(4.0); // оптимизация компилятора
    String s6 = new String(s1);

    System.out.println(s1 == s2); // сравниваются ссылки на объект
    System.out.println(s1 == s3); // сравниваются ссылки на объект
    System.out.println(s1 + "2.0" == s4); // сравниваются ссылки на объект
    System.out.println(s4 == s5); // сравниваются ссылки на объект
    System.out.println("++++++++++++");
    System.out.println(s1.equals(s2)); // сравнивается содержимое объекта
    System.out.println(s1.equals(s3)); // сравнивается содержимое объекта
    System.out.println(s4.equals(s1 + "2.0")); // сравнивается содержимое объекта
    System.out.println(s4.equals(s5)); // сравнивается содержимое объекта
    System.out.println(s6.equals(s1)); // сравнивается содержимое объекта
  }
}
