/*Program Name:Luhn_Mod10Check.java
 * Authors: Austin P
 * Date last Updated: 8/21/2024
 * Purpose: This program is meant to define if the user inputted credit card number
 * is a valid or invalid number through the Luhn check/Mod10 check.
 */

// Needed to take a user input
import java.util.Scanner;

public class Luhn_Mod10Check {
  public static void main(String[] args) {
    // Obtains user input then stores it as a long variable
    Scanner input = new Scanner(System.in);
    System.out.print("Enter a credit card Number as a long integer: ");
    long number = input.nextLong();

    /*
     * Checks to see if the number inputted is valid through the Luhn check
     * and prints if it is or isn't valid according to the Luhn check.
     */
    if (isValid(number)) {
      System.out.println(number + " is valid.");
    } else {
      System.out.println(number + " is invalid.");
    }
    // close the input to prevent a memeory leak
    input.close();
  }

  // Return true if the inputed card number is a valid number
  public static boolean isValid(long number) {
    int cardNum = getSize(number);

    // Returns true if the prefix condition is met, and if the card number is
    // divisible by 10
    return (cardNum >= 13 && cardNum <= 16) &&
        (prefixMatched(number, 4) || prefixMatched(number, 5) ||
            prefixMatched(number, 37) || prefixMatched(number, 6))
        &&
        ((sumOfDoubleEvenPlace(number) + sumOfOddPlace(number)) % 10 == 0);
  }

  /** Get the result from Step 2 */
  public static int sumOfDoubleEvenPlace(long number) {
    int sumTotal = 0;
    String numStr = Long.toString(number);
    for (int i = numStr.length() - 2; i >= 0; i -= 2) {
      sumTotal += getDigit(Character.getNumericValue(numStr.charAt(i)) * 2);

    }
    return sumTotal;
  }

  /**
   * Return this number if it is a single digit, otherwise,
   * return the sum of the two digits
   */
  public static int getDigit(int number) {
    if (number < 10) {
      return number;
    } else {
      return number / 10 + number % 10;
    }
  }

  /** Return sum of odd-place digits in number */
  public static int sumOfOddPlace(long number) {
    int sumTotal = 0;
    String numStr = Long.toString(number);
    for (int i = numStr.length() - 1; i >= 0; i -= 2) {
      sumTotal += Character.getNumericValue(numStr.charAt(i));
    }
    return sumTotal;
  }

  /** Return true if the number d is a prefix for number */
  public static boolean prefixMatched(long number, int d) {
    int prefixSize = getSize(d);
    long prefix = getPrefix(number, prefixSize);
    return prefix == d;
  }

  /** Return the number of digits in d */
  public static int getSize(long d) {
    return Long.toString(d).length();
  }

  /**
   * Return the first k number of digits from number. If the
   * number of digits in number is less than k, return number.
   */
  public static long getPrefix(long number, int k) {
    if (getSize(number) > k) {
      String numStr = Long.toString(number);
      return Long.parseLong(numStr.substring(0, k));
    }
    return number;
  }
}
