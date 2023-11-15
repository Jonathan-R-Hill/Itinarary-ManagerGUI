package d3344758.phase_1;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;

/**
 *
 * @author Jonathan Hill (d3344758)
 */
public class FileOperations {

  private String fileName = "";
  private boolean fileExists = false;
  private File storageFile = new File(this.fileName);

  public FileOperations(String fileName, boolean fileExists) {
    this.fileExists = fileExists;
    this.fileName = fileName;
  }

  public void readFile() {
    /**
     * The method accesses the file using a BufferedReader and FileReader,
     * reading each line from the file and printing it to the console.
     * Any IOException that occurs during the file reading process is caught and
     * its stack trace is printed.
     */
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = reader.readLine()) != null) {
        System.out.println(line); // TODO change later for GUI
      }
    } catch (IOException error) {
      error.printStackTrace();
    }
  }

  public void writeToFile(String information) {
    /**
     * This method writes the given information to the file specified by the fileName attribute.
     * The data is written using a BufferedWriter and FileWriter. Any IOException
     * that occurs during the file writing process is caught, and its stack
     * trace is printed.
     *
     * @param information The data from user input to be written to the file.
     */
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
      writer.write(information);
      System.out.println("Write to file was successful!");
    } catch (IOException error) {
      error.printStackTrace();
    }
  }

  public void checkCreateFile() {
    /**
     * This method checks if the file exists. If not, it creates the file and sets the
     * fileExists attribute to true. If the file does not exist  it creates the file
     * and prints the file location on the computer. If
     * the file already exists, it prints a message indicating the file's
     * presence and path. If any IOException occurs during file creation, the
     * error stack trace is printed to the console.
     */
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
