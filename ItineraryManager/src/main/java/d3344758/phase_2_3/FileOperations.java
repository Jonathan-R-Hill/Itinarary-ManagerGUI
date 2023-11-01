package d3344758.phase_2_3;

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
  private File storageFile;

  public FileOperations(String fileName, boolean fileExists) {
    this.fileExists = fileExists;
    this.fileName = fileName;
    this.storageFile = new File(fileName);
  }

  public void readFile() {
    /**
     * Reads data from the specified file and prints its contents to the
     * console. This method accesses the file, reads its data line by line, and
     * outputs each line to the console. If an IOException occurs during the
     * file reading process, the error details are printed to the standard error
     * stream.
     */
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = reader.readLine()) != null) {
        System.out.println(line);
      }
    } catch (IOException error) {
      error.printStackTrace();
    }
  }

  public void writeToFile(String information) {
    /**
     * This method is used to write data to the file, typically for each
     * activity as entered via the console GUI. The data is expected to be
     * provided as the 'information' parameter. If the write operation is
     * successful, it will print a success message to the console.
     *
     * @param information The data to be written to the file.
     */
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
      writer.append(information + "\n");
      System.out.println("Write operation was successful!");
    } catch (IOException error) {
      error.printStackTrace();
    }
  }

  public void checkCreateFile() {
    /**
     * This method checks whether the file exists. If the file does not exist,
     * it tries to create the file. It provides console feedback about the
     * file's existence and creation status.
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
