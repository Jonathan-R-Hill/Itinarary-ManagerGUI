package ItineraryInput;

import Main.ProgramChoice;
import Objects.ItineraryAddon;
import Utility.UserValidationChecks;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Jonathan Hill (d3344758)
 */
public class ClientItineraryAddons {

  private static List<ItineraryAddon> ExistingItineraryInformation = ProgramChoice.itineraryAddons;
  private static List<ItineraryAddon> userChoiceAddons = new ArrayList<>();

  private static void itineraryChoiceOutput() {
    System.out.println("""
                       Please enter the name of the addon you would like to add.
                       If you require none or are done entering addons, Enter: 999""");
    for (ItineraryAddon addon : ExistingItineraryInformation) {
      System.out.printf("%s\t£%.2f\n",
              addon.getItineraryAddonName(), (Float.parseFloat(addon.getItineraryAddonPrice()) / 100));
    }
  }

  private static void handleAddonSelection(String userChoice, Scanner userInput) {
    for (ItineraryAddon addon : ExistingItineraryInformation) {
      if (userChoice.equals(addon.getItineraryAddonName())) {
        if (!userChoiceAddons.contains(addon)) {
          if (UserValidationChecks.checkHappy(userInput, userChoice).equals("yes")) {
            userChoiceAddons.add(addon);
            break;
          }
        } else {
          System.out.println("Please ensure you are not entering an addon you have not already added.");
          break;
        }
      }
    }
  }

  public static List<ItineraryAddon> collectUserChoice(Scanner userInput) {

    while (true) {
      itineraryChoiceOutput();
      String userChoice = userInput.nextLine();

      if (userChoice.equals("999")) {
        System.out.println("Thank you for your input.\n");
        break;
      }

      handleAddonSelection(userChoice, userInput);
    }

    return userChoiceAddons;
  }

}
