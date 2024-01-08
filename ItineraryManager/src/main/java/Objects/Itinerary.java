package Objects;

/**
 *
 * @author Jonathan Hill (d3344758)
 */
public class Itinerary {

  String attendeeName;
  String totalPeople;
  String date;
  String reference;
  String totalCostPence;
  String totalActivities;
  String activityCodes;
  String itineraryAddonNames;

  /**
   * @param attendeeName The name of the lead attendee in the form 'Initial Surname(s)'
   * @param totalPeople The total people in the party
   * @param date the date of booking for the itinerary
   * @param reference The reference String in the form AA123A
   * @param totalCostPence The total cost of the Itinerary booked in pence
   * @param totalActivities The total amount of activities
   * @param activityCodes The codes of each activity that also stores the add-ons
   * @param itineraryAddonNames The names of itinerary add-ons separated by a comma ','
   */
  public Itinerary(String attendeeName, String totalPeople, String date, String reference,
          String totalCostPence, String totalActivities, String activityCodes, String itineraryAddonNames) {
    this.attendeeName = attendeeName;
    this.totalPeople = totalPeople;
    this.date = date;
    this.reference = reference;
    this.totalCostPence = totalCostPence;
    this.totalActivities = totalActivities;
    this.activityCodes = activityCodes;
    this.itineraryAddonNames = itineraryAddonNames;
  }

  public String getAttendeeName() {
    return attendeeName;
  }

  public void setAttendeeName(String attendeeName) {
    this.attendeeName = attendeeName;
  }

  public String getTotalPeople() {
    return totalPeople;
  }

  public void setTotalPeople(String totalPeople) {
    this.totalPeople = totalPeople;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String Date) {
    this.date = Date;
  }

  public String getReference() {
    return reference;
  }

  public void setReference(String reference) {
    this.reference = reference;
  }

  public String getTotalCostPence() {
    return totalCostPence;
  }

  public void setTotalCostPence(String totalCostPence) {
    this.totalCostPence = totalCostPence;
  }

  public String getTotalActivities() {
    return totalActivities;
  }

  public void setTotalActivities(String totalActivities) {
    this.totalActivities = totalActivities;
  }

  public String getActivityCodes() {
    return activityCodes;
  }

  public void setActivityCodes(String activityCodes) {
    this.activityCodes = activityCodes;
  }

  public String getItineraryAddonNames() {
    return itineraryAddonNames;
  }

  public void setItineraryAddonNames(String itineraryAddonNames) {
    this.itineraryAddonNames = itineraryAddonNames;
  }

}
