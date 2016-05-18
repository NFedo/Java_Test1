package javaCourse.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Nadejda.Fedorova on 17.05.2016.
 */
public class Collections {

  public static void main(String[] args) {
    String[] langs = {"Java", "C#", "Python", "PHP"};  // new String[4];  // массив
    // langs[0] = "Java";
    // langs[1] = "C#";
    // langs[2] = "Python";
    // langs[3] = "PHP";

    // интерфейс
    List<String> languages = Arrays.asList("Java", "C#", "Python", "PHP");  // список
    // languages.add("Java");
    // languages.add("C#");
    // languages.add("Python");

   // for (int i = 0; i <langs.length; i++) {
   //   System.out.println("Я хочу выучить " + langs[i]);

      for (String l : languages) {
        System.out.println("Я хочу выучить " + l);
    }

    // for (int i = 0; i < languages.size(); i++) {
    //   System.out.println("Я хочу выучить " + languages.get(i));
    // }
  }
}
