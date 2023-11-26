package d3344758.phase_2_3;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Jonathan Hill (d3344758)
 */
public class NameValidation {

  // TODO JavaDoc   
  public static boolean checkClientName(String name) {
    boolean isValid = true;

    String[] nameSplit = name.trim().replaceAll("\\s+", " ").split(" ");

    for (String part : nameSplit) {
      if (part.matches("^[a-zA-Z]+$")) {  // regex  ^ = start   $ = end    + = atleast one valid char
        name += StringUtils.capitalize(part) + " ";
      } else {
        System.out.println("Invalid input. Please do not use numbers.");
        isValid = false;
        break;
      }
    }

    if (isValid && name.length() <= 20 && nameSplit.length >= 2) {
      return true;

    } else if (name.length() > 20) {
      System.out.println("Please ensure the name is less than 20 characters long. "
              + "If it is more, please shorten the last name.");
      return false;
    } else {
      return false;
    }
  }
}
