package ItineraryReceiptGeneration;

import Objects.ActivityAddon;
import java.util.ArrayList;

import java.util.List;

/**
 *
 * @author Jonathan Hill (d3344758)
 */
public class ActivityAddonOutput {

  /**
   * Splits the add-on code into an array of individual add-ons. Assumes the code follows the format
   * "COO-50:ABC,ZXY". If no codes are found or an error occurs it returns null instead of crashing
   *
   * @param code The add-on code to be split.
   * @return An array of individual add-ons, or null if the code doesn't match the expected pattern.
   */
  private static String[] splitAddons(String code) {
    try {
      String[] splitCode = code.split(":")[1].split(",");
      return splitCode;
    } catch (ArrayIndexOutOfBoundsException e) {
      return null;
    }
  }

  /**
   * Finds and returns the names of add-ons based on the provided add-on code and existing add-ons
   * information for each addon in the list provided.
   *
   * @param existingAddons The list of pre-existing add-ons.
   * @param code The add-on code to be checked against existing add-ons.
   * @return A list of add-on names corresponding to the given add-on code.
   */
  private static List<String> findActivityAddonInfo(List<ActivityAddon> existingAddons, String code) {
    String[] addons = splitAddons(code);
    List<String> addonInfo = new ArrayList<>();

    for (String add : addons) {
      for (ActivityAddon addon : existingAddons) {
        if (addon.getShortName().equals(add)) {
          addonInfo.add(addon.getAddonName());
          break;
        }
      }
    }
    return addonInfo;
  }

  /**
   * Outputs a message indicating that no add-ons are selected for the activity and applies padding
   * to complete the boarders of the receipt
   */
  private static void outputNoAddon() {
    String noAddon = "|    - No Addons";
    int widthForPadding = 106 - (noAddon.length());
    String formatString = "%s" + "%" + widthForPadding + "s|%n";

    System.out.printf(formatString, noAddon, "");
  }

  /**
   * Outputs add-on information for a specific activity, including the: add-on name, cost, and total
   * cost along with the barriers of the receipt spaced out with padding.
   *
   * @param information The name of the add-on.
   * @param cost The cost of the add-on.
   * @param totalPeople The total number of people.
   * @param totalCost The total cost of the add-on for all people.
   */
  private static void outputActivityAddon(String information, float cost, int totalPeople, float totalCost) {
    String totalActivityAddonCost = String.format(" = £%.2f", totalCost);
    String startAddon = "|    - Add-on:  ";

    int totalActivityCostLength = totalActivityAddonCost.length();
    int informationLength = information.length();
    int widthForPadding = 104 - (startAddon.length() + informationLength + 4 + 5 + 3 + totalActivityCostLength);

    String formatString = "%s" + "%-" + informationLength + "s @ £%5.2f x %-2d%s%" + widthForPadding + "s|%n";

    System.out.printf(formatString,
            startAddon, information, cost, totalPeople, totalActivityAddonCost, "");
  }

  /**
   * Outputs add-on information for a given activity code, considering the total number of people.
   * If there are no add-ons included with the code provided, it will run the outputNoAddon method; If the
   * code has add-ons it will run the: findActivityAddonInfo method and loop over each addon in the
   * code and run the outputActivityAddon method until it has done the full list provided by the
   * method.
   *
   * @param existingAddons The list of pre-existing add-ons.
   * @param code The code associated with the activity with add-ons.
   * @param totalPeople The total number of people for the activity.
   * @return itineraryTotalCost 
   */
  protected static float addonOutput(List<ActivityAddon> existingAddons, String code, int totalPeople) {
    float activityAddonTotalCost = 0;
    
    if (splitAddons(code) == null) {
      outputNoAddon();
      return activityAddonTotalCost;
    } else {
      List<String> selectedAddons = findActivityAddonInfo(existingAddons, code);
      
      for (String addon : selectedAddons) {
        for (ActivityAddon existingAddonCheck : existingAddons) {
          if (existingAddonCheck.getAddonName().equals(addon)) {
            float addonPrice = Float.parseFloat(existingAddonCheck.getAddonPrice()) / 100;
            float totalCost = addonPrice * totalPeople;
            activityAddonTotalCost += totalCost;

            outputActivityAddon(existingAddonCheck.getAddonName(), addonPrice, totalPeople, totalCost);
            break;
          }
        }
      }
      return activityAddonTotalCost;
    }
  }

}
