package d3344758.phase_2_3;

import java.util.Scanner;

/**
 *
 * @author Jonathan Hill (d3344758)
 */
public class ValidationChecks {

  public static String checkHappy(Scanner userInput, String param) {
    String checkHappy = "";
    while (!checkHappy.equals("yes") && !checkHappy.equals("no")) {
      System.out.println("Are you happy with your input: " + param + "?\t yes or no");
      checkHappy = userInput.nextLine().toLowerCase();
    }
    return checkHappy;
  }

  public static String checkHappy(Scanner userInput, int param) {
    String checkHappy = "";
    while (!checkHappy.equals("yes") && !checkHappy.equals("no")) {
      System.out.println("Are you happy with your input: " + param + "?\t yes or no");
      checkHappy = userInput.nextLine().toLowerCase();
    }
    return checkHappy;
  }

  public static String checkHappy(Scanner userInput, float param) {
    String checkHappy = "";
    while (!checkHappy.equals("yes") && !checkHappy.equals("no")) {
      System.out.printf("Are you happy with your input: %.2f?\t yes or no\n", param);
      checkHappy = userInput.nextLine().toLowerCase();
    }
    return checkHappy;
  }

  public static String checkHappy(Scanner userInput, int... param) {
    String checkHappy = "";
    while (!checkHappy.equals("yes") && !checkHappy.equals("no")) {
      System.out.printf("Are you happy with your input: Hours: %d Minutes: %d?\t yes or no\n",
              param[0], param[1]);
      checkHappy = userInput.nextLine().toLowerCase();
    }
    return checkHappy;
  }

  public static boolean addAnother(Scanner userInput, String param) {
    String check = "";
    while (!check.equals("yes") && !check.equals("no")) {
      System.out.printf("Would you like to make another %s entry?\tyes or no\n", param);
      check = userInput.nextLine().toLowerCase().trim().strip();
    }

    if (check.equals("yes")) {
      return true;
    }
    return false;
  }

}
