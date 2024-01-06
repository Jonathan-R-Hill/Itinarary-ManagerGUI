package Main;

import java.util.Scanner;

/**
 *
 * @author Jonathan Hill (d3344758)
 */
public class Main {

  private static Scanner userInput = new Scanner(System.in);

  public static void main(String[] args) {
    System.out.println("Welcome to the Itinerary manager console. "
            + "You can chose to create a new activity, addon or create a customers itinerary here");
    ProgramChoice.userChoice(userInput);
  }
}
