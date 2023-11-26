package d3344758.phase_2_3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author Jonathan Hill (d3344758)
 */
public class ItineraryInput {

  private String clientName;
  private String date;
  private int totalPeople;
  private String referenceNumber;
  private List<String> activityCodes = new ArrayList<>();
  private List<String> activities = new ArrayList<>();
  private int totalActivities;
  private String[] exsistingActivities;
  private List<String> itineraryAddons = new ArrayList<>();
  private List<String[]> activityInformation;
  private List<String[]> existingItineraryAddonInformation;

  // TODO JavaDoc
  public void populateExistingData() {
    var readActivities = new FileOperations("activities.txt", true);
    var readItineraryAddons = new FileOperations("addons.txt", true);
    readActivities.checkCreateFile();
    readItineraryAddons.checkCreateFile();
    setActivityInformation(readActivities.readFile());
    setExsistingActivities(new String[getActivityInformation().size()]);
    setExistingItineraryAddonInformation(readItineraryAddons.readFile());

    int counter = 0;  // for adding the items to the array
    for (String[] items : getActivityInformation()) {
      // adding just the activity name to a separate array
      getExsistingActivities()[counter] = items[0];
      counter++;
    }
    setTotalActivities(getExsistingActivities().length);
  }

  // TODO JavaDoc
  private void inputClientName(Scanner userInput) {
    boolean check = true;

    while (check) {
      System.out.println("Please enter the name of the client. Enter as: "
              + "First Initial space last name (ex: J Hill).");
      System.out.println("Please use a maximum of 20 characters.");
      String name = userInput.nextLine();

      if (NameValidation.checkClientName(name)) {
        String checkHappy = UserValidationChecks.checkHappy(userInput, name);

        if (checkHappy.equals("yes")) {
          check = false;
          setClientName(name.trim());
        }
      }
    }
  }

  /**
   * Prompts the user to input the number of people in their party. Catches any errors related to
   * entering: Characters and negative numbers
   *
   * @param userInput The Scanner object for user input.
   */
  // TODO -- might change try catch for regex?
  private void inputTotalPeople(Scanner userInput) {
    boolean isValidInput = false;

    while (!isValidInput) {
      int numberOfPeople = 0;
      try {
        System.out.println("Please enter the number of people in your party.");
        numberOfPeople = userInput.nextInt();
        userInput.nextLine();

        if (numberOfPeople >= 1) {
          if (UserValidationChecks.checkHappy(userInput, numberOfPeople).equals("yes")) {
            isValidInput = true;
            setTotalPeople(numberOfPeople);
            System.out.println("The total people are set as: " + numberOfPeople);
          }
        } else {
          System.out.println("Please ensure you enter a number equal to or greater than 1.");
        }
      } catch (InputMismatchException error) {
        System.out.println("Please enter a valid number. E.g: 5");
        userInput.nextLine();
      }
    }
  }

  /**
   * Prompts the user to input the date for the booked activities.
   *
   * @param userInput The Scanner object for user input.
   */
  private void inputDate(Scanner userInput) {
    boolean isValidInput = true;

    LocalDate today = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    String formattedDate = today.format(formatter);

    while (isValidInput) {
      System.out.printf("""
                        Please enter the date the activities are being booked for. Enter in the format: DD-MM-YYYY.
                        For example, today's date is: %s
                        """, formattedDate);

      String userDateInput = userInput.nextLine().trim();

      try {
        LocalDate userDate = LocalDate.parse(userDateInput, formatter);

        if (userDate.isBefore(today)) {
          System.out.println("The date entered is in the past. Please enter a future date.");
        } else {
          if (UserValidationChecks.checkHappy(userInput, userDateInput).equals("yes")) {
            System.out.println("The date is set for: " + userDate.format(formatter));
            isValidInput = false;
            setDate(userDateInput);
          }
        }
      } catch (DateTimeParseException e) {
        System.out.println("Please ensure you enter a valid date in the format: DD-MM-YYYY");
      }
    }
  }

  /**
   * Generates a reference code consisting of 2 random letters 3 random numbers and a random letter
   * setting the referenceNumber to the 3 joined as a String
   */
  private void clientRefernece() {
    String startLetters = RandomStringUtils.randomAlphabetic(2).toUpperCase();
    String numbers = RandomStringUtils.randomNumeric(3);
    String finalLetter = RandomStringUtils.randomAlphabetic(1).toUpperCase();

    String reference = startLetters + numbers + finalLetter;
    setReferenceNumber(reference);
  }

  private String addonRequired(Scanner userInput, String activityCode) {
    String addons = ActivityAddonsRequired.check(userInput, activityCode);
    
    String codeWithAddons = activityCode + ":" + addons;
    System.out.println(codeWithAddons);
    return codeWithAddons;
  }

  // TODO JavaDoc  --- test -- fix
  public void inputActivities(Scanner userInput) {
    boolean check = true;

    while (check) {
      boolean isValid = true;

      for (int i = 0; i < getTotalActivities(); i++) {
        System.out.print("Activity number " + i + ":  " + getExsistingActivities()[i] + "\t");
        if (i % 3 == 0 && i != 0) {
          System.out.println(""); // To make it easier for the user to read the activities
        }
      }

      try {
        System.out.println("Please enter the activity you would like to add. Pick a number from the list provided.");
        int userChoice = userInput.nextInt();
        userInput.nextLine();

        if (userChoice < getTotalActivities() && userChoice >= 0) {
          String userActivity = getExsistingActivities()[userChoice];

          for (String activity : activities) {
            if (activity.equals(userActivity)) {
              isValid = false;
              break;
            }
          }

          if (isValid) {
            String checkHappy = UserValidationChecks.checkHappy(userInput, userActivity);

            if (checkHappy.equals("yes")) {
              String[] activityInfo = getActivityInformation().get(userChoice);
              String code = activityInfo[1];
              activities.add(getExsistingActivities()[userChoice]);
              System.out.println(code);
              code = addonRequired(userInput, code);
              activityCodes.add(code);
              
              check = UserValidationChecks.addAnother(userInput, "Activity");
            }
          }
        } else {
          System.out.println("Please pick a number from the activities provided.");
        }
      } catch (InputMismatchException error) {
        System.out.println("Please enter a number that has been displayed.");
        userInput.nextLine();
      }
    }
  }

  // TODO JavaDoc  -- test
  private void inputItineraryAddons(Scanner userInput) {
    boolean check = true;

    while (check) {
      System.out.println("""
                             Would you like any Itinerary addons?
                             Please enter the name of the addon you would like as it is written.
                             If you do not require any, enter: none""");

      for (String[] addon : getExistingItineraryAddonInformation()) {
        System.out.println(addon[0].trim());
      }

      String userChoice = userInput.nextLine().toLowerCase();
      
      if (userChoice.equals("none")) {
        check = false;
        break;
      }
      
      if (!userChoice.equals("none")) {
        if (ItineraryAddonErrorChecks.isValidItineraryAddons(userChoice, getItineraryAddons())) {
          if (ItineraryAddonErrorChecks.isHappyWithChoice(userInput, userChoice)) {
            for (String[] addon : getExistingItineraryAddonInformation()) {
              if (addon[0].equals(userChoice)) {
                String addonPrice = addon[1];
                addItineraryAddons(userChoice + ":" + addonPrice);
                break;
              }
            }
          }
        }
      }
    }
  }
  // TODO finish method 

  public void gatherInformation(Scanner userInput, FileOperations file) {
    boolean check = true;

    while (check) {
      inputClientName(userInput);
      inputTotalPeople(userInput);
      inputDate(userInput);
      inputActivities(userInput);
      clientRefernece();
      inputItineraryAddons(userInput);

      System.out.println("\n\n\n"); // Leave space between the reciept and question
      String clientInformation = getClientName() + "\t" + getDate() + "\t" + getReferenceNumber() + "\t"
              + getActivityCodes() + "\t" + getTotalActivities() + "\t" + getTotalPeople();

      file.writeToFile(clientInformation);

      check = UserValidationChecks.addAnother(userInput, "Customers itinerary");
      if (check) {
        // To clear the console to start another intinerary
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
      }
    }

  }

  // ---------- Getters/Setters below ---------- //
  public String getClientName() {
    return clientName;
  }

  public void setClientName(String clientName) {
    this.clientName = clientName;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public int getTotalPeople() {
    return totalPeople;
  }

  public void setTotalPeople(int totalPeople) {
    this.totalPeople = totalPeople;
  }

  public String getReferenceNumber() {
    return referenceNumber;
  }

  public void setReferenceNumber(String referenceNumber) {
    this.referenceNumber = referenceNumber;
  }

  public List<String> getActivityCodes() {
    return activityCodes;
  }

  public void setActivityCodes(List<String> activityCodes) {
    this.activityCodes = activityCodes;
  }

  public int getTotalActivities() {
    return totalActivities;
  }

  public void setTotalActivities(int totalActivities) {
    this.totalActivities = totalActivities;
  }

  public String[] getExsistingActivities() {
    return exsistingActivities;
  }

  public void setExsistingActivities(String[] exsistingActivities) {
    this.exsistingActivities = exsistingActivities;
  }

  public List<String[]> getActivityInformation() {
    return activityInformation;
  }

  public void setActivityInformation(List<String[]> activityInformation) {
    this.activityInformation = activityInformation;
  }

  public List<String> getItineraryAddons() {
    return itineraryAddons;
  }

  public void addItineraryAddons(String info) {
    this.itineraryAddons.add(info);
  }

  public List<String[]> getExistingItineraryAddonInformation() {
    return existingItineraryAddonInformation;
  }

  public void setExistingItineraryAddonInformation(List<String[]> existingItineraryAddonInformation) {
    this.existingItineraryAddonInformation = existingItineraryAddonInformation;
  }

}
