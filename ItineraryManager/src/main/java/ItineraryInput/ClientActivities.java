package ItineraryInput;

import Objects.Activity;
import Utility.UserValidationChecks;
import Main.ProgramChoice;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Jonathan Hill (d3344758)
 */
public class ClientActivities {

  private List<String> activities = new ArrayList<>();
  private List<String> activityCodes = new ArrayList<>();
  private List<Activity> ExistingActivityInformation = ProgramChoice.activityData;

  /**
   * This method runs the supporting methods to output the activity choices, collect the users input
   * and perform the necessary action based on their input by either exiting or collecting
   * information about the activity they have selected
   *
   * @param userInput The Scanner object for user input.
   */
  protected void collectUserActivityChoices(Scanner userInput) {
    while (true) {
      ActivityInputUtils.activityConsoleOutput(getExistingActivityInformation());
      int userChoice = ActivityInputUtils.userActivityChoice(userInput);

      if (userChoice == 999) {
        if (activities.isEmpty()) {
          System.out.println("To continue, you need to select atleast one activity");
        } else {
          System.out.println("Leaving activity selection.\n");
          break;
        }
      }

      selectAndAddActivity(userChoice, userInput);
    }
  }

  /**
   * Selects and adds the chosen activity based on the user's input if the choice is valid and not a
   * duplicate, It will then collect any add-ons requested by the user and add them to the arrays
   * for later use. If the user enters a number that is not a valid activity the method stops before
   * moving onto collecting activity add-ons.
   *
   * @param userChoice The user's choice of activity.
   * @param userInput The Scanner object for user input.
   */
  private void selectAndAddActivity(int userChoice, Scanner userInput) {
    boolean notDuplicate = true;
    if (userChoice < 0 || userChoice >= ExistingActivityInformation.size()) {
      System.out.println("Please ensure you are picking from the list provided by entering the number next to the activity.");
      return;
    }

    Activity usersActivityChoiceActivity = getExistingActivityInformation().get(userChoice);
    String userActivity = usersActivityChoiceActivity.getActivityName();
    String activityCode = usersActivityChoiceActivity.getActivityCode();

    // Check for duplicates 
    for (String activity : activities) {
      if (userActivity.equals(activity)) {
        notDuplicate = false;
        System.out.println("Please ensure you are not entering duplicate activities.\n");
        break;
      }
    }

    if (notDuplicate) {
      float cost = Float.parseFloat(usersActivityChoiceActivity.getBaseCost()) / 100;
      System.out.printf("""
                        Activity Start time: %s
                        Activity Cost per person: £%.2f
                        Activity Expected Duration: %s hours
                        """,
              usersActivityChoiceActivity.getStartTime(), cost,
              usersActivityChoiceActivity.getExpectedDuration());
      if (UserValidationChecks.checkHappy(userInput, userActivity).equals("yes")) {
        String codeToAdd = ActivityInputUtils.addonRequired(userInput, activityCode);
        addActivityCodes(codeToAdd);
        addActivities(userActivity);
      }
    }
  }

  // ---------- Getters/Setters below ---------- //
  public List<String> getActivities() {
    return activities;
  }

  private void addActivities(String activity) {
    this.activities.add(activity);
  }

  public List<String> getActivityCodes() {
    return activityCodes;
  }

  private void addActivityCodes(String code) {
    this.activityCodes.add(code);
  }

  public List<Activity> getExistingActivityInformation() {
    return ExistingActivityInformation;
  }

}
