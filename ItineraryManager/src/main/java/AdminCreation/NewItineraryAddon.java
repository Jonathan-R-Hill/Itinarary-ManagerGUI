package AdminCreation;

import Utility.FileOperations;
import Utility.UserValidationChecks;
import java.util.InputMismatchException;

import java.util.Scanner;

/**
 *
 * @author Jonathan Hill (d3344758)
 */
public class NewItineraryAddon {

  private String itineraryAddonName;
  private int itineraryAddonPrice;

  private void itineraryName(Scanner userInput) {
    while (true) {
      System.out.println("Please enter the name of the Itinerary add-on you would like to create. ");
      String addonName = userInput.nextLine();

      String checkHappy = UserValidationChecks.checkHappy(userInput, addonName);
      if (checkHappy.equals("yes")) {
        setItineraryAddonName(addonName);
        break;
      }
    }
  }

  private void itineraryPrice(Scanner userInput) {
    while (true) {
      try {
        System.out.println("Please enter the cost of your addon.");
        float userPrice = userInput.nextFloat();
        userInput.nextLine();

        String checkHappy = UserValidationChecks.checkHappy(userInput, userPrice);
        if (checkHappy.equals("yes")) {
          float priceToAdd = userPrice * 100;
          setItineraryAddonPrice((int) priceToAdd);
          break;
        }
      } catch (InputMismatchException e) {
        System.out.println("Please ensure you enter a number.");
        userInput.nextLine();
      }
    }
  }

  public void collectInformation(Scanner userInput, FileOperations file) {
    itineraryName(userInput);
    itineraryPrice(userInput);
    
    String checkHappy = UserValidationChecks.checkHappy(userInput, "\nItinerary Add-on Name: " + itineraryAddonName +
            "\nItinerary Add-on Price: " + (itineraryAddonPrice / 100));
    if (checkHappy.equals("yes")) {

      String infoString = itineraryAddonName + "\t" + itineraryAddonPrice;
      file.writeToFile(infoString);

      if (UserValidationChecks.addAnother(userInput, "Itinerary Addon")) {
        collectInformation(userInput, file);
      } else {
        System.out.println("Exiting Program");
      }
    }

  }

  private void setItineraryAddonName(String itineraryAddonName) {
    this.itineraryAddonName = itineraryAddonName;
  }

  private void setItineraryAddonPrice(int itineraryAddonPrice) {
    this.itineraryAddonPrice = itineraryAddonPrice;
  }
}
