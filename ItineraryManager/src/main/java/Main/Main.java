package Main;

import java.util.Scanner;

/**
 *
 * @author Jonathan Hill (d3344758)
 */
public class Main {
  
  public static Scanner userInput = new Scanner(System.in);
  
  public static void main(String[] args) {
    // One day we might be able to use System.out.println in one word that isn't a method. That'd be great
    System.out.println("""
                       \tWelcome to the Itinerary manager console!
                       You can chose to create a new activity, Activity add-on,
                       Itinerary add-on or create a customers itinerary here\n""");
    ProgramChoice.userChoice(userInput);
  }
}
