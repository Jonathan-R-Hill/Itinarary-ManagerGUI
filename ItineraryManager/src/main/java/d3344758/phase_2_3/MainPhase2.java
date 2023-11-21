package d3344758.phase_2_3;

import java.util.Scanner;

/**
 *
 * @author Jonathan Hill (d3344758)
 */
public class MainPhase2 {

  public static Scanner userInput = new Scanner(System.in);
  private static String staffChoice = "";

  private static void userChoice(Scanner userInput) {

    while (!staffChoice.equals("custom") && !staffChoice.equals("admin")) {
      System.out.println("To add a new activity, enter: admin. To add a customers itinarary enter: custom");
      staffChoice = userInput.nextLine();
    }

    if (staffChoice.equals("custom")) {
      var customerInput = new ItineraryInput();
      var addItineryToFile = new FileOperations("itineraries.txt", false);
      addItineryToFile.checkCreateFile();
      customerInput.populateExistingData();
      customerInput.gatherInformation(userInput, addItineryToFile);
    } else {
      var addActivity = new NewActivityInput();
      var addActivityToFile = new FileOperations("activities.txt", false);
      addActivityToFile.checkCreateFile();
      addActivity.collectInformation(userInput, addActivityToFile);
    }
  }

  public static void main(String[] args) {
    System.out.println("Welcome to the Itinarary manager console. "
            + "You can chose to create a new activity or create a customers itninerary here");
    userChoice(userInput);
  }
}
