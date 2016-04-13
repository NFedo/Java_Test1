package javaCourse.sandbox;

public class NewProgram12 {
    public static void main(String[] args) {
        String somebody = "world";
        System.out.println("Hello, " + somebody + "!");

        hello("world !");
        hello("world !!");
        hello("world !!!");

        double l = 5;
        System.out.println("Площадь квадрата со стороной " + l + " = " + area(l));

        double a = 4;
        double b = 6;
        System.out.println("Площадь прямоугольника со сторонами " + a + " и " + b + " = " + area(a,b));

        int Lo = 6;
        double ll  = 8.0;
        int s = Lo * Lo;
        System.out.println("Площадь квадрата со стороной " + Lo + " = " + (Lo * Lo));
    }

    public static void hello(String somebody){
        System.out.println("Hello, " + somebody + "!");
    }

    public static double area(double len) { // совсем другая переменная l
      return len * len ;
    }

    public static double area(double a, double b) {
        return a * b;
    }
}