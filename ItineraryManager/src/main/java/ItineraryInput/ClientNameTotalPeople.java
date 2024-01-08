package ItineraryInput;

import Utility.UserValidationChecks;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Jonathan Hill (d3344758)
 */
public class ClientNameTotalPeople {

  /**
   * Prompts the user to input the name of the client and validates the input
 Using the 'ClientNameValidation' class
   *
   * @param userInput The Scanner object for reading user input.
   * @return The validated and formatted client name.
   */
  protected static String inputClientName(Scanner userInput) {

    while (true) {
      System.out.println("Please enter the name of the client. Enter as: "
              + "First Initial space last name (ex: J Hill).");
      System.out.println("Please use a maximum of 20 characters.");
      String name = userInput.nextLine();

      if (ClientNameValidation.checkClientName(name)) {
        name = ClientNameValidation.convertToTitleCase(name);
        String checkHappy = UserValidationChecks.checkHappy(userInput, name);

        if (checkHappy.equals("yes")) {
          return name.trim();
        }
      }
    }
  }

  /**
   * Prompts the user to input the number of people in a party and performs validation. The method
   * ensures that the entered value is a positive integer and handles input errors such as characters.
   *
   * @param userInput A Scanner object used to read input from the user.
   * @return The total number of people in the party once it has been validated.
   */
  protected static int inputTotalPeople(Scanner userInput) {

    while (true) {
      int numberOfPeople = 0;
      try {
        System.out.println("Please enter the number of people in your party.");
        numberOfPeople = userInput.nextInt();
        userInput.nextLine();

        if (numberOfPeople >= 1 && numberOfPeople < 100 ) {
          if (UserValidationChecks.checkHappy(userInput, numberOfPeople).equals("yes")) {
            System.out.println("The total people are set as: " + numberOfPeople);
            return numberOfPeople;
          }

        } else {
          System.out.println("Please ensure you enter a number equal to or greater than 1 and less than 100.");
        }
      } catch (InputMismatchException error) {
        System.out.println("Please enter a valid whole number. E.g: 5");
        userInput.nextLine();
      }
    }
  }

}
