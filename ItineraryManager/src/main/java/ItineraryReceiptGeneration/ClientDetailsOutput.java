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

  protected static void clientDetailsLineOne(String name, String ref) {
    String clientName = String.format("Client: %s", name);
    String reference = String.format("Ref: %s", ref);
    int widthForPadding = 103 - clientName.length() - reference.length();

    System.out.printf("| %s%" + widthForPadding + "s%s |%n", clientName, "", reference);
  }

  protected static void clientDetailsLineTwo(String dateToChange) {
    String date = String.format("Date: %s", Conversions.convertDate(dateToChange));
    int widthForPadding = 103 - date.length();
    System.out.printf("| %s%" + widthForPadding + "s |%n", date, "");
  }

  protected static void clientDetailsLineThree(String activityTotal, String attendees) {
    String totalActivitiesString = String.format("Total Activities: %s", activityTotal);
    String attendeesString = String.format("Attendees: %s", attendees);

    int activityStringLen = totalActivitiesString.length();
    int attendeesStringLen = attendeesString.length();
    int widthForPadding = 103 - activityStringLen - attendeesStringLen;

    System.out.printf("| %-" + (widthForPadding + activityStringLen) + "s%s |%n", totalActivitiesString, attendeesString);
  }

  protected static void outputCostBreakdown() {
    String costBreakdownString = "Cost Breakdown";
    int widthForPadding = 103 - costBreakdownString.length();
    int widthEachSide = widthForPadding / 2;

    System.out.printf("| %" + widthEachSide + "s%s%" + widthEachSide + "s |%n", "", costBreakdownString, "");
  }
}
