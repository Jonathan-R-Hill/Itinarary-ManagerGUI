package ItineraryInput;

import Utility.UserValidationChecks;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author Jonathan Hill (d3344758)
 */
public class ClientDateReference {

  /**
   * Prompts the user to input a date for booking activities. Validates the input to ensure it is in
   * the correct format (DD-MM-YYYY) and represents a future date. Repeats the
   * prompt until a valid date is entered catching any errors when checking the date
   * entered is a valid date even when the user enters the valid format. 
   *
   * @param userInput The Scanner object to read user input.
   * @return A String representing the user-entered date in the format DD-MM-YYYY.
   */
  protected static String inputDate(Scanner userInput) {
    LocalDate today = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    String formattedDate = today.format(formatter);

    while (true) {
      System.out.printf("""
                        Please enter the date the activities are being booked for. Enter in the format: DD-MM-YYYY.
                        For example, today's date is: "%s"
                        """, formattedDate);

      String userDateInput = userInput.nextLine().trim();

      try {
        userDateInput = datePadding(userDateInput);
        LocalDate userDate = LocalDate.parse(userDateInput, formatter);

        if (userDate.isBefore(today) || userDate.isEqual(today)) {
          System.out.println("The date entered is either in the past or today. Please enter a future date.");
        } else if (!isValidDate(userDateInput) || !checkLeapYear(userDateInput)) {
          System.out.println("Please ensure you are entering a valid date.");
        } else {
          if (UserValidationChecks.checkHappy(userInput, userDateInput).equals("yes")) {
            System.out.println("The date is set for: " + userDate.format(formatter));
            return userDateInput;
          }
        }
      } catch (DateTimeParseException e) {
        System.out.println("Please ensure you enter a valid date in the format: DD-MM-YYYY");
      } catch (DateTimeException e) {
        System.out.println("Please ensure you enter a valid date in the format: DD-MM-YYYY");
      }
    }
  }

  /**
   * Take the users date and applies padding if they only entered 1 number by splitting the data and
   * looping over the split data changing anything that is length 1 to length 2 by adding a 0 in
   * front to apply the correct format.
   *
   *
   * @param date The user date entered to have padding added
   * @return String of The formatted date in the form DD-MM-YYYY
   */
  private static String datePadding(String date) {
    String[] splitDate = date.split("-");
    List<String> newDate = new ArrayList<>();

    for (String part : splitDate) {
      if (part.length() == 1) {
        part = "0" + part;
        newDate.add(part);
      } else {
        newDate.add(part);
      }
    }

    String paddedDate = String.join("-", newDate);
    return paddedDate;
  }

  /**
   * Checks the date is a valid date.
   *
   * @param date The users date selection in the format DD-MM-YYYY
   * @return (boolean) true if the date is valid; false if invalid
   */
  private static boolean isValidDate(String date) {
    String[] splitDate = date.split("-");
    int userDay = Integer.parseInt(splitDate[0]);
    int userMonth = Integer.parseInt(splitDate[1]);

    if (userDay >= 1 && userDay <= Month.of(userMonth).maxLength() && userMonth >= 1 && userMonth <= 12) {
      return true;
    }
    return false;
  }

  /**
   * Checks if the month is Feb and checks if it is a leap year if the user enters a day that is
   * after the 28th and returns true/false depending on if it is a leap year if both of these
   * conditions are true. returns true if the date is before the 28th or the month is not Feb (2)
   *
   * @param date The users date selection in the format DD-MM-YYYY
   * @return (Boolean) true if the date is valid; false if invalid
   */
  private static boolean checkLeapYear(String date) {
    String[] splitDate = date.split("-");

    int userDate = Integer.parseInt(splitDate[0]);
    int userMonth = Integer.parseInt(splitDate[1]);
    int userYear = Integer.parseInt(splitDate[2]);

    if (userMonth == 2 && userDate > 28) {
      return ((userYear % 4 == 0 && userYear % 100 != 0) || userYear % 400 == 0);
    }
    return true;
  }

  /**
   * Generates a reference code consisting of 2 random letters 3 random numbers and a random letter
   * setting the referenceNumber to the 3 joined as a String
   *
   * @return (String) containing the client reference code
   */
  protected static String clientReference() {
    String startLetters = RandomStringUtils.randomAlphabetic(2).toUpperCase();
    String numbers = RandomStringUtils.randomNumeric(3);
    String finalLetter = RandomStringUtils.randomAlphabetic(1).toUpperCase();

    String reference = startLetters + numbers + finalLetter;
    return reference;
  }
}
