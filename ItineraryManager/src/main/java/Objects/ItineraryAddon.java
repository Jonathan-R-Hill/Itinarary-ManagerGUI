package Objects;

/**
 *
 * @author Jonathan Hill (d3344758)
 */
public class ItineraryAddon {

  private String itineraryAddonName;
  private String itineraryAddonPrice;

  public ItineraryAddon(String itineraryAddonName, String itineraryAddonPrice) {
    this.itineraryAddonName = itineraryAddonName;
    this.itineraryAddonPrice = itineraryAddonPrice;
  }

  public String getItineraryAddonName() {
    return itineraryAddonName;
  }

  public String getItineraryAddonPrice() {
    return itineraryAddonPrice;
  }
}
