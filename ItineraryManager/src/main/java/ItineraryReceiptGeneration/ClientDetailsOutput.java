package ItineraryReceiptGeneration;

/**
 *
 * @author Jonathan Hill (d3344758)
 */
public class ClientDetailsOutput {

  /**
   * Outputs the starting and ending barriers for the receipt once called
   */
  protected static void startEndReceipt() {
    String repeatedString = new String(new char[105]).replace('\0', '=');

    System.out.printf("+%105s+%n", repeatedString);
  }

  /**
   * Outputs the Clients name and reference code with padding in between. 
   */
  protected static void clientDetailsLineOne(String name, String ref) {
    String clientName = String.format("Client: %s", name);
    String reference = String.format("Ref: %s", ref);
    int widthForPadding = 103 - clientName.length() - reference.length();

    System.out.printf("| %s%" + widthForPadding + "s%s |%n", clientName, "", reference);
  }
  
  /**
   * Outputs the date of the itinerary in the form DD-MMM-YYYY (written form)
   * 
   * @param dateToChange The number form of the date
   */
  protected static void clientDetailsLineTwo(String dateToChange) {
    String date = String.format("Date: %s", Conversions.convertDate(dateToChange));
    int widthForPadding = 103 - date.length();
    System.out.printf("| %s%" + widthForPadding + "s |%n", date, "");
  }
  
  /**
   * outputs the activity total and the amount of people attending
   * 
   * @param activityTotal The total amount of activities
   * @param attendees The total amount of people attending
   */
  protected static void clientDetailsLineThree(String activityTotal, String attendees) {
    String totalActivitiesString = String.format("Total Activities: %s", activityTotal);
    String attendeesString = String.format("Attendees: %s", attendees);

    int activityStringLen = totalActivitiesString.length();
    int attendeesStringLen = attendeesString.length();
    int widthForPadding = 103 - activityStringLen - attendeesStringLen;

    System.out.printf("| %-" + (widthForPadding + activityStringLen) + "s%s |%n", totalActivitiesString, attendeesString);
  }
  
  /**
   * Outputs "Cost Breakdown" before all of the calculations are shown in the centre of the receipt.
   */
  protected static void outputCostBreakdown() {
    String costBreakdownString = "Cost Breakdown";
    int widthForPadding = 103 - costBreakdownString.length();
    int widthEachSide = widthForPadding / 2;

    System.out.printf("| %" + widthEachSide + "s%s%" + widthEachSide + "s  |%n", "", costBreakdownString, "");
  }
}
