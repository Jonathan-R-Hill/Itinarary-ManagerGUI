package ItineraryReceiptGeneration;

/**
 *
 * @author Jonathan Hill (d3344758)
 */
public class DiscountCalcUtil {

  /**
   * Calculates the discount based on the total number of people and total activities.
   *
   * @param totalPeople The total number of people in the itinerary.
   * @param totalActivities The total number of activities in the itinerary.
   * @return The calculated discount as a float value.
   */
  protected static float calcualteDiscount(int totalPeople, int totalActivities) {
    float discount;

    if (totalPeople < 10) {
      discount = discount10People(totalActivities);
    } else if (totalActivities < 20) {
      discount = discount20People(totalActivities);
    } else {
      discount = discount20PlusPeople(totalActivities);
    }

    return discount;
  }

  /**
   * Calculates the discount for itineraries with less than 10 people.
   *
   * @param totalActivities The total number of activities in the itinerary.
   * @return The calculated discount as a float value.
   */
  private static float discount10People(int totalActivities) {
    switch (totalActivities) {
      case 1, 2 -> {
        return 0.00f;
      }
      case 3, 4, 5 -> {
        return 0.05f;
      }
      default -> {
        return 0.10f;
      }
    }
  }

  /**
   * Calculates the discount for itineraries with 10 - 19 people.
   *
   * @param totalActivities The total number of activities in the itinerary.
   * @return The calculated discount as a float value.
   */
  private static float discount20People(int totalActivities) {
    switch (totalActivities) {
      case 1, 2 -> {
        return 0.05f;
      }
      case 3, 4, 5 -> {
        return 0.08f;
      }
      default -> {
        return 0.12f;
      }
    }
  }

  /**
   * Calculates the discount for itineraries with 20 or more people.
   *
   * @param totalActivities The total number of activities in the itinerary.
   * @return The calculated discount as a float value.
   */
  private static float discount20PlusPeople(int totalActivities) {
    switch (totalActivities) {
      case 1, 2 -> {
        return 0.10f;
      }
      case 3, 4, 5 -> {
        return 0.12f;
      }
      default -> {
        return 0.14f;
      }
    }
  }
}
