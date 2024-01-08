package ItineraryInput;

import Main.ProgramChoice;
import Objects.ActivityAddon;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Jonathan Hill (d3344758)
 */
public class ActivityAddonsRequired {

  private static final List<ActivityAddon> ExistingAddons = ProgramChoice.activityAddons;

  /**
   * Handles the user's add-on choice. Checks if the provided add-on choice is valid by verifying it
   * is in the list of existing add-ons and has not been added before.
   *
   * @param addonsAdded List of add-ons already added by the user.
   * @param userChoice The user's choice of add-on.
   * @return True if the add-on choice is valid; false otherwise.
   */
  private static boolean handleAddonChoice(List<String> addonsAdded, String userChoice) {
    if (!addonsAdded.contains(userChoice)) {
      for (ActivityAddon object : ExistingAddons) {
        if (userChoice.equals(object.getShortName())) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Outputs the available add-on choices to the user in the console.
   */
  private static void consoleOutput() {
    System.out.println("Please choose from the list below.");
    for (ActivityAddon object : ExistingAddons) {
      float price = Float.parseFloat(object.getAddonPrice()) / 100;
      System.out.printf("Addon Name: %s\tCost: £%s\tEnter: %s\n",
              object.getAddonName(), price, object.getShortName());
    }
    System.out.println("If you do not require any addons or you are done entering them.\tEnter: NONE");
  }

  /**
   * Collects the user's add-on choices. Continuously prompts the user for add-on selections until
   * the user enters "NONE" to indicate they are done. Returns a comma-separated string of the
   * user's selected add-on codes. We also check every input to ensure it is not an add-on that has
   * already been added to the activity.
   *
   * @param userInput Scanner object for user input.
   * @param code The code associated with the activity the user is adding add-ons to.
   * @return Comma-separated string of user's selected add-on codes.
   */
  public static String collectUserAddons(Scanner userInput, String code) {
    List<String> addonsAdded = new ArrayList<>();

    while (true) {
      consoleOutput();
      String userChoice = userInput.nextLine().toUpperCase();

      if (userChoice.equals("NONE")) {
        System.out.println("thank you for your input.\n");
        break;
      } else if (!addonsAdded.contains(userChoice)) {
        if (handleAddonChoice(addonsAdded, userChoice)) {
          System.out.println("Addon added.\n");
          addonsAdded.add(userChoice);
        } else {
          System.out.println("Please ensure you are entering a valid item from the list provided.");
        }
      } else {
        System.out.println("Please ensure you are not entering an addon you have already chosen\n");
      }
    }

    return String.join(",", addonsAdded);
  }

}
