package Utility;

import Objects.Activity;
import Objects.ActivityAddon;
import Objects.ItineraryAddon;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Jonathan Hill (d3344758)
 */
public class FileOperations {

  private final String fileName;
  private boolean fileExists = false;
  private final File storageFile;

  /**
   * Constructs a new {@code FileOperations} object. Initializes the file existence status, the file
   * name, and the file itself.
   *
   * @param fileName The name of the file with which the operations will be performed.
   * @param fileExists A boolean indicating if the file already exists.
   */
  public FileOperations(String fileName, boolean fileExists) {
    this.fileExists = fileExists;
    this.fileName = fileName;
    this.storageFile = new File(fileName);
  }

  /**
   * Reads data from a file and creates a list of Activity objects.
   *
   * The file should contain tab-separated information for each activity, and each line represents
   * an activity with specific details. The values are split by tabs, and the method creates an
   * Activity object for each line, assuming the correct number of fields are present. The first
   * field after the colon in each part is stored in the corresponding field of the Activity object.
   *
   * @return A list of Activity objects read from the file.
   */
  public List<Activity> readActivityFile() {
    List<Activity> activities = new ArrayList<>();

    try (Scanner scanner = new Scanner(new File(this.fileName))) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] parts = line.split("\t");

        for (int i = 0; i < parts.length; i++) {
          String[] newPart = parts[i].split(":", 2);

          if (newPart.length > 1) {
            parts[i] = newPart[1].strip();
          }
        }

        if (parts.length == 7) {
          String activityName = parts[0];
          String activityCode = parts[1];
          String location = parts[2];
          String startTime = parts[3];
          String expectedDuration = parts[4];
          String baseCost = parts[5];
          String generalDescription = parts[6];

          Activity activity = new Activity(activityName, activityCode, baseCost, location, expectedDuration, startTime, generalDescription);
          activities.add(activity);
        } else {
          System.out.println("Skipping invalid line: " + line);
        }
      }
    } catch (FileNotFoundException error) {
      error.printStackTrace();
    }

    return activities;
  }

  /**
   * The file should contain tab-separated information for each activity add-on, and each line
   * represents an add-on with specific details. The values are split by tabs, and the method
   * creates an ActivityAddon object for each line, assuming the correct number of fields are
   * present. The first field after the colon in each part is stored in the corresponding field of
   * the Activity object.
   *
   * @return A list of ActivityAddon objects read from the file
   */
  public List<ActivityAddon> readAddonFile() {
    List<ActivityAddon> addons = new ArrayList<>();

    try (Scanner scanner = new Scanner(new File(this.fileName))) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] parts = line.split("\t");

        for (int i = 0; i < parts.length; i++) {
          String[] newPart = parts[i].split(":", 2);

          if (newPart.length > 1) {
            parts[i] = newPart[1].strip();
          }
        }

        if (parts.length == 3) {
          String addonName = parts[0];
          String addonShortName = parts[1];
          String addonPrice = parts[2];

          var activityAddon = new ActivityAddon(addonName, addonShortName, addonPrice);
          addons.add(activityAddon);
        } else {
          System.out.println("Skipping invalid line: " + line);
        }
      }
    } catch (FileNotFoundException error) {
      error.printStackTrace();
    }

    return addons;
  }
  
  /**
   * 
   * @return  A list of ItineraryAddon objects read from the file
   */
  public List<ItineraryAddon> readItineraryAddonFile() {
    List<ItineraryAddon> addons = new ArrayList<>();

    try (Scanner scanner = new Scanner(new File(this.fileName))) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] parts = line.split("\t");

        for (int i = 0; i < parts.length; i++) {
          String[] newPart = parts[i].split(":", 2);

          if (newPart.length > 1) {
            parts[i] = newPart[1].strip();
          }
        }

        if (parts.length == 2) {
          String addonName = parts[0];
          String addonPrice = parts[1];

          var itneraryAddon = new ItineraryAddon(addonName, addonPrice);
          addons.add(itneraryAddon);
        } else {
          System.out.println("Skipping invalid line: " + line);
        }
      }
    } catch (FileNotFoundException error) {
      error.printStackTrace();
    }

    return addons;
  }

  /**
   * Writes the provided information to the file. The information is appended to the end of the
   * file. After writing, a success message is printed to the console. If an IOException occurs, it
   * is caught and its stack trace is printed.
   *
   * @param information The data to be written to the file, as a single string.
   */
  public void writeToFile(String information) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
      writer.append(information + "\n");
      System.out.println("Write operation was successful.");
      writer.close();
    } catch (IOException error) {
      error.printStackTrace();
    }
  }

  /**
   * Checks if the file specified by the 'fileName' exists and, if not, attempts to create it.
   * Provides console output indicating whether the file exists, was created, or if the creation
   * failed.
   */
  public boolean checkCreateFile() {
    if (!getFileExists()) {
      if (storageFile.exists()) {
        String filePath = storageFile.getAbsolutePath();
        setFileExists(true);
        System.out.println("File located at: " + filePath);
      } else {
        System.out.println("File does not exist! Creating file");
        try {
          if (storageFile.createNewFile()) {
            System.out.println("File created successfully!");
            String filePath = storageFile.getAbsolutePath();
            setFileExists(true);
            System.out.println("File created at: " + filePath);
            return false;
          }
        } catch (IOException error) {
          error.printStackTrace();
          System.out.println("Failed to create the file");
        }
      }
    }
    return true;
  }

  // ---------- Getters/Setters below ---------- //
  private boolean getFileExists() {
    return this.fileExists;
  }

  private void setFileExists(boolean trueFalse) {
    this.fileExists = trueFalse;
  }

}
