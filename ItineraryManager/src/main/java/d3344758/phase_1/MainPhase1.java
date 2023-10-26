package d3344758.phase_1;

import java.util.Scanner;

/**
 *
 * @author Jonathan Hill (d3344758)
 */
public class MainPhase1 {

  public static void main(String[] args) {
    Scanner userInput = new Scanner(System.in);
    NewActivityInput addActivity = new NewActivityInput();  // created ready t otake input in the GUI
    FileOperations fileHandler = new FileOperations("itineraries.txt", false);  // Create the FileOperations object
    fileHandler.checkCreateFile();  // ensure the file is created if not create it
    

    
  }
}
