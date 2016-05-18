package javaCourse.sandbox;

/**
 * Created by Nadejda.Fedorova on 17.05.2016.
 */
public class Primes {
  public static boolean isPrime(int n) {
    for (int i = 2; i < n; i++ ){    // i = i + 1 or i++
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }

  public static boolean isPrimeWhile(int n) {
    int i = 2;
    while (i < n && n % i != 0) {
      i++;
    }
    return i == n;
  }

  public static boolean isPrime(long n) {
    for (long i = 2; i < n; i++ ){    // i = i + 1 or i++
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }

  public static boolean isPrimeFast(int n) {
    int m = (int) Math.sqrt(n); // целая часть корня
    for (int i = 2; i < m; i++ ){    // i = i + 1 or i++
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }
}
