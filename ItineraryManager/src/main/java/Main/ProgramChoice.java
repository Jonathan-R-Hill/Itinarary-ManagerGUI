package Main;

import AdminCreation.NewActivityInput;
import AdminCreation.NewAddonInput;
import AdminCreation.NewItineraryAddon;

import ItineraryReceiptGeneration.GenerateReceipt;
import ItineraryInput.ItineraryMain;

import Objects.Activity;
import Objects.ActivityAddon;
import Objects.ItineraryAddon;
import Utility.FileOperations;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Jonathan Hill (d3344758)
 */
public class ProgramChoice {

  public static List<Activity> activityData;
  public static List<ActivityAddon> activityAddons;
  public static List<ItineraryAddon> itineraryAddons;

  /**
   * Asks the user to chose from a list provided to get what they want to do. Will loop the input
   * until they enter a valid option then it will run the necessary method to execute that request.
   *
   * @param userInput A Scanner used to take in input from the user
   */
  protected static void userChoice(Scanner userInput) {
    String staffChoice = "";

    while (!staffChoice.equals("custom")
            && !staffChoice.equals("activity")
            && !staffChoice.equals("activity addon")
            && !staffChoice.equals("itinerary addon")) {

      System.out.println("""
                         To add a new activity, enter: activity
                         To add a new activity add-on, enter: activity addon
                         To add a customers itinarary enter: custom
                         To add a new itinerary addon, enter: itinerary addon
                         """);
      staffChoice = userInput.nextLine().toLowerCase();
    }

    switch (staffChoice) {
      case "custom" ->
        custom(userInput);
      case "activity" ->
        activity(userInput);
      case "activity addon" ->
        activityAddon(userInput);
      case "itinerary addon" ->
        itineraryAddon(userInput);
    }
  }

  /**
   * TODO finish
   *
   * @param userInput A Scanner used to take in input from the user
   */
  private static void custom(Scanner userInput) {
    // File IO Operations
    var generateActivities = new FileOperations("activities.txt", false);
    var generateActivityAddons = new FileOperations("addons.txt", false);
    var generateItineraryAddons = new FileOperations("itineraryAddons.txt", false);
    var saveItineraryInfo = new FileOperations("itineraries.txt", false);
    
    saveItineraryInfo.checkCreateFile();
    generateActivities.checkCreateFile();
    generateActivityAddons.checkCreateFile();
    generateItineraryAddons.checkCreateFile();
    
    // Populating exisisting data
    activityData = generateActivities.readActivityFile();
    activityAddons = generateActivityAddons.readAddonFile();
    itineraryAddons = generateItineraryAddons.readItineraryAddonFile();

    // Itinerary collection Operations
    var customerInput = new ItineraryMain();
    customerInput.collectInformation(userInput);

    // Receipt Generation 
    var customerReceipt = new GenerateReceipt(customerInput);
    int totalCost = (int) customerReceipt.generateReceipt() * 100;
    customerInput.setTotalCost(totalCost); // update after receipt
    customerInput.writeToFile(saveItineraryInfo);
  }

  /**
   * Creates the necessary objects related to creating a new activity. Ensures that the file for
   * storing activities exists, if not it is created. Once all of the objects are created it runs
   * the colelctInformation method to create a new activity and save it to file.
   *
   * @param userInput A Scanner used to take in input from the user
   */
  private static void activity(Scanner userInput) {
    var addActivity = new NewActivityInput();
    var addActivityToFile = new FileOperations("activities.txt", false);
    addActivityToFile.checkCreateFile();
    addActivity.collectInformation(userInput, addActivityToFile);
  }

  /**
   * Creates the necessary objects related to creating a new activity. Ensures that the file for
   * storing add-ons exists, if not it is created. Once all of the objects are created it runs the
   * addonCreation method to create a new add-on and save it to the file
   *
   * @param userInput A Scanner used to take in input from the user
   */
  private static void activityAddon(Scanner userInput) {
    var addAddon = new NewAddonInput();
    var addAddonToFile = new FileOperations("addons.txt", false);
    addAddonToFile.checkCreateFile();
    addAddon.addonCreation(userInput, addAddonToFile);
  }

  private static void itineraryAddon(Scanner userInput) {
    var addItineraryAddon = new NewItineraryAddon();
    var addAddonToFile = new FileOperations("itineraryAddons.txt", false);
    addAddonToFile.checkCreateFile();
    addItineraryAddon.collectInformation(userInput, addAddonToFile);
  }

}
