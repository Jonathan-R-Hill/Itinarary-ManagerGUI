package d3344758.phase_2_3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Jonathan Hill (d3344758)
 */
public class ItineraryInput {

  private String clientName;
  private String date;
  private int totalPeople;
  private String referenceNumber;
  private String[] activityCodes;
  private String[] activities;
  private int totalActivities;
  private String[] exsistingActivities;
  private List<String[]> activityInformation;

  // TODO test and fix method
  public void populateExistingData() {
    FileOperations readActivities = new FileOperations("activities.txt", true);
    readActivities.checkCreateFile();
    setActivityInformation(readActivities.readFile());
    setExsistingActivities(new String[getActivityInformation().size()]);

    int counter = 0;  // for adding the items to the array
    for (String[] items : getActivityInformation()) {
      // adding just the activity name to a separate array
      getExsistingActivities()[counter] = items[0];
      counter++;
    }

    for (String item : getExsistingActivities()) {
      System.out.println(item);
    }
  }

  // TODO Doc String & test
  private void inputClientName(Scanner userInput) {
    boolean check = true;
    String checkHappy = "";

    while (check) {
      System.out.println("""
                       Please enter the name of the client.  Enter as: First Initial space last name.
                       Please use a maximum of 20 characters.""");
      String name = userInput.nextLine();

      if (name.length() <= 20 && name.charAt(1) == ' ') {
        checkHappy = ValidationChecks.checkHappy(userInput, name);
      } else if (name.length() > 20) {
        System.out.println("Please ensure the name is less than 20 characters long. If it is more"
                + "Please shorten the last name.");
      } else {

      }

      if (checkHappy.equals("yes")) {
        check = false;
        setClientName(name);
      }
    }
  }

  // TODO method
  private void inputDate(Scanner userInput) {
    LocalDate today = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
    String formattedDate = today.format(formatter);
  }

  // TODO method
  private void inputTotalPeople(Scanner userInput) {

  }

  // TODO method
  private void clientRefernece(Scanner userInput) {

  }

  // TODO method
  private void inputActivities(Scanner userInput) {

  }

  // TODO method
  private void fetchActivityCodes() {

  }

  // TODO method
  public void gatherInformation() {

  }

  // TODO method
  public void generateReciept() {

  }

  // ---------- Getters/Setters below ---------- //
  public String getClientName() {
    return clientName;
  }

  public void setClientName(String clientName) {
    this.clientName = clientName;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public int getTotalPeople() {
    return totalPeople;
  }

  public void setTotalPeople(int totalPeople) {
    this.totalPeople = totalPeople;
  }

  public String getReferenceNumber() {
    return referenceNumber;
  }

  public void setReferenceNumber(String referenceNumber) {
    this.referenceNumber = referenceNumber;
  }

  public String[] getActivityCodes() {
    return activityCodes;
  }

  public void setActivityCodes(String[] activityCodes) {
    this.activityCodes = activityCodes;
  }

  public int getTotalActivities() {
    return totalActivities;
  }

  public void setTotalActivities(int totalActivities) {
    this.totalActivities = totalActivities;
  }

  public String[] getExsistingActivities() {
    return exsistingActivities;
  }

  public void setExsistingActivities(String[] exsistingActivities) {
    this.exsistingActivities = exsistingActivities;
  }

  public List<String[]> getActivityInformation() {
    return activityInformation;
  }

  public void setActivityInformation(List<String[]> activityInformation) {
    this.activityInformation = activityInformation;
  }

}
