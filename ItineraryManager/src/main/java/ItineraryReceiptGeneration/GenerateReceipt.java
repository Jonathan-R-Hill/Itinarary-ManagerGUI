package ItineraryReceiptGeneration;

import ItineraryInput.ItineraryMain;
import Main.ProgramChoice;
import Objects.Activity;
import Objects.ActivityAddon;
import Objects.ItineraryAddon;

import java.util.List;

public class GenerateReceipt {

  private ItineraryMain itinerary;
  private final List<Activity> existingActivityInformation = ProgramChoice.activityData;
  private final List<ActivityAddon> existingAddons = ProgramChoice.activityAddons;
  private final List<ItineraryAddon> existingItineraryAddons = ProgramChoice.itineraryAddons;

  public GenerateReceipt(ItineraryMain itinerary) {
    this.itinerary = itinerary;
  }

  private void sectionNameOutput(String information) {
    String sectionNameString = String.format("%s", information);
    int widthForPadding = 103 - sectionNameString.length();

    System.out.printf("| %s%" + widthForPadding + "s |%n", sectionNameString, "");
  }

  private void sectionTotalOutput(String sectionName, float totalCost) {
    String sectionNameString = String.format("%s: ", sectionName);
    String totalCostString = String.format("Total Cost: £%6.2f", totalCost);
    int widthForPadding = 101 - sectionName.length() - totalCostString.length();
    int blankLine = 105;
    
    System.out.printf("| %s%s%" + widthForPadding + "s |%n", sectionNameString, totalCostString, "");
    System.out.printf("|%" + blankLine + "s|%n", "");
  }

  private float discountOutput(float ActivitiesTotalCost, int totalPeople, int totalActivities) {
    float discountPercentage = DiscountCalcUtil.calcualteDiscount(totalPeople, totalActivities); // decimal
    float discountTotal = ActivitiesTotalCost * discountPercentage;
    String discountString = String.format("| Activity Discount: %.0f%%", discountPercentage * 100);
    String discountTotalString = String.format("Total Discount: £%6.2f", discountTotal);

    int widthForPadding = 105 - discountString.length() - discountTotalString.length();

    System.out.printf("%s%" + widthForPadding + "s%s |%n", discountString, "", discountTotalString);

    return discountTotal;
  }

  private void outputOverallTotal(float totalPrice) {
    String totalCostString = String.format("| Total Cost: £%6.2f", totalPrice);
    int widthForPadding = 105 - totalCostString.length();

     System.out.printf("%s%" + widthForPadding + "s |%n", totalCostString, "");
  }

  /**
   * outputs a receipt that can take any input that the user has requested totalling
   * the cost of each item they have added to their itinerary including their details including:
   * Lead Attendee (client), Reference code, Date of booking, Total Activities, 
   * Total people attending as well as details of their add-ons and activities
   * 
   * @return (float) total cost of the itinerary
   */
  public float generateReceipt() {
    int totalPeople = itinerary.getTotalPeople();
    int totalActivities = itinerary.getActivities().size();
    String totalActivitiesString = Conversions.convertTotalActivities(totalActivities);
    int counter = 1;

    ClientDetailsOutput.startEndReceipt();
    ClientDetailsOutput.clientDetailsLineOne(itinerary.getClientName(), itinerary.getReferenceNumber());
    ClientDetailsOutput.clientDetailsLineTwo(itinerary.getDate());
    ClientDetailsOutput.clientDetailsLineThree(totalActivitiesString, Conversions.convertTotalPeople(totalPeople));
    ClientDetailsOutput.outputCostBreakdown();
    // Itinerary
    sectionNameOutput("Itinerary Add-ons");
    float itineraryTotalCost = ItineraryAddonOutput.outputItineraryInformation(existingItineraryAddons, itinerary.getItineraryAddons(), totalPeople);
    sectionTotalOutput("Itinerary Addons", itineraryTotalCost);

    // Activities
    float allActivitiesCost = 0;
    sectionNameOutput("Activities");
    for (int i = 0; i < totalActivities; i++) {

      String activityname = itinerary.getActivities().get(i);
      Activity currentActivity = ActivityOutput.getCurrentActivityInfo(activityname, existingActivityInformation);

      float activityCost = Float.parseFloat(currentActivity.getBaseCost()) / 100;
      float totalActivityCost = activityCost * totalPeople;

      float activityAddonTotalCost = ActivityOutput.outputActivity(activityname, activityCost, counter, totalPeople, totalActivityCost);
      float activityTotalCost = ActivityAddonOutput.addonOutput(existingAddons, itinerary.getActivityCodes().get(counter - 1), totalPeople);
      allActivitiesCost += activityAddonTotalCost + activityTotalCost;
      sectionTotalOutput(activityname, (activityAddonTotalCost + activityTotalCost));
      counter++;
    }
    sectionTotalOutput("Activities", allActivitiesCost);

    float discount = discountOutput(allActivitiesCost, totalPeople, totalActivities);
    outputOverallTotal(allActivitiesCost + itineraryTotalCost - discount);
    ClientDetailsOutput.startEndReceipt();

    return allActivitiesCost + itineraryTotalCost - discount;
  }

}
