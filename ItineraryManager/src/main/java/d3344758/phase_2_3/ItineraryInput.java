package d3344758.phase_2_3;

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
  private List<String[]> information;

  // TODO method
  public void populateExistingData() {
    FileOperations readActivities = new FileOperations("activities.txt", true);
    readActivities.checkCreateFile();
    setInformation(readActivities.readFile());
    setExsistingActivities(new String[getInformation().size()]);
    
    int counter = 0;  // for adding the items to the array
    for (String[] items : getInformation()) {
      // adding just the activity name to a separate array
      getExsistingActivities()[counter] = items[0];
      counter++;
    }

    for (String item : getExsistingActivities()) {
      System.out.println(item);
    }
  }

  // TODO method
  private void inputClientName(Scanner userInput) {

  }

  // TODO method
  private void inputDate(Scanner userInput) {

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

  public List<String[]> getInformation() {
    return information;
  }

  public void setInformation(List<String[]> information) {
    this.information = information;
  }

}
