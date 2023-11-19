package d3344758.phase_2_3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

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
    FileOperations readActivities = new FileOperations("activities.txt", true);
    FileOperations readItineraryAddons = new FileOperations("addons.txt", true);
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
  }

  // TODO JavaDoc   --I really hate regex
  private void inputClientName(Scanner userInput) {
    boolean check = true;
    String checkHappy = "";

    while (check) {
      System.out.println("Please enter the name of the client. Enter as: "
              + "First Initial space last name (ex: J Hill).");
      System.out.println("Please use a maximum of 20 characters.");
      String name = userInput.nextLine();

      String[] nameSplit = name.trim().replaceAll("\\s+", " ").split(" ");  // Replaces multiple spaces with just one
      name = "";
      boolean isValid = true;

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
        checkHappy = ValidationChecks.checkHappy(userInput, name);
        if (checkHappy.equals("yes")) {
          check = false;
          setClientName(name.trim());
        }
      } else if (name.length() > 20) {
        System.out.println("Please ensure the name is less than 20 characters long. "
                + "If it is more, please shorten the last name.");
      }
    }
  }

  // TODO JavaDoc
  private void inputDate(Scanner userInput) {
    boolean check = true;

    LocalDate today = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    String formattedDate = today.format(formatter);

    while (check) {
      String checkHappy = "";

      System.out.printf("""
                        Please enter the date the activities are being booked for. Enter in the format: DD-MM-YYYY.
                        For Example todays date is: %s
                        """, formattedDate);
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

  // TODO JavaDoc   --- Might re-write
  private String addonRequired(Scanner userInput, String activityCode) {
    boolean check = true;
    String code = activityCode;
    ArrayList<String> addonsAdded = new ArrayList<>();
    int addonCounter = addonsAdded.size();

    try {
      String[] splitCode = code.split(":");
      String[] addonsSplit = splitCode[1].split(",");

      if (splitCode.length != 1) {
        addonsAdded.addAll(Arrays.asList(addonsSplit));
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Error within addonRequired array splitting\nMoving on");
      System.out.println(e);
    }

    System.out.println("All activity addons are provided by\n------> Exciting Activities Ltd <------");
    while (check) {
      String checkHappy = "";
      boolean isValid = true;

      System.out.println("""
                       Please choose the addons you would like if any. 
                       For Insurance. Enter: INS  (costs £20.00)
                       For Travel. Enter: TRN (costs £2.00)
                       For Photography. Enter: PHO  (costs £10.00)
                       If none are required. Enter: none""");
      String userChoice = userInput.nextLine().toUpperCase();

      for (String addon : addonsAdded) {
        if (userChoice.equals(addon)) {
          isValid = false;
          break;
        }
      }

      if (isValid) {
        switch (userChoice) {
          case "TRN" -> {
            checkHappy = ValidationChecks.checkHappy(userInput, "Travel (TVN)");
            if (checkHappy.equals("yes")) {
              if (addonCounter == 0) {
                code += ":";
              }
              code += "TRN,";
              addonCounter += 1;
              addonsAdded.add("TRN,");
              check = ValidationChecks.addAnother(userInput, "addon");
            }
          }
          case "INS" -> {
            checkHappy = ValidationChecks.checkHappy(userInput, "Insurance (INS)");
            if (checkHappy.equals("yes")) {
              if (addonCounter == 0) {
                code += ":";
              }
              code += "INS,";
              addonCounter += 1;
              addonsAdded.add("INS,");
              check = ValidationChecks.addAnother(userInput, "addon");
            }
          }
          case "PHO" -> {
            checkHappy = ValidationChecks.checkHappy(userInput, "Photography (PHO)");
            if (checkHappy.equals("yes")) {
              if (addonCounter == 0) {
                code += ":";
              }
              code += "PHO,";
              addonCounter += 1;
              addonsAdded.add("PHO,");
              check = ValidationChecks.addAnother(userInput, "addon");
            }
          }
          case "NONE" -> {
            check = false;
          }
          default -> {
            System.out.println("Please ensure you enter one of the codes OR none");
          }

        }
      } else {
        System.out.println("That has already been addon to the Activity.");
      }

    }
    return code;
  }

  // TODO JavaDoc  Duplcation Checking? 
  private void inputActivities(Scanner userInput) {
    boolean check = true;
    int totalActivities = getExsistingActivities().length;
    String checkHappy = "";

    while (check) {
      for (int i = 0; i < totalActivities; i++) {
        System.out.print("Activity number " + i + ":  " + getExsistingActivities()[i] + "\t");
        if (i % 3 == 0 && i != 0) {
          System.out.println(""); // To make it easier for the user to read the activities
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

          code = addonRequired(userInput, code);
          activityCodes.add(code);

          check = ValidationChecks.addAnother(userInput, "Activity");
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
      boolean isValid = true;
      String checkHappy = "";
      String addonPrice = "";

      System.out.println("""
                         Would you like any Itinerary addons?
                         Please enter the name of the addon you would like as it is wrote
                         If you do not require any. Enter: none""");
      for (String[] addon : getExistingItineraryAddonInformation()) {
        System.out.println(addon[0].trim());
      }
      String userChoice = userInput.nextLine().toLowerCase();

      if (!getItineraryAddons().isEmpty()) {
        for (String addon : getItineraryAddons()) {
          String[] addonSplit = addon.split(":");
          if (userChoice.equals(addonSplit[0])) {
            isValid = false;
            System.out.printf("\"%s\" has already been added. Please chose a different option.\n", userChoice);
            break;
          }
        }
      }

      if (isValid) {
        checkHappy = ValidationChecks.checkHappy(userInput, userChoice);

        if (checkHappy.equals("yes")) {
          for (String[] addon : getExistingItineraryAddonInformation()) {
            if (addon[0].equals(userChoice)) {
              addonPrice = addon[1];
              break;
            }
            System.out.println(addon[0]);
          }
          setItineraryAddons(userChoice + ":" + addonPrice);
        }
      }

    }
  }

  // TODO finish method
  private String[] calcActivityCosts(List<String> activityCodes) {
    String[] activityCosts = new String[activityCodes.size()];

    int count = 0;
    for (String info : activityCodes) {
      String[] splitCode = info.split(":");
      String code = splitCode[0];

      for (String[] existingInfo : getActivityInformation()) {
        String codeInExisting = existingInfo[1];
        String priceInExisting = existingInfo[5];

        if (code.equals(codeInExisting)) {
          activityCosts[count] = priceInExisting;
          count++;
          break;
        }
      }

    }

    return activityCosts;
  }

  //TODO method   
  private String[] calcItineraryAddOns(List<String> itineraryAddons) {
    String[] addonCosts = new String[getItineraryAddons().size()];

    return addonCosts;
  }

  // TODO method 
  private void calculateCosts() {

  }

  // TODO method
  private void generateReciept() {
    calculateCosts();

    System.out.println("+" + "-".repeat(25) + "+");
    System.out.printf("| %-20s%-5s |\n", "PH", "PH");
    System.out.println("+" + "-".repeat(25) + "+");
  }

  // TODO finish method test write to file 
  public void gatherInformation(Scanner userInput, FileOperations file) {
    boolean check = true;

    while (check) {
      inputClientName(userInput);
      inputTotalPeople(userInput);
      inputDate(userInput);
      inputActivities(userInput);
      clientRefernece();

      generateReciept();
      System.out.println("\n\n\n"); // Leave space between the reciept and question
      String clientInformation = getClientName() + getDate() + getReferenceNumber()
              + getActivityCodes() + getTotalActivities() + getTotalPeople();

      file.writeToFile(clientInformation);

      check = ValidationChecks.addAnother(userInput, "Customers itinerary");
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

  public void setItineraryAddons(String info) {
    this.itineraryAddons.add(info);
  }

  public List<String[]> getExistingItineraryAddonInformation() {
    return existingItineraryAddonInformation;
  }

  public void setExistingItineraryAddonInformation(List<String[]> existingItineraryAddonInformation) {
    this.existingItineraryAddonInformation = existingItineraryAddonInformation;
  }

}
