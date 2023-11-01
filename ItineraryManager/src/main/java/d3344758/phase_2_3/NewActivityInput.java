package d3344758.phase_2_3;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Jonathan Hill (d3344758)
 */
public class NewActivityInput {

  private String activityName;
  private String activityCode;
  private float baseCost;
  private String location;
  private float expectedDuration;
  private String days;
  private String startTime;
  private String generalDescription;

  private void activityTitle(Scanner userInput) {
    /**
     * Prompts the user to enter the name of their activity through the provided
     * Scanner object. The user is given the option to confirm or change the
     * entered activity name. If the user confirms the name by typing "yes", the
     * provided activity name is accepted and stored. If the user types "no",
     * they can re-enter the activity name. The method loops until the user
     * confirms the entered activity name.
     *
     * @param userInput A Scanner object to read user input.
     */
    boolean check = true;

    while (check) {
      System.out.println("Please enter the name of your activity");
      String activityname = userInput.nextLine();

      String checkHappy = CheckHappy.checkHappy(userInput, activityname);

      if (checkHappy.equals("yes")) {
        check = false;
        setActivityName(activityname);
      } else {
        checkHappy = "";
      }
    }
    System.out.println();
  }

  private void activityCode(Scanner userInput) {
    /**
     * Prompts the user to enter the activity code in the format: abc-00 (3
     * letters hyphen 2 digits). Validates the input and provides an option to
     * confirm or change the entered activity code. The activity code must
     * consist of 3 letters, a hyphen, and 2 digits.
     *
     * @param userInput A Scanner object to read user input.
     */
    boolean check = true;
    String checkHappy = "";

    while (check) {
      System.out.println("Please enter the activity code in the format: abc-00 (3 letters hyphen 2 digits)");
      String activityCode = userInput.nextLine().strip().trim().toUpperCase();

      String[] splitCode = activityCode.split("-");
      if (splitCode.length != 2 || splitCode[0].length() != 3 || splitCode[1].length() != 2
              || !splitCode[0].matches("[a-zA-Z]+") || !splitCode[1].matches("\\d+")) {
        checkHappy = "no";
        System.out.println("Please ensure you are entering the code correctly.");
      } else {
        checkHappy = CheckHappy.checkHappy(userInput, activityCode);
      }

      if (checkHappy.equals("yes")) {
        check = false;
        setActivityCode(activityCode);
      } else {
        activityCode = "";
        checkHappy = "";
      }
    }
  }

  private void activityPrice(Scanner userInput) {
    /**
     * Prompts the user to enter the base cost of the activity as a positive
     * floating-point number through the provided Scanner object. The method
     * validates the input to ensure it's a non-negative number. The user is
     * given the option to confirm or change the entered base cost. If the user
     * confirms the price by typing "yes", the provided base cost is accepted
     * and stored. If the user types "no", they can re-enter the base cost. The
     * method loops until the user enters a valid non-negative number and
     * confirms the entered base cost.
     *
     * @param userInput A Scanner object to read user input.
     * @throws InputMismatchException If the user enters a non-numeric value.
     */
    boolean check = true;
    String checkHappy = "";

    while (check) {
      try {
        System.out.println("Please enter the base cost of the activity as a number: ");
        float activityPrice = userInput.nextFloat();
        if (activityPrice >= 0.00) {
          userInput.nextLine(); // consume the new line char or any input that is leftover
          checkHappy = CheckHappy.checkHappy(userInput, activityPrice);

          if (checkHappy.equals("yes")) {
            check = false;
            setBaseCost(activityPrice);
          } else {
            activityPrice = -1.00f;
            checkHappy = "";
            userInput.nextLine(); // consume the new line char or any input that is leftover
          }
        } else {
          System.out.println("Please enter a number great than or equal to 0.");
          userInput.nextLine();
        }
      } catch (InputMismatchException error) {
        System.out.println(error);
        System.out.println("Please enter a number greater than or equal to 0");
        userInput.nextLine();
      }
    }
  }

  private void activityLocation(Scanner userInput) {
    /**
     * Prompts the user to enter the location of the activity through the
     * provided Scanner object. The method validates the input and allows the
     * user to confirm or change the entered location. If the user confirms the
     * location by typing "yes", the provided location is accepted and stored.
     * If the user types "no", they can re-enter the location. The method loops
     * until the user enters a valid location and confirms the entered value.
     *
     * @param userInput A Scanner object to read user input.
     */
    boolean check = true;
    String checkHappy = "";

    while (check) {
      System.out.println("Please enter the location of the activity: ");
      String activityLocation = userInput.nextLine();

      checkHappy = CheckHappy.checkHappy(userInput, activityLocation);

      if (checkHappy.equals("yes")) {
        check = false;
        setLocation(activityLocation);
      } else {
        activityLocation = "";
        checkHappy = "";
      }
    }
  }

  private void activityDuration(Scanner userInput) {
    /**
     * Prompts the user to enter the expected duration of the activity in hours
     * and minutes using the provided Scanner object. The method validates the
     * input and allows the user to confirm or change the entered duration. If
     * the user confirms the duration by typing "yes", the provided duration is
     * converted to decimal hours and stored. If the user types "no", they can
     * re-enter the duration. The method loops until the user enters a valid
     * duration and confirms the entered value.
     *
     * @param userInput A Scanner object to read user input.
     */
    boolean check = true;
    String checkHappy = "";
    int durationHours = 0;
    int durationMinutes = 0;

    while (check) {
      try {
        System.out.println("Please enter how long the event is expected to last (cannot be a negative number): ");
        System.out.print("Hours: ");
        durationHours = userInput.nextInt();
        userInput.nextLine(); // consume the new line char or any input that is leftover

        System.out.print("\nMinutes: ");
        durationMinutes = userInput.nextInt();
        userInput.nextLine(); // consume the new line char or any input that is leftover

        float convertedMinutes = 0.0f;
        if (durationMinutes != 0) {
          convertedMinutes = (float) durationMinutes / 60.00f; // Convert the minutes to decimal value
        }
        float totalTime = (float) durationHours + (float) convertedMinutes;

        if (durationHours < 0 || durationMinutes < 0 || durationMinutes > 59) {
          checkHappy = "no";
          System.out.println("Please re-enter your time. Please do not enter negative numbers and "
                  + "ensure you are not entering more than 59 minutes.");
        } else {
          checkHappy = CheckHappy.checkHappy(userInput, durationHours, durationMinutes);
        }

        if (checkHappy.equals("yes")) {
          check = false;
          setExpectedDuration(totalTime);
        } else {
          checkHappy = "";
        }

      } catch (InputMismatchException error) {
        userInput.nextLine();
        System.out.println(error + "\n");
        System.out.println("Please enter a number greater than or equal to 0");
        durationHours = 0;
        durationMinutes = 0;

      }

    }
  }

  private void activityDays(Scanner userInput) {
    /**
     * Prompts the user to enter the days when the activity will take place.
     * Allows entry as either the first 3 letters of the day or the full day
     * name (e.g., "monday" or "mon" are acceptable). Multiple days can be
     * entered, separated by commas. Validates the input and provides an option
     * to confirm or change the entered days.
     *
     * @param userInput A Scanner object to read user input.
     */
    boolean check = true;
    String checkHappy = "";

    while (check) {
      System.out.println("""
                         Please enter the days the activity will take place. 
                         Enter as either the first 3 lettersof the day or as the full day. 
                         For expample: monday and mon is acceptable. 
                         To enter multiple, separate them with commas. mon, tue etc
                         """);
      String dayString = userInput.nextLine().toLowerCase().trim();

      // checking for valid dates
      int indexDays = 0;
      String[] daysArray = dayString.split(",");
      for (String day : daysArray) {
        day = day.trim().strip();
        switch (day) {
          case "mon", "tue", "wed", "thu", "fri", "sat", "sun" -> {
          }
          case "monday" -> {
            daysArray[indexDays] = "mon";
          }
          case "tuesday" -> {
            daysArray[indexDays] = "tue";
          }
          case "wednesday" -> {
            daysArray[indexDays] = "wed";
          }
          case "thursday" -> {
            daysArray[indexDays] = "thu";
          }
          case "friday" -> {
            daysArray[indexDays] = "fri";
          }
          case "saturday" -> {
            daysArray[indexDays] = "sat";
          }
          case "sunday" -> {
            daysArray[indexDays] = "sun";
          }
          default -> {
            System.out.println("Error with your input. Please enter dates coded as 3 letters seperated by commas");
            checkHappy = "no";
            break;
          }
        }
        indexDays++;
      }

      String days = String.join(",", daysArray);
      days = days.replaceAll("\\s", "");

      if (checkHappy != "no") {
        checkHappy = CheckHappy.checkHappy(userInput, days);
      }

      if (checkHappy.equals("yes")) {
        check = false;
        setDays(days);
      } else {
        dayString = "";
        checkHappy = "";
      }
    }
  }

  private void activityTime(Scanner userInput) {
    /**
     * Prompts the user to enter the time when the activity will take place in
     * the format "HH:MM". Validates the input format and checks if the entered
     * time falls within the valid time range (0-23 hours, 0-59 minutes).
     * Provides an option to confirm or change the entered time.
     *
     * @param userInput A Scanner object to read user input.
     */
    boolean check = true;
    String checkHappy = "";
    int hours = 0;
    int minutes = 0;

    while (check) {
      System.out.println("Please enter the time the activity will take place in the format \"HH:MM\"."
              + "For example: to enter 2PM enter: 14:00 ");
      String time = userInput.nextLine();
      String[] timeArrayStrings = time.split(":");

      if (timeArrayStrings.length != 2) {
        checkHappy = "no";
        System.out.println("Please ensure you sue the correct format");
      } else {
        try {
          hours = Integer.parseInt(timeArrayStrings[0]);
          minutes = Integer.parseInt(timeArrayStrings[1]);
          if ((hours > 23 || hours < 0) || minutes < 0 || minutes > 59) {
            checkHappy = "no";
            System.out.println("Please ensure your hours range is between 0 - 23 and minutes is between 0 - 59");
          }
        } catch (NumberFormatException e) {
          System.out.println(e);
          System.out.println("Please re-enter your time");
          checkHappy = "no";
          hours = -1;
          minutes = -1;
          userInput.nextLine();
        } catch (InputMismatchException e) {
          System.out.println(e);
          System.out.println("Please re-enter your time");
          checkHappy = "no";
          hours = -1;
          minutes = -1;
        }
      }

      if (checkHappy != "no") {
        checkHappy = CheckHappy.checkHappy(userInput, time);
      }

      if (checkHappy.equals("yes")) {
        check = false;
        setStartTime(time);
      } else {
        time = "";
        checkHappy = "";
      }
    }
  }

  private void activityDescription(Scanner userInput) {
    /**
     * Prompts the user to enter a short description of the activity. Validates
     * the input and provides an option to confirm or change the entered
     * description. This description is associated with the activity specified
     * by getActivityName().
     *
     * @param userInput A Scanner object to read user input.
     */
    boolean check = true;
    String checkHappy = "";

    while (check) {
      System.out.println("Please enter a short description of the activity " + getActivityName() + ": ");
      String description = userInput.nextLine();

      checkHappy = CheckHappy.checkHappy(userInput, description);

      if (checkHappy.equals("yes")) {
        check = false;
        setGeneralDescription(description);
      } else {
        description = "";
        checkHappy = "";
      }
    }
  }

  private String takeUserInput(Scanner userInput) {
    /**
     * Takes user input for various activity details including title, code,
     * location, start time, duration, days, price, and description using the
     * provided Scanner object.
     *
     * @param userInput A Scanner object to read user input.
     * @return A formatted string containing the entered activity details.
     */
    activityTitle(userInput);
    activityCode(userInput);
    activityLocation(userInput);
    activityTime(userInput);
    activityDuration(userInput);
    activityDays(userInput);
    activityPrice(userInput);
    activityDescription(userInput);

    String output = String.format("activity: %s\tcode: %s\tlocation: %s\tstart: %s\tduration: %.2f\t"
            + "days: %s\tprice: %.2f\tdescription: %s",
            getActivityName(), getActivityCode(), getLocation(), getStartTime(),
            getExpectedDuration(), getDays(), getBaseCost(), getGeneralDescription()
    );

    return output;
  }

  private boolean addAnother(Scanner userInput) {
    /**
     * Prompts the user to decide whether they want to enter another activity.
     *
     * @param userInput A Scanner object to read user input.
     * @return true if the user wants to add another activity, false otherwise.
     */
    String checkAgain = "";

    while (!checkAgain.equals("yes") && !checkAgain.equals("no")) {
      System.out.println("Would you like to enter another activity?");
      checkAgain = userInput.nextLine();
    }

    if (checkAgain.equals("yes")) {
      return true;
    }

    return false;
  }

  // TODO Bug test --- Doc String
  public void collectInformation(Scanner userInput, FileOperations file) {
    String running = takeUserInput(userInput);

    String checkHappy = "";
    System.out.println("\n\nActivity details:");
    System.out.println("Activity name:\t" + getActivityName());
    System.out.println("Acitivty Code:\t" + getActivityCode());
    System.out.println("Activity price:\t" + getBaseCost());
    System.out.println("Activity location:\t" + getLocation());
    System.out.println("Activity date:\t" + getDays());
    System.out.println("Activity start time:\t" + getStartTime());
    System.out.println("Activity duration:\t" + getExpectedDuration());
    System.out.println("Activity description:\t" + getGeneralDescription());

    while (!checkHappy.equals("yes") && !checkHappy.equals("no")) {
      System.out.println("Are you happy with these details? if not we will start over.  yes or no");
      checkHappy = userInput.nextLine();
    }

    if (checkHappy.equals("yes")) {
      file.writeToFile(running);
      boolean again = addAnother(userInput);
      if (again) {
        collectInformation(userInput, file);
      }
    } else {
      setBaseCost(-1.00f);
      setDays("");
      setExpectedDuration(-1.00f);
      setLocation("");
      setStartTime("");
      setActivityName("");
      setActivityCode("");
      setGeneralDescription("");
      collectInformation(userInput, file);
    }
  }

  // ---------- Getters/Setters below ---------- //
  private float getBaseCost() {
    return baseCost;
  }

  private void setBaseCost(float baseCost) {
    this.baseCost = baseCost;
  }

  private String getActivityName() {
    return activityName;
  }

  private void setActivityName(String activityName) {
    this.activityName = activityName;
  }

  private String getGeneralDescription() {
    return generalDescription;
  }

  private void setGeneralDescription(String generalDescription) {
    this.generalDescription = generalDescription;
  }

  private String getLocation() {
    return location;
  }

  private void setLocation(String location) {
    this.location = location;
  }

  private String getDays() {
    return days;
  }

  private void setDays(String days) {
    this.days = days;
  }

  private String getStartTime() {
    return this.startTime;
  }

  private void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  private float getExpectedDuration() {
    return expectedDuration;
  }

  private void setExpectedDuration(float expectedDuration) {
    this.expectedDuration = expectedDuration;
  }

  private String getActivityCode() {
    return this.activityCode;
  }

  private void setActivityCode(String activityCode) {
    this.activityCode = activityCode;
  }

}
