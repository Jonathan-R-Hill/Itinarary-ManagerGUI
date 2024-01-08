package GUI;

import Objects.Activity;
import Objects.ActivityAddon;
import Objects.Itinerary;

import java.util.List;

/**
 *
 * @author Jonathan Hill (d3344758)
 */
public class GuiMain {

  private final List<Itinerary> customerItinerary;
  private final List<Activity> existingActivities;
  private final List<ActivityAddon> existingAddons;
  
  /**
   * @param customerItinerary A list of existing Itinerary Objects
   * @param existingActivities A list of existing Activity Objects
   * @param existingAddons A list of existing ActivityAddon Objects
   */
  public GuiMain(List<Itinerary> customerItinerary, List<Activity> existingActivities, List<ActivityAddon> existingAddons ) {
    this.customerItinerary = customerItinerary;
    this.existingActivities = existingActivities;
    this.existingAddons = existingAddons;
  }
  
  public void main() {
    var launchPage = new LaunchPage(customerItinerary, existingActivities, existingAddons);
  }

}
