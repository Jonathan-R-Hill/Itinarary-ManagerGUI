package ItineraryReceiptGeneration;

import java.time.Month;

/**
 *
 * @author Jonathan Hill (d3344758)
 */
public class Conversions {

  /**
   * Covert the numbers 1- 5 to their respective written form Returns the int as a String if it is
   * above 5
   *
   * @param totalPeople The amount of people the user has selected upon creating their itinerary
   * @return String of of the amount of people in the party either as a number if above 5 OR as a
   * written number if less than 5
   */
  protected static String convertTotalPeople(int totalPeople) {

    if (totalPeople <= 5) {
      switch (totalPeople) {
        case 1 -> {
          return "One";
        }
        case 2 -> {
          return "Two";
        }
        case 3 -> {
          return "Three";
        }
        case 4 -> {
          return "Four";
        }
        case 5 -> {
          return "Five";
        }
      }
    }

    return String.valueOf(totalPeople);
  }

  /**
   * Converts a date represented in the format "DD-MM-YYYY" to its shortened written form of the
   * month.
   *
   * @param date The date in the format "DD-MM-YYYY".
   * @return The date written in the format: DD MMM YYYY (MMM = Short written date)
   */
  protected static String convertDate(String date) {
    String splitDate[] = date.split("-");
    String monthToConvert = splitDate[1].strip();
    int monthNumber = Integer.parseInt(monthToConvert);

    Month month = Month.of(monthNumber);
    splitDate[1] = month.toString().substring(0, 3);

    return String.join(" ", splitDate);
  }

  /**
   * Covert the numbers 1- 5 to their respective written form Returns the int as a String if it is
   * above 5
   *
   * @param totalActivities The amount of activities the user has selected upon creating their itinerary
   * @return String of of the amount of activities as a number if above 5 OR as a written number if less than 5
   */
  protected static String convertTotalActivities(int totalActivities) {

    if (totalActivities <= 5) {
      switch (totalActivities) {
        case 1 -> {
          return "One";
        }
        case 2 -> {
          return "Two";
        }
        case 3 -> {
          return "Three";
        }
        case 4 -> {
          return "Four";
        }
        case 5 -> {
          return "Five";
        }
      }
    }

    return String.valueOf(totalActivities);
  }

}
