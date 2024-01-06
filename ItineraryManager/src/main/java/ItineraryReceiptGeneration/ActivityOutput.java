package ItineraryReceiptGeneration;

import Objects.Activity;
import java.util.List;

/**
 *
 * @author Jonathan Hill (d3344758)
 */
public class ActivityOutput {

  protected static float outputActivity(String information, float cost, int itemCounter, int totalPeople, float totalCost) {
    String totalActivityCost = String.format(" = £%.2f", totalCost);

    int totalActivityCostLength = totalActivityCost.length();
    int informationLength = information.length();
    int counterLength = itemCounter < 10 ? 2 : 3;
    int widthForPadding = 100 - (counterLength + 1 + informationLength + 2 + 5 + 3 + totalActivityCostLength);

    String itemCounterFormat = itemCounter < 10 ? "| %d." : "| %-2d.";
    String formatString = itemCounterFormat + " %-" + informationLength + "s @ £%5.2f x %-2d%s%" + widthForPadding + "s|%n";

    System.out.printf(formatString, itemCounter, information, cost, totalPeople, totalActivityCost, "");
    
    return totalCost;
  }

  protected static Activity getCurrentActivityInfo(String actvityName, List<Activity> existingActivityInformation) {
    for (Activity object : existingActivityInformation) {
      if (object.getActivityName().equals(actvityName)) {
        return object;
      }
    }
    return null;
  }
  
}
