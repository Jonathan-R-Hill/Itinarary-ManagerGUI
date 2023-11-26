package d3344758.phase_2_3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Jonathan Hill (d3344758)
 */
public class ActivityAddonsRequired {

 

  public static String check(Scanner userInput, String code) {
     List<String> addonsAdded = new ArrayList<>();
     
    System.out.println("All activity addons are provided by\n------> Exciting Activities Ltd <------");

    while (true) {
      System.out.println("""
                Please choose the addons you would like if any. 
                For Insurance. Enter: INS  (costs £20.00)
                For Travel. Enter: TRV (costs £2.00)
                For Photography. Enter: PHO  (costs £10.00)
                If none are required. Enter: none""");

      String userChoice = userInput.nextLine().toUpperCase();

      if (userChoice.equals("NONE")) {
        System.out.println("thank you for your input.");
        break;
      } else if (!addonsAdded.contains(userChoice)) {
        boolean validChoice = handleAddonChoice(userInput, code, addonsAdded, userChoice);

        if (validChoice) {
          System.out.println("Addon added.\n");
          addonsAdded.add(userChoice);
        } else {
          System.out.println("Please ensure you are entering a valid item from the list provided.");
        }
      } else {
        System.out.println("Please ensure you are not entering an addon you have already chosen\n");
      }
    }

    return addonsAdded.toString();
  }

  public static boolean handleAddonChoice(Scanner userInput, String activityCode,
          List<String> addonsAdded, String userChoice) {

    if (!addonsAdded.contains(userChoice)) {
      switch (userChoice) {
        case "TRV", "INS", "PHO" -> {
          return true;
        }
      }
    }
    return false;

  }
}
