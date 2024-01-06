package AdminCreation;

import Utility.FileOperations;
import Utility.UserValidationChecks;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Jonathan Hill (d3344758)
 */
public class NewAddonInput {

  private String addonName;
  private String addonShortName;
  private float addonPrice;

  private void addonNameInput(Scanner userInput) {
    while (true) {
      System.out.println("Please enter the Add-ons name: ");
      String addonName = userInput.nextLine();

      if (!addonName.equals("")) {
        String checkHappy = UserValidationChecks.checkHappy(userInput, addonName);

        if (checkHappy.equals("yes")) {
          setAddonName(addonName);
          break;
        }
      } else {
        System.out.println("Please ensure you are entering a name for the addon.");
      }
      System.out.println();
    }

  }

  private void addonShortNameInput(Scanner userInput) {
    while (true) {
      System.out.println("Please enter the Add-ons short name with a length of 3 letters/numbers: ");
      String addonShortendName = userInput.nextLine().strip().toUpperCase();

      String nameToCheck = addonShortendName.replaceAll("\\s", "");  // remove all spaces

      if (nameToCheck.length() != 3) {
        System.out.println("Please ensure you are only using 3 letters and/or numbers and no spaces");
      } else {
        String checkHappy = UserValidationChecks.checkHappy(userInput, nameToCheck);

        if (checkHappy.equals("yes")) {
          setAddonShortName(nameToCheck);
          break;
        }
      }
    }
  }

  private void addonPriceInput(Scanner userInput) {
    while (true) {
      try {
        System.out.println("Please enter the Add-on price ");
        float addonPrice = userInput.nextFloat();
        userInput.nextLine();
        
        if (addonPrice > 0) {
          String checkHappy = UserValidationChecks.checkHappy(userInput, addonPrice);

          if (checkHappy.equals("yes")) {
            setAddonPrice(addonPrice * 100);
            break;
          }
        } else {
          System.out.println("Please ensure the price you are entering is greater than 0.\n");
        }

      } catch (InputMismatchException e) {
        System.out.println("Please ensure you only enter a number greater than 0.\n");
        userInput.nextLine();
      }

    }
  }

  private boolean addAnother(Scanner userInput) {
    String checkAgain = "";

    while (!checkAgain.equals("yes") && !checkAgain.equals("no")) {
      System.out.println("Would you like to enter another addon?");
      checkAgain = userInput.nextLine();
    }

    return checkAgain.equals("yes");
  }

  public void addonCreation(Scanner userInput, FileOperations file) {
    addonNameInput(userInput);
    addonShortNameInput(userInput);
    addonPriceInput(userInput);

    String checkHappy = "";

    while (!checkHappy.equals("yes") && !checkHappy.equals("no")) {
      System.out.println("\n\n");
      System.out.println("Addon Name: " + getAddonName());
      System.out.println("Addon Short Name: " + getAddonShortName());
      System.out.println("Addon Price: " + (getAddonPrice() / 100));
      System.out.println("\nAre you happy with these details? if not we will start over.  yes or no");
      checkHappy = userInput.nextLine();

      String infoString = getAddonName() + "\t" + getAddonShortName() + "\t" + getAddonPrice();
      if (checkHappy.equals("yes")) {
        file.writeToFile(infoString);
        boolean again = addAnother(userInput);
        if (again) {
          addonCreation(userInput, file);
        }
      } else {
        System.out.println("\n\n\nNo problem let's restart.\n");
        setAddonName("");
        setAddonShortName("");
        setAddonPrice(0);
        addonCreation(userInput, file);
      }
    }

  }

  public String getAddonName() {
    return addonName;
  }

  public void setAddonName(String addonName) {
    this.addonName = addonName;
  }

  public String getAddonShortName() {
    return addonShortName;
  }

  public void setAddonShortName(String addonShortName) {
    this.addonShortName = addonShortName;
  }

  public float getAddonPrice() {
    return addonPrice;
  }

  public void setAddonPrice(float addonPrice) {
    this.addonPrice = addonPrice;
  }

}
