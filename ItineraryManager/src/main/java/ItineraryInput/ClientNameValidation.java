package ItineraryInput;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Jonathan Hill (d3344758)
 */
public class ClientNameValidation {

  /**
   * Checks the validity of a client's name. The name should contain at least two parts, only
   * alphabetical characters, and the total length should be less than or equal to 20 characters.
   *
   * @param name The name to be validated.
   * @return True if the name is valid; false otherwise.
   */
  public static boolean checkClientName(String name) {
    boolean isValid = true;
    String newName = "";
    String[] nameSplit = name.trim().replaceAll("\\s+", " ").split(" ");
    // regex.. 마음에 들지 않지만, 마음에 들어요
    if (nameSplit[0].length() > 1) {
      nameSplit[0] = nameSplit[0].substring(0, 1);
    }
    
    for (String part : nameSplit) {
      if (part.matches("^[a-zA-Z]+$")) {  // regex  ^ = start   $ = end    + = atleast one valid char
        newName += StringUtils.capitalize(part) + " ";
      } else {
        System.out.println("Invalid input. Please do not use numbers.");
        isValid = false;
        break;
      }
    }

    if (isValid && newName.length() <= 20 && nameSplit.length >= 2) {
      return true;

    } else if (name.length() > 20) {
      System.out.println("Please ensure the name is less than 20 characters long. "
              + "If it is more, please shorten the last name.");
      return false;
    } else {
      return false;
    }
  }
  
  /**
   * Splits the name provided to capitalise and collect the first initial of the clients name and capitalise
   * the start of the clients second name. 
   * 
   * @param name The name input by the user
   * @return The clients name with the first letters capitalised in the format: Initial LastName
   */
  public static String convertToTitleCase(String name) {
    String[] splitName = name.split(" ");
    String newName = "";

    if (splitName[0].length() > 1) {
      splitName[0] = splitName[0].substring(0, 1);
    }

    for (String part : splitName) {
      newName += StringUtils.capitalize(part) + " ";
    }

    return newName;
  }
}
