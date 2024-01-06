package Objects;

/**
 *
 * @author Jonathan Hill (d3344758)
 */
public class ActivityAddon {

  private final String addonName;
  private final String shortName;
  private final String addonPrice;

  /**
   *
   * @param addonName name of the add-on
   * @param shortName the shortened version for the activity code if selected
   * @param addPrice price of the add-on in pence
   */
  public ActivityAddon(String addonName, String shortName, String addPrice) {
    this.addonName = addonName;
    this.shortName = shortName;
    this.addonPrice = addPrice;
  }

  public String getAddonName() {
    return addonName;
  }

  public String getAddonPrice() {
    return addonPrice;
  }

  public String getShortName() {
    return shortName;
  }

}
