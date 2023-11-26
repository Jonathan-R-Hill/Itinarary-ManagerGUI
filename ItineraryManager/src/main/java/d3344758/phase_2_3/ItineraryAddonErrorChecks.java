package d3344758.phase_2_3;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Jonathan Hill (d3344758)
 */
public class ItineraryAddonErrorChecks {

  public static boolean isValidItineraryAddons(String userChoice, List<String> existingAddons) {

    if (!userChoice.equals("none")) {
      for (String addon : existingAddons) {
        String[] addonSplit = addon.split(":");
        
        if (userChoice.equals(addonSplit[0])) {
          System.out.printf("\"%s\" has already been added. Please choose a different option.\n", userChoice);
          return false;
        }
      }
    } else {
      return false;
    }

    return true;
  }

  public static boolean isHappyWithChoice(Scanner userInput, String choice) {
    String checkHappy = UserValidationChecks.checkHappy(userInput, choice);
    return checkHappy.equals("yes");
  }
}
