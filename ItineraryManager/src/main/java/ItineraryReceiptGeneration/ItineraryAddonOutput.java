package ItineraryReceiptGeneration;

import Objects.ItineraryAddon;
import java.util.List;

/**
 *
 * @author Jonathan Hill (d3344758)
 */
public class ItineraryAddonOutput {

  /**
   * Outputs information about selected itinerary add-ons, including cost details.
   *
   * @param existingItineraryAddons A list of existing itinerary add-ons.
   * @param userChoiceAddons A list of user-selected itinerary add-ons.
   * @param totalPeople The total number of people the itinerary applies to.
   * @return The total cost of the selected itinerary add-ons.
   */
  protected static float outputItineraryInformation(List<ItineraryAddon> existingItineraryAddons,
          List<ItineraryAddon> userChoiceAddons, int totalPeople) {
    float itineraryTotalCost = 0;
    if (!userChoiceAddons.isEmpty()) {
      for (ItineraryAddon userAddon : userChoiceAddons) {
        for (ItineraryAddon existingAddon : existingItineraryAddons) {
          if (userAddon.getItineraryAddonName().equals(existingAddon.getItineraryAddonName())) {
            float addonPrice = Float.parseFloat(userAddon.getItineraryAddonPrice()) / 100;
            float totalCost = totalPeople * addonPrice;

            itineraryTotalCost += totalCost;
            outputItineraryAddon(userAddon.getItineraryAddonName(), addonPrice, totalPeople, totalCost);
            break;
          }
        }
      }
    }

    if (userChoiceAddons.isEmpty()) {
      outputItineraryNoAddon();
    }
    return itineraryTotalCost;
  }

  /**
   * Outputs a message indicating that no add-ons are selected for the activity and applies padding
   * to complete the boarders of the receipt
   */
  private static void outputItineraryNoAddon() {
    String noAddon = "|    - No Addons";
    int widthForPadding = 106 - (noAddon.length());
    String formatString = "%s" + "%" + widthForPadding + "s|%n";

    System.out.printf(formatString, noAddon, "");
  }

  /**
   * Outputs information about an itinerary add-on, including cost details.
   *
   * @param information The name or description of the add-on.
   * @param cost The individual cost of the add-on.
   * @param totalPeople The total number of people the add-on applies to.
   * @param totalCost The total cost of the add-on for all people.
   */
  private static void outputItineraryAddon(String information, float cost, int totalPeople, float totalCost) {
    String totalAddonCost = String.format(" = £%.2f", totalCost);
    String startAddon = "|    - ";

    int totalActivityCostLength = totalAddonCost.length();
    int informationLength = information.length();
    int widthForPadding = 104 - (startAddon.length() + informationLength + 4 + 5 + 3 + totalActivityCostLength);

    String formatString = "%s" + "%-" + informationLength + "s @ £%5.2f x %-2d%s%" + widthForPadding + "s|%n";

    System.out.printf(formatString,
            startAddon, information, cost, totalPeople, totalAddonCost, "");
  }
}
