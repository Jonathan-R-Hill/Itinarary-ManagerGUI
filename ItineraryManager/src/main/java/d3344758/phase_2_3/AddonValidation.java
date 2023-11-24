package d3344758.phase_2_3;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Jonathan Hill (d3344758)
 */
public class AddonValidation {

  /**
   * Handles the user's choice for addons and updates the activity code. Ensures the user is happy
   * with the choice they have made if it is relating to an addon.
   *
   * @param userInput The Scanner object for user input.
   * @param activityCode The code representing the activity and its addons.
   * @param addonsAdded The list of addons already added to the activity.
   * @param userChoice The user's choice for the addon.
   * @return True if the addon choice is handled successfully, false otherwise.
   */
  public static boolean handleAddonChoice(Scanner userInput, String activityCode,
          List<String> addonsAdded, String userChoice) {

    if (!addonsAdded.contains(userChoice)) {
      boolean isValid = true;

      switch (userChoice) {
        case "TRV", "INS", "PHO" -> {
          String addonDescription = switch (userChoice) {
            case "TRV" ->
              "Travel (TRV)";
            case "INS" ->
              "Insurance (INS)";
            case "PHO" ->
              "Photography (PHO)";
            default ->
              "";
          };

          String checkHappy = UserValidationChecks.checkHappy(userInput, addonDescription);
          if (checkHappy.equals("yes")) {
            updateActivityCode(activityCode, addonsAdded, userChoice);
            return !UserValidationChecks.addAnother(userInput, "addon");
          }
        }
        case "NONE" -> {
          System.out.println("Thank you for clarifying. Moving on.");
          return true;
        }
        default ->
          System.out.println("Please ensure you enter one of the codes OR none");
      }

      if (!isValid) {
        System.out.println("That has already been addon to the Activity.");
      }
    } else {
      System.out.println("That addon has already been selected. Please choose a different one.");
    }

    return false;
  }

  /**
   * Updates the activity code with the selected addon.
   *
   * @param activityCode The code representing the activity and its addons.
   * @param addonsAdded The list of addons already added to the activity.
   * @param userChoice The user's choice for the addon.
   */
  private static void updateActivityCode(String activityCode, List<String> addonsAdded, String userChoice) {
    int addonCounter = addonsAdded.size();
    String code = activityCode;

    if (addonCounter == 0) {
      code += ":";
    }
    code += userChoice + ",";
    addonsAdded.add(userChoice + ",");
  }
}
