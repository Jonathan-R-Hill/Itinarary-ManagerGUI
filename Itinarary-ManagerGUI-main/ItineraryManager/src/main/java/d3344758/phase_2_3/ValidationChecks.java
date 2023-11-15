package d3344758.phase_2_3;

import java.util.Scanner;

/**
 *
 * @author Jonathan Hill (d3344758)
 */
public class ValidationChecks {

  /**
   * Prompts the user to decide on whether they are happy with their input or not.
   * returns a string of either "yes" or "no" for the method calling this to decide on whether to ask for the input
   * again or end the loop
   *
   * @param userInput A Scanner to take input from the user
   * @param param String of what the user is inputting to ask if they are happy with their input
   * @return String of yes or no for method logic to accept input or not
   */
  public static String checkHappy(Scanner userInput, String param) {
    String checkHappy = "";
    while (!checkHappy.equals("yes") && !checkHappy.equals("no")) {
      System.out.println("Are you happy with your input: " + param + "?\t yes or no");
      checkHappy = userInput.nextLine().toLowerCase();
    }
    return checkHappy;
  }

  /**
   * Prompts the user to decide on whether they are happy with their input or not returns a string
   * of either "yes" or "no" for the method calling this to decide on whether to ask for the input
   * again or end the loop
   *
   * @param userInput A Scanner to take input from the user
   * @param param int of what the user is inputting to ask if they are happy with their input
   * @return String of yes or no for method logic to accept input or not
   */
  public static String checkHappy(Scanner userInput, int param) {
    String checkHappy = "";
    while (!checkHappy.equals("yes") && !checkHappy.equals("no")) {
      System.out.println("Are you happy with your input: " + param + "?\t yes or no");
      checkHappy = userInput.nextLine().toLowerCase();
    }
    return checkHappy;
  }

  /**
   * Prompts the user to decide on whether they are happy with their input or not returns a string
   * of either "yes" or "no" for the method calling this to decide on whether to ask for the input
   * again or end the loop
   *
   * @param userInput A Scanner to take input from the user
   * @param param float of what the user is inputting to ask if they are happy with their input
   * @return String of yes or no for method logic to accept input or not
   */
  public static String checkHappy(Scanner userInput, float param) {
    String checkHappy = "";
    while (!checkHappy.equals("yes") && !checkHappy.equals("no")) {
      System.out.printf("Are you happy with your input: %.2f?\t yes or no\n", param);
      checkHappy = userInput.nextLine().toLowerCase();
    }
    return checkHappy;
  }

  /**
   * Prompts the user to decide on whether they are happy with their input or not returns a string
   * of either "yes" or "no" for the method calling this to decide on whether to ask for the input
   * again or end the loop
   *
   * @param userInput A Scanner to take input from the user
   * @param param (multiple) int of what the user is inputting to ask if they are happy with their
   * input
   * @return String of yes or no for method logic to accept input or not
   */
  public static String checkHappy(Scanner userInput, int... param) {
    String checkHappy = "";
    while (!checkHappy.equals("yes") && !checkHappy.equals("no")) {
      System.out.printf("Are you happy with your input: Hours: %d Minutes: %d?\t yes or no\n",
              param[0], param[1]);
      checkHappy = userInput.nextLine().toLowerCase();
    }
    return checkHappy;
  }

  /**
   * Prompts the user to decide on whether they want to add another of what they are currently doing.
   * Loops the method that is currently running if they chose "yes" and stops the method if they
   * chose "no"
   *
   * @param userInput A Scanner to take input from the user
   * @param param String of what the user is currently doing
   * @return boolean that will decide on whether to re-run the method
   */
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
