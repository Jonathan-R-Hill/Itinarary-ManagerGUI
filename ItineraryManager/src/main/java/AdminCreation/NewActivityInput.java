package AdminCreation;

import Utility.FileOperations;
import Utility.UserValidationChecks;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Jonathan Hill (d3344758)
 */
public class NewActivityInput {

    private String activityName;
    private String activityCode;
    private int baseCost;
    private String location;
    private float expectedDuration;
    private String startTime;
    private String generalDescription;

    /**
     * Prompts the user to enter the name of their activity. The user is given the option to confirm
     * or change the entered activity name. If the user confirms the name the provided activity name
     * is accepted and stored. If the user rejects, they can re-enter the activity name. Loops until
     * the user confirms their selection
     *
     * @param userInput A Scanner object to read user input.
     */
    private void activityTitle(Scanner userInput) {
        boolean check = true;

        while (check) {
            String checkHappy = "";
            System.out.println("Please enter the name of your activity");
            String activityname = userInput.nextLine();

            if (!activityname.equals("")) {
                checkHappy = UserValidationChecks.checkHappy(userInput, activityname);
            } else {
                System.out.println("Please ensure you enter a name for the activity.");
            }

            if (checkHappy.equals("yes")) {
                check = false;
                setActivityName(activityname);
            }
        }
        System.out.println();
    }

    /**
     * Prompts the user to enter the activity code in the format: abc-00. ensures the input is valid
     * and provides an option to confirm or change the entered code. The activity code must consist of
     * 3 letters, a hyphen, and 2 digits.
     *
     * @param userInput A Scanner object to read user input.
     */
    private void activityCode(Scanner userInput) {
        boolean check = true;

        while (check) {
            String checkHappy = "";
            System.out.println("Please enter the activity code in the format: abc-00 (3 letters hyphen 2 digits)");
            String activityCode = userInput.nextLine().strip().trim().toUpperCase();

            String[] splitCode = activityCode.split("-");
            if (splitCode.length != 2 || splitCode[0].length() != 3 || splitCode[1].length() != 2
                    || !splitCode[0].matches("[a-zA-Z]+") || !splitCode[1].matches("\\d+")) {
                checkHappy = "no";
                System.out.println("Please ensure you are entering the code correctly.");
            } else {
                checkHappy = UserValidationChecks.checkHappy(userInput, activityCode);
            }

            if (checkHappy.equals("yes")) {
                check = false;
                setActivityCode(activityCode);
            }
        }
    }

    /**
     * Prompts the user to enter the base cost of the activity as a positive floating-point number.
     * validates the input to ensure it's a non-negative number. The user is given the option to
     * confirm or change the entered base cost. If the user confirms the price, the provided base cost
     * is accepted and stored. If the user rejects the price, they can re-enter the base cost. Loops
     * until the user enters a valid number and confirms their entry. stored as pence
     *
     * @param userInput A Scanner object to read user input.
     * @throws InputMismatchException If the user enters a non-numeric value.
     */
    private void activityPrice(Scanner userInput) {
        boolean check = true;

        while (check) {
            String checkHappy = "";
            try {
                System.out.println("Please enter the base cost of the activity as a number: ");
                float activityPrice = userInput.nextFloat();
                if (activityPrice > 0.00) {
                    userInput.nextLine(); // consume the new line char or any input that is leftover
                    checkHappy = UserValidationChecks.checkHappy(userInput, activityPrice);

                    if (checkHappy.equals("yes")) {
                        check = false;
                        float priceToAdd = activityPrice * 100;
                        setBaseCost((int) priceToAdd);
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

    /**
     * Prompts the user to enter the location of the activity. Validates the input and allows the user
     * to confirm or change the entered location. If the user confirms the location, the provided
     * location is accepted and stored. If the user rejects the provided location, they can re-enter
     * the location. Loops until the user confirms the entered value.
     *
     * @param userInput A Scanner object to read user input.
     */
    private void activityLocation(Scanner userInput) {
        boolean check = true;

        while (check) {
            String checkHappy = "";
            System.out.println("Please enter the location of the activity: ");
            String activityLocation = userInput.nextLine();

            checkHappy = UserValidationChecks.checkHappy(userInput, activityLocation);

            if (checkHappy.equals("yes")) {
                check = false;
                setLocation(activityLocation);
            }
        }
    }

    /**
     * Prompts the user to enter the expected duration of the activity in hours and minutes. Validates
     * the input and allows the user to confirm or change the entered duration. If the user confirms
     * the duration, the provided duration is converted to decimal hours and stored. If the user
     * rejects, they can re-enter the duration. Loops until the user enters a valid duration and
     * confirms the entered value.
     *
     * @param userInput A Scanner object to read user input.
     */
    private void activityDuration(Scanner userInput) {
        boolean check = true;
        int durationHours = 0;
        int durationMinutes = 0;

        while (check) {
            String checkHappy = "";
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
                    checkHappy = UserValidationChecks.checkHappy(userInput, durationHours, durationMinutes);
                }

                if (checkHappy.equals("yes")) {
                    check = false;
                    setExpectedDuration(totalTime);
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

    /**
     * Prompts the user to enter the time when the activity will take place in the format "HH:MM".
     * Validates the input format and checks if the entered time falls within the valid time range
     * (0-23 hours, 0-59 minutes). Provides an option to confirm or change the entered time.
     *
     * @param userInput A Scanner object to read user input.
     */
    public void activityTime(Scanner userInput) {
        boolean check = true;
        String checkHappy = "";
        int hours = 0;
        int minutes = 0;

        while (check) {
            checkHappy = "";
            System.out.println("Please enter the time the activity will take place in the format \"HH:MM\"."
                    + "For example: to enter 2PM enter: 14:00 ");
            String time = userInput.nextLine();
            String[] timeArrayStrings = time.split(":");

            if (timeArrayStrings.length != 2) {
                checkHappy = "no";
                System.out.println("Please ensure you sure the correct format");
            } else {
                try {
                    hours = Integer.parseInt(timeArrayStrings[0]);
                    minutes = Integer.parseInt(timeArrayStrings[1]);
                    if ((hours > 23 || hours < 0) || minutes < 0 || minutes > 59) {
                        checkHappy = "no";
                        System.out.println("Please ensure your hours range is between 0 - 23 and minutes is between 0 - 59");
                    }
                } catch (NumberFormatException | InputMismatchException e) {
                    System.out.println(e);
                    System.out.println("Please re-enter your time");
                    checkHappy = "no";
                    hours = -1;
                    minutes = -1;
                }
            }

            if (timeArrayStrings.length == 2) {
                // Adding 0's to the start of any digits that are not 10+ for 24h time
                if (timeArrayStrings[1].length() < 2 || timeArrayStrings[0].length() < 2) {
                    if (timeArrayStrings[1].length() < 2) {
                        timeArrayStrings[1] = "0" + timeArrayStrings[1];
                    }
                    if (timeArrayStrings[0].length() < 2) {
                        timeArrayStrings[0] = "0" + timeArrayStrings[0];
                    }
                }

                String timeToStore = String.join(":", timeArrayStrings);

                if (!checkHappy.equals("no")) {
                    checkHappy = UserValidationChecks.checkHappy(userInput, timeToStore);
                }

                if (checkHappy.equals("yes")) {
                    check = false;
                    setStartTime(timeToStore);
                }
            }
        }
    }

    /**
     * Prompts the user to enter a short description of the activity. provides an option for the user
     * to confirm or change the entered description.
     *
     * @param userInput A Scanner object to read user input.
     */
    private void activityDescription(Scanner userInput) {
        boolean check = true;

        while (check) {
            System.out.println("Please enter a short description of the activity " + getActivityName() + ": ");
            String description = userInput.nextLine();

            String checkHappy = UserValidationChecks.checkHappy(userInput, description);

            if (checkHappy.equals("yes")) {
                check = false;
                setGeneralDescription(description);
            }
        }
    }

    /**
     * Takes user input for various activity details including title, code, location, start time,
     * duration, price, and description by calling the methods list within the method.
     *
     * @param userInput A Scanner object to read user input.
     * @return A formatted string containing the entered activity details.
     */
    private String takeUserInput(Scanner userInput) {
        activityTitle(userInput);
        activityCode(userInput);
        activityLocation(userInput);
        activityTime(userInput);
        activityDuration(userInput);
        activityPrice(userInput);
        activityDescription(userInput);

        String output = String.format("activity: %s\tcode: %s\tlocation: %s\tstart: %s\tduration: %.2f\t"
                        + "price: %.2f\tdescription: %s",
                getActivityName(), getActivityCode(), getLocation(), getStartTime(),
                getExpectedDuration(), getBaseCost(), getGeneralDescription()
        );
        return output;
    }

    /**
     * Prompts the user to decide whether they want to enter another activity.
     *
     * @param userInput A Scanner object to read user input.
     * @return true if the user wants to add another activity, false otherwise.
     */
    private boolean addAnother(Scanner userInput) {
        String checkAgain = "";

        while (!checkAgain.equals("yes") && !checkAgain.equals("no")) {
            System.out.println("Would you like to enter another activity?");
            checkAgain = userInput.nextLine();
        }

        return checkAgain.equals("yes");
    }

    /**
     * Interactively collects information about an activity from the user and writes it to a file if
     * confirmed. Initially, it gathers user input for an activity using the `takeUserInput` method.
     * Then, it displays the details of the activity. The user is then asked to confirm if they are
     * happy with the details. If the user confirms, the information is written to a file using the
     * provided `FileOperations` object. If the user is not happy with the details, they can start
     * over by re-entering the information. Once a decision has been made the user is asked if they
     * want to add another entry and will either loop or end based on their choice.
     *
     * @param userInput Scanner object to read user input from the console.
     * @param file FileOperations object to handle writing the collected information to a file.
     */
    public void collectInformation(Scanner userInput, FileOperations file) {
        String running = takeUserInput(userInput);

        String checkHappy = "";
        System.out.println("\n\nActivity details:");
        System.out.println("Activity name:\t" + getActivityName());
        System.out.println("Acitivty Code:\t" + getActivityCode());
        System.out.println("Activity price:\t" + getBaseCost());
        System.out.println("Activity location:\t" + getLocation());
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
            setBaseCost(-1);
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

    private void setBaseCost(int baseCost) {
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