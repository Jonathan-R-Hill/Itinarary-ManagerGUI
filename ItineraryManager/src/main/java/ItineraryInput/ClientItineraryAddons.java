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

  /**
   * Outputs the available itinerary add-on choices to the user and prompts for their selection. The
   * user can enter the name of the add-on they would like to add, or enter "999" to indicate they
   * are done entering add-ons.
   */
  private static void itineraryChoiceOutput() {
    System.out.println("""
                       Please enter the name of the addon you would like to add.
                       If you require none or are done entering addons, Enter: 999""");
    for (ItineraryAddon addon : ExistingItineraryInformation) {
      System.out.printf("%s\t£%.2f\n",
              addon.getItineraryAddonName(), (Float.parseFloat(addon.getItineraryAddonPrice()) / 100));
    }
  }

  /**
   * Handles the user's add-on selection. If the selected add-on is not already in the user's choices,
   * it prompts the user for confirmation to add the add-on. If the user confirms, the add-on is added
   * to the user's choices. If the user has selected an add-on before they will not be able to re-add the add-on.
   *
   * @param userChoice The name of the add-on selected by the user.
   * @param userInput Scanner object for user input.
   */
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

  /**
   * Collects the user's addon choices. It continuously prompts the user for addon selections until
   * the user enters "999" to indicate they are done. It returns a list containing the user's
   * selected addons.
   *
   * @param userInput Scanner object for user input.
   * @return List of ItineraryAddon objects representing the user's selected addons.
   */
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
