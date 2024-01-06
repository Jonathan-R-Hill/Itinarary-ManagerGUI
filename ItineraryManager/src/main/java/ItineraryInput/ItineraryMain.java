package ItineraryInput;

import Objects.ItineraryAddon;
import Utility.FileOperations;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ItineraryMain {

  private String clientName;
  private int totalPeople;
  private String date;
  private String referenceNumber;
  private List<String> activities = new ArrayList<>();
  private List<String> activityCodes = new ArrayList<>();
  private List<ItineraryAddon> itineraryAddons = new ArrayList<>();
  private int totalCost = 0;

  /**
   * Run the necessary methods to collect user data and sets the class variables for later use.
   * As well as creating spaces between each method call to make it easier to
   * read console output for the user.
   * 
   * @param userInput A Scanner Object used to collect user input
   */
  public void collectInformation(Scanner userInput) {
    var activityInformation = new ClientActivities();

    setClientName(ClientNameTotalPeople.inputClientName(userInput));
    System.out.println("\n\n");
    setTotalPeople(ClientNameTotalPeople.inputTotalPeople(userInput));
    System.out.println("\n\n");

    setDate(ClientDateReference.inputDate(userInput));
    System.out.println("\n\n");
    setReferenceNumber(ClientDateReference.clientReference());
    System.out.println("\n\n");

    activityInformation.collectUserActivityChoices(userInput);
    setActivities(activityInformation.getActivities());
    setActivityCodes(activityInformation.getActivityCodes());
    System.out.println("\n\n");
    setItineraryAddons(ClientItineraryAddons.collectUserChoice(userInput));
    System.out.println("\n\n");
  }
  
  /**
   * Collects all information as a tab separated String then writes that data to
   * A file using the 'FileOperations' object passed in as a parameter. 
   * 
   * @param file A FileOperations Object that will be used to write data to a file
   */
  public void writeToFile(FileOperations file) {
    int totalActivities = getActivities().size();

    String information = getReferenceNumber() + "\t" + getDate() + "\t" + getClientName() + "\t"
            + getTotalCost() + "\t" + totalActivities  + "\t" + getActivityCodes() + "\t" + getTotalPeople();
    
    file.writeToFile(information);
  }

  // ---------- Getters/Setters below ---------- //
  public String getClientName() {
    return clientName;
  }

  private void setClientName(String clientName) {
    this.clientName = clientName;
  }

  public String getDate() {
    return date;
  }

  private void setDate(String date) {
    this.date = date;
  }

  public int getTotalPeople() {
    return totalPeople;
  }

  private void setTotalPeople(int totalPeople) {
    this.totalPeople = totalPeople;
  }

  public String getReferenceNumber() {
    return referenceNumber;
  }

  private void setReferenceNumber(String referenceNumber) {
    this.referenceNumber = referenceNumber;
  }

  public List<String> getActivities() {
    return activities;
  }

  private void setActivities(List<String> activities) {
    this.activities = activities;
  }

  public List<String> getActivityCodes() {
    return activityCodes;
  }

  private void setActivityCodes(List<String> activityCodes) {
    this.activityCodes = activityCodes;
  }

  public List<ItineraryAddon> getItineraryAddons() {
    return itineraryAddons;
  }

  private void setItineraryAddons(List<ItineraryAddon> itineraryAddons) {
    this.itineraryAddons = itineraryAddons;
  }

  public int getTotalCost() {
    return totalCost;
  }

  public void setTotalCost(int totalCost) {
    this.totalCost += totalCost;
  }

}
