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

  private static List<ActivityAddon> ExistingAddons = ProgramChoice.activityAddons;

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

  private static void consoleOutput() {
    System.out.println("Please choose from the list below.");
    for (ActivityAddon object : ExistingAddons) {
      float price = Float.parseFloat(object.getAddonPrice()) / 100;
      System.out.printf("Addon Name: %s\tCost: £%s\tEnter: %s\n",
              object.getAddonName(), price, object.getShortName());
    }
    System.out.println("If you do not require any addons or you are done entering them.\tEnter: NONE");
  }

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
