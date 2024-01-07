package Main;

import AdminCreation.NewActivityInput;
import AdminCreation.NewAddonInput;
import AdminCreation.NewItineraryAddon;
import GUI.GuiMain;

import ItineraryReceiptGeneration.GenerateReceipt;
import ItineraryInput.ItineraryMain;

import Objects.Activity;
import Objects.ActivityAddon;
import Objects.Itinerary;
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
            && !staffChoice.equals("itinerary addon")
            && !staffChoice.equals("gui")) {

      System.out.println("""
                         To add a new activity, enter: activity
                         To add a new activity add-on, enter: activity addon
                         To add a new itinerary addon, enter: itinerary addon
                         To add a customers itinarary enter: custom
                         To load the existing itineraries, enter: gui
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
      case "gui" ->
        gui();
    }
  }

  /**
   * Creates the necessary objects to handle file IO operations for collecting data to carry out the 
   * methods required to collect data from the user. Ensures the files required exist before attempting to
   * read data from the files specified in the file objects. 
   * The method then creates an itinerary Input object to collect the data and store it temporarily before
   * saving to file.
   * Once the user has finished filling out their required details, we create a receipt object and generate the
   * receipt based on the information they have entered into the temporary object.
   * Once all of this is complete we update the total cost and save all of the data provided to a file.
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
  
  /**
   * Creates the necessary objects related to creating a new Itinerary Add-on. Ensures that the file for
   * storing the add-on exists, if not it is created. Once all of the objects are created it runs the
   * collectInformation method passing in the FileOperations Object to save the data to file once 
   * the user is done entering the information required.
   * 
   * @param userInput A Scanner used to take in input from the user
   */
  private static void itineraryAddon(Scanner userInput) {
    var addItineraryAddon = new NewItineraryAddon();
    var addAddonToFile = new FileOperations("itineraryAddons.txt", false);
    addAddonToFile.checkCreateFile();
    addItineraryAddon.collectInformation(userInput, addAddonToFile);
  }
  
  /**
   * TODO JavDoc complete method
   */
  private static void gui() {
    var readItineraries = new FileOperations("itineraries.txt", false);
    readItineraries.checkCreateFile();
    
    var gui = new GuiMain(readItineraries.readItineraryFile());
    gui.main();
    
  }

}
