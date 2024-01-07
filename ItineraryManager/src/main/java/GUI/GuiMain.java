package GUI;

import Objects.Itinerary;

import java.util.List;

/**
 *
 * @author Jonathan Hill (d3344758)
 */
public class GuiMain {

  protected List<Itinerary> customerData;

  public GuiMain(List<Itinerary> customerData) {
    this.customerData = customerData;
  }

  public void main() {
    var launchPage = new LaunchPage(customerData);
  }

}
