package d3344758.phase_2_3;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jonathan Hill (d3344758)
 */
public class FileOperations {

  private String fileName = "";
  private boolean fileExists = false;
  private File storageFile;

  /**
   * Constructs a new {@code FileOperations} object. Initializes the file
   * existence status, the file name, and the file itself.
   *
   * @param fileName The name of the file with which the operations will be
   * performed.
   * @param fileExists A boolean indicating if the file already exists.
   */
  public FileOperations(String fileName, boolean fileExists) {
    this.fileExists = fileExists;
    this.fileName = fileName;
    this.storageFile = new File(fileName);
  }

  /**
   * Reads the content of the file line by line, splitting each line into an
   * array of strings based on tab characters. Further splits each item by the
   * first colon encountered and trims the result. Each line's items are stored
   * as a String array and all lines are collected into a list.
   *
   * @return A list of string arrays, each representing the split content of one
   * line in the file.
   */
  public List<String[]> readFile() {
    List<String[]> fileContents = new ArrayList<>();
    
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      String line;

      while ((line = reader.readLine()) != null) {
        String[] items = line.split("\t");

        for (int i = 0; i < items.length; i++) {
          String[] parts = items[i].split(":", 2);

          if (parts.length > 1) {
            items[i] = parts[1].trim().strip();
          }
        }
        fileContents.add(items);
      }
    } catch (IOException error) {
      error.printStackTrace();
    }
    return fileContents;
  }

  /**
   * Writes the provided information to the file. The information is appended to
   * the end of the file. After writing, a success message is printed to the
   * console. If an IOException occurs, it is caught and its stack trace is
   * printed.
   *
   * @param information The data to be written to the file, as a single string.
   */
  public void writeToFile(String information) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
      writer.append(information + "\n");
      System.out.println("Write operation was successful!");
    } catch (IOException error) {
      error.printStackTrace();
    }
  }

  /**
   * Checks if the file specified by the 'fileName' exists and, if not, attempts
   * to create it. Provides console output indicating whether the file exists,
   * was created, or if the creation failed.
   */
  public void checkCreateFile() {
    if (!getFileExists()) {
      if (storageFile.exists()) {
        System.out.println("File exists!");
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
          }
        } catch (IOException error) {
          error.printStackTrace();
          System.out.println("Failed to create the file");
        }
      }
    }
  }

  // ---------- Getters/Setters below ---------- //
  private boolean getFileExists() {
    return this.fileExists;
  }

  private void setFileExists(boolean trueFalse) {
    this.fileExists = trueFalse;
  }

  private String getFileName() {
    return this.fileName;
  }

  private void setFileName(String newName) {
    this.fileName = newName;
  }

}
