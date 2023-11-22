package d3344758.phase_2_3;

import java.util.List;

/**
 *
 * @author d3344758
 */
public class ReceiptGeneration {

  private List<String[]> activityInformation;
  private List<String> activityCodes;
  private List<String> inineraryAddons;
  private int itineraryAddonsLength;

  public ReceiptGeneration(List<String[]> activityInformation, List<String> inineraryAddons, List<String> activityCodes) {
    this.activityInformation = activityInformation;
    this.inineraryAddons = inineraryAddons;
    this.activityCodes = activityCodes;
    this.itineraryAddonsLength = inineraryAddons.size();
  }
  
  
  
// TODO finish method
  private String[] calcActivityCosts(List<String> activityCodes) {
    String[] activityCosts = new String[activityCodes.size()];

    int count = 0;
    for (String info : activityCodes) {
      String[] splitCode = info.split(":");
      String code = splitCode[0];

      for (String[] existingInfo : activityInformation) {
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
    String[] addonCosts = new String[itineraryAddonsLength];

    return addonCosts;
  }

  // TODO method 
  private void calculateCosts() {
//    calcActivityCosts();
//    calcItineraryAddOns();
  }

  // TODO method
  public void generateReciept() {
    calculateCosts();

    System.out.println("+" + "-".repeat(25) + "+");
    System.out.printf("| %-20s%-5s |\n", "PH", "PH");
    System.out.println("+" + "-".repeat(25) + "+");
  }
}
