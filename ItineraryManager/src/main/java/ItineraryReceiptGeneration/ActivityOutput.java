package ItineraryReceiptGeneration;

import Objects.Activity;
import java.util.List;

/**
 *
 * @author Jonathan Hill (d3344758)
 */
public class ActivityOutput {

  /**
   * Outputs the information for a single activity in the itinerary.
   *
   * @param information The information about the activity.
   * @param cost The cost of the activity.
   * @param itemCounter The counter for the activity item in the itinerary.
   * @param totalPeople The total number of people in the itinerary.
   * @param totalCost The total cost of the activity for all people.
   * @return The total cost of the activity for all people as a float value.
   */
  protected static float outputActivity(String information, float cost, int itemCounter, int totalPeople, float totalCost) {
    String totalActivityCost = String.format(" = £%.2f", totalCost);

    int totalActivityCostLength = totalActivityCost.length();
    int informationLength = information.length();
    int counterLength = itemCounter < 10 ? 2 : 3;
    int widthForPadding = 100 - (counterLength + 1 + informationLength + 2 + 5 + 3 + totalActivityCostLength + 2);

    String itemCounterFormat = itemCounter < 10 ? "| %d." : "| %-2d.";
    String formatString = itemCounterFormat + " %-" + informationLength + "s @ £%7.2f x %-2d%s%" + widthForPadding + "s|%n";

    System.out.printf(formatString, itemCounter, information, cost, totalPeople, totalActivityCost, "");

    return totalCost;
  }

  /**
   * Retrieves information about a specific activity.
   *
   * @param actvityName The name of the activity to retrieve information for.
   * @param existingActivityInformation A list of existing Activity objects containing information about activities.
   * @return The Activity object containing information about the specified activity. Returns null
   * if the activity is not found which should never happen.
   */
  protected static Activity getCurrentActivityInfo(String actvityName, List<Activity> existingActivityInformation) {
    for (Activity object : existingActivityInformation) {
      if (object.getActivityName().equals(actvityName)) {
        return object;
      }
    }
    return null;
  }

}
