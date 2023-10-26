package d3344758.phase_1;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Jonathan Hill (d3344758)
 */
public class NewActivityInput {

  private String title;
  private float baseCost;
  private String location;
  private float expectedDuration;
  private String date;
  private String startTime;
  private String generalDescription;

  /* public NewActivityInput(float baseCost, String title, String generalDescription, String location, String dateTime, float expectedDuration) {
    this.baseCost = baseCost;
    this.title = title;
    this.generalDescription = generalDescription;
    this.location = location;
    this.dateTime = dateTime;
    this.expectedDuration = expectedDuration;
  } */
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
    String checkHappy = "";

    while (check) {
      System.out.println("Please enter the name of your activity");
      String activityname = userInput.nextLine();

      while (!checkHappy.equals("yes") && !checkHappy.equals("no")) {
        System.out.println("Are you happy with the name: " + activityname + "?\t yes or no");
        checkHappy = userInput.nextLine().toLowerCase();
      }

      if (checkHappy.equals("yes")) {
        check = false;
        setTitle(activityname);
      } else {
        activityname = "";
        checkHappy = "";
        userInput.nextLine(); // consume the new line char or any input that is leftover
      }
    }
    System.out.printf("Your title: \"%s\" was accepted.\n", getTitle());
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
          while (!checkHappy.equals("yes") && !checkHappy.equals("no")) {
            System.out.println("Are you happy with the price: " + activityPrice + "?\t yes or no");
            checkHappy = userInput.nextLine().toLowerCase();
          }

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
        System.err.println(error);
        System.out.println("Please enter a number greater than or equal to 0");
        userInput.nextLine();
      }
    }
    System.out.printf("Your price: \"%.2f\" was accepted.\n", getBaseCost());
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

      while (!checkHappy.equals("yes") && !checkHappy.equals("no")) {
        System.out.println("Are you happy with the location: \"" + activityLocation + "\"?\t yes or no");
        checkHappy = userInput.nextLine().toLowerCase();
      }
      if (checkHappy.equals("yes")) {
        check = false;
        setLocation(activityLocation);
      } else {
        activityLocation = "";
        checkHappy = "";
        userInput.nextLine(); // consume the new line char or any input that is leftover
      }
    }
    System.out.printf("Your activity Location: \" %s\" was accpted.\n", getLocation());
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

    while (check) {
      try {
        System.out.println("Please enter how long the event is expected to last (cannot be a negative number): ");
        System.out.print("Hours: ");
        int durationHours = userInput.nextInt();
        userInput.nextLine(); // consume the new line char or any input that is leftover
        System.out.print("\nMinutes: ");
        int durationMinutes = userInput.nextInt();
        userInput.nextLine(); // consume the new line char or any input that is leftover
        // Convert the minutes to decimal value and create total time 
        float convertedMinutes = durationMinutes / 60.00f;
        float totalTime = (float) durationHours + (float) convertedMinutes;
        if (durationHours < 0 || durationMinutes < 0) {
          checkHappy = "no";
          System.out.println("Please re-enter your time. Please do not enter negative numbers.");
        }
        while (!checkHappy.equals("yes") && !checkHappy.equals("no")) {
          System.out.println("Are you happy with the location: \"" + durationHours + " Hours  " + durationMinutes + " Minutes\"? (" + totalTime + ")\t yes or no");
          checkHappy = userInput.nextLine().toLowerCase();
        }
        if (checkHappy.equals("yes")) {
          check = false;
          setExpectedDuration(totalTime);
        } else {
          totalTime = -1.00f;
          durationHours = -1;
          durationMinutes = -1;
          checkHappy = "";
          userInput.nextLine(); // consume the new line char or any input that is leftover
        }

      } catch (InputMismatchException error) {
        System.err.println(error);
        System.out.println("Please enter a number greater than or equal to 0");
      }

    }
    System.out.printf("Your activities expected duration \"%.2f\" Hours was accepted.\n", getExpectedDuration());
  }

// TODO date validation? 
  private void activityDate(Scanner userInput) {
    boolean check = true;
    String checkHappy = "";
    String todayDate = new SimpleDateFormat("dd/MM/yy").format(Calendar.getInstance().getTime());

    while (check) {
      System.out.println("Please enter the date the activity will take place in the format \"DD/MM/YY\""
              + "Example: todays date is:  " + todayDate);
      String date = userInput.nextLine();

      while (!checkHappy.equals("yes") && !checkHappy.equals("no")) {
        System.out.println("Are you happy with the date: \"" + date + "\"?\t yes or no");
        checkHappy = userInput.nextLine().toLowerCase();
      }
      if (checkHappy.equals("yes")) {
        check = false;
        setDate(date);
      } else {
        date = "";
        checkHappy = "";
        userInput.nextLine(); // consume the new line char or any input that is leftover
      }
    }
  }

  // TODO time
  private void activityTime(Scanner userInput) {
    boolean check = true;
    String checkHappy = "";

    while (check) {
      System.out.println("Please enter the time the activity will take place in the format \"HH:MM\": ");
      String time = userInput.nextLine();

      while (!checkHappy.equals("yes") && !checkHappy.equals("no")) {
        System.out.println("Are you happy with the start time: : \"" + time + "\"?\t yes or no");
        checkHappy = userInput.nextLine().toLowerCase();
      }
      if (checkHappy.equals("yes")) {
        check = false;
        setStartTime(time);
      } else {
        time = "";
        checkHappy = "";
        userInput.nextLine(); // consume the new line char or any input that is leftover
      }
    }
  }

  // TODO description
  private void activityDescription(Scanner userInput) {
    boolean check = true;
    String checkHappy = "";

    while (check) {
      System.out.println("Please enter a short description of the activity " + getTitle() + ": ");
      String description = userInput.nextLine();

    }
  }

  private void checkDetails(Scanner userInput) {
    String checkHappy = "";
    System.out.println("Are these details correct? ");
    System.out.println("Activity name:\t" + getTitle());
    System.out.println("Activity price:\t" + getBaseCost());
    System.out.println("Activity location:\t" + getLocation());
    System.out.println("Activity date:\t" + getDate());
    System.out.println("Activity start time:\t" + getStartTime());
    System.out.println("Activity description:\t" + getGeneralDescription());

    while (!checkHappy.equals("yes") && !checkHappy.equals("no")) {
      System.out.println("Are you happy with these details? if not we will start over.  yes or no");
      checkHappy = userInput.nextLine();
    }
    if (checkHappy.equals("yes")) {
      // TODO write to file
    } else {
      setBaseCost(-1.00f);
      setDate("");
      setExpectedDuration(-1.00f);
      setGeneralDescription("");
      setLocation("");
      setStartTime("");
      setTitle("");
    }
  }

  // ---------- Getters/Setters below ---------- //
  public float getBaseCost() {
    return baseCost;
  }

  public void setBaseCost(float baseCost) {
    this.baseCost = baseCost;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getGeneralDescription() {
    return generalDescription;
  }

  public void setGeneralDescription(String generalDescription) {
    this.generalDescription = generalDescription;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getStartTime() {
    return date;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public float getExpectedDuration() {
    return expectedDuration;
  }

  public void setExpectedDuration(float expectedDuration) {
    this.expectedDuration = expectedDuration;
  }
}
