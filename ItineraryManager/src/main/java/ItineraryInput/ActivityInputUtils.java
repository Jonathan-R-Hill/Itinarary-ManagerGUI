package ItineraryInput;

import Objects.Activity;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Jonathan Hill (d3344758)
 */
public class ActivityInputUtils {

  /**
   * Outputs the available activity choices for the user to chose from along with a number to save
   * the user typing out the entire activity name
   * 
   * @param ExistingActivityInformation A List of Activity Objects containing the information of each activity
   */
  protected static void activityConsoleOutput(List<Activity> ExistingActivityInformation) {
    int counter = 0;

    for (Activity information : ExistingActivityInformation) {
      System.out.print(counter + ": " + information.getActivityName() + "\t");
      counter++;
      if (counter % 3 == 0 && counter != 0) {
        System.out.println(); // makes the output easier to read
      }
    }

    System.out.println("\nIf you are done entering activities; Enter: 999");
  }

  /**
   * Displays activities available along with numbers for the user to chose from and returns the
   * numerical value entered. 
   * If the user enters a character catch the error and will loop until they enter a  valid number
   *
   * @param userInput A Scanner Object to get input from the user
   * @return (int) The users choice of activity
   */
  protected static int userActivityChoice(Scanner userInput) {
    while (true) {
      try {
        System.out.println("Please enter the activity you would like to add. Pick a number from the list provided.");
        int userChoice = userInput.nextInt();
        userInput.nextLine();
        return userChoice;
      } catch (InputMismatchException error) {
        System.out.println("Please enter a number that has been displayed.");
        userInput.nextLine();
      }
    }
  }

  /**
   * Asks the user for input regarding add-ons regarding their activity. Once the user has finished
   * with their choice, creates a new String with the format: CODE:ADDONS
   *
   * @param userInput A scanner object to be used to take in the user choice of add-ons
   * @param activityCode The activity code collected from the users choice
   * @return The current activity code along with the add-ons selected by the user in the format:
   * code:addons
   */
  protected static String addonRequired(Scanner userInput, String activityCode) {
    String addons = ActivityAddonsRequired.collectUserAddons(userInput, activityCode);

    String codeWithAddons = activityCode + ":" + addons;
    return codeWithAddons;
  }

}
