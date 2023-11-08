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
  private List<String[]> activityInformation;

  // TODO JavaDoc
  public void populateExistingData() {
    FileOperations readActivities = new FileOperations("activities.txt", true);
    readActivities.checkCreateFile();
    setActivityInformation(readActivities.readFile());
    setExsistingActivities(new String[getActivityInformation().size()]);

    int counter = 0;  // for adding the items to the array
    for (String[] items : getActivityInformation()) {
      // adding just the activity name to a separate array
      getExsistingActivities()[counter] = items[0];
      counter++;
    }
  }

  // TODO Doc String maybe Title case?
  private void inputClientName(Scanner userInput) {
    boolean check = true;
    String checkHappy = "";

    while (check) {
      System.out.println("""
                       Please enter the name of the client.  Enter as: First Initial space last name (ex: J Hill).
                       Please use a maximum of 20 characters .""");
      String name = userInput.nextLine();

      if (name.length() <= 20 && name.charAt(1) == ' ') {
        checkHappy = ValidationChecks.checkHappy(userInput, name);
      } else if (name.length() > 20) {
        System.out.println("Please ensure the name is less than 20 characters long. If it is more"
                + " Please shorten the last name.");
      }

      if (checkHappy.equals("yes")) {
        check = false;
        setClientName(name);
      }
    }
  }

  // TODO JavaDoc maybe padding?
  private void inputDate(Scanner userInput) {
    boolean check = true;

    LocalDate today = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    String formattedDate = today.format(formatter);

    while (check) {
      String checkHappy = "";

      System.out.println("Please enter the date the activities are being booked for. "
              + "Enter in the format: DD-MM-YYYY. for Example todays date is: " + formattedDate);
      String userDateInput = userInput.nextLine().trim();

      try {
        LocalDate userDate = LocalDate.parse(userDateInput, formatter);

        if (userDate.isBefore(today)) {
          System.out.println("The date entered is in the past. Please enter a future date.");
        } else {
          checkHappy = ValidationChecks.checkHappy(userInput, userDateInput);

          if (checkHappy.equals("yes")) {
            System.out.println("The date is set for: " + userDate.format(formatter));
            check = false;
            setDate(userDateInput);
          }
        }
      } catch (DateTimeParseException e) {
        System.out.println("Please ensure you enter the date in the correct format: DD-MM-YYYY");
      }
    }
  }

  // TODO JavaDoc
  private void inputTotalPeople(Scanner userInput) {
    boolean check = true;
    int numberOfPeople = 0;

    while (check) {
      String checkHappy = "";
      try {
        System.out.println("Please enter the amount of people in your party.");
        numberOfPeople = userInput.nextInt();
        userInput.nextLine();

        if (numberOfPeople >= 1) {
          checkHappy = ValidationChecks.checkHappy(userInput, numberOfPeople);
        } else {
          System.out.println("Please ensure you are entering a number equal to or greater than 1");
        }

        if (checkHappy.equals("yes")) {
          check = false;
          setTotalPeople(numberOfPeople);
          System.out.println("The total people is set as: " + numberOfPeople);
        }
      } catch (InputMismatchException error) {
        System.out.println("Please enter as a number. EG: 5");
        numberOfPeople = 0;
        userInput.nextLine();
      }
    }
  }

  // TODO JavaDoc
  private void clientRefernece() {
    String startLetters = RandomStringUtils.randomAlphabetic(2).toUpperCase();
    String numbers = RandomStringUtils.randomNumeric(3);
    String finalLetter = RandomStringUtils.randomAlphabetic(1).toUpperCase();

    String reference = startLetters + numbers + finalLetter;
    setReferenceNumber(reference);
  }

  // TODO JavaDoc
  private void inputActivities(Scanner userInput) {
    boolean check = true;
    int totalActivities = getExsistingActivities().length;
    String checkHappy = "";

    while (check) {
      for (int i = 0; i < totalActivities; i++) {
        System.out.print("Activity number " + i + ":  " + getExsistingActivities()[i] + "\t");
        if (i % 3 == 0 && i != 0) {
          System.out.println("");
        }
      }

      try {
        System.out.println("Please enter the activity you would like to add. "
                + "Pick a number from the list provided.");
        int userChoice = userInput.nextInt();
        userInput.nextLine();

        if (userChoice <= totalActivities && userChoice >= 0) {
          String userActivity = getExsistingActivities()[userChoice];

          checkHappy = ValidationChecks.checkHappy(userInput, userActivity);
        } else {
          System.out.println("Please pick a number from the activities provided.");
        }

        if (checkHappy.equals("yes")) {
          String[] activityInfo = getActivityInformation().get(userChoice);
          String code = activityInfo[1];
          activities.add(getExsistingActivities()[userChoice]);
          activityCodes.add(code);

          check = ValidationChecks.addAnother(userInput, "Activity");

        }
      } catch (InputMismatchException error) {
        System.out.println("Please enter a number that has been displayed.");
        userInput.nextLine();
      }
    }
  }

  // TODO method
  public void gatherInformation() {

  }

  // TODO method
  public void generateReciept() {

    System.out.println("+------------------------------+");
    System.out.printf("| %-30s%-5s |\n", "PH", "PH");
    System.out.println("+------------------------------+");
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

}
