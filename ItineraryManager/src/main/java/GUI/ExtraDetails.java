package GUI;

import Objects.Activity;
import Objects.ActivityAddon;
import Objects.Itinerary;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.Border; // doesn't get imported with * 

/**
 *
 * @author Jonathan Hill (d3344758)
 */
public class ExtraDetails {

  private final List<Activity> existingActivities;
  private final List<ActivityAddon> existingAddons;
  
  /**
   * @param itinerary The current Itinerary Object containing the data the user has selected
   * @param existingActivities A list of existing Activity Objects
   * @param existingAddons A list of existing ActivityAddon
   */
  public ExtraDetails(Itinerary itinerary, List<Activity> existingActivities, List<ActivityAddon> existingAddons) {
    this.existingActivities = existingActivities;
    this.existingAddons = existingAddons;
    openItineraryDetails(itinerary);
  }

  /**
   * Creates a simple ui that displays the users details by adding multiple panes that contain
   * labels and tables containing the information. We use a GridBagLayout to allow control of where
   * the information appears. The information for each pane is generated in their own method.
   *
   * @param it Itinerary Object containing it's information
   */
  private void openItineraryDetails(Itinerary it) {
    JPanel activityInformation = activityInformation(getActivityInfo(it.getActivityCodes(),
            existingAddons, existingActivities));

    JFrame frame = new JFrame("Itinerary Details");
    frame.setLayout(new GridBagLayout());

    JPanel detailsPanel = detailsPanel(it.getAttendeeName(), it.getTotalPeople(),
            Float.parseFloat(it.getTotalCostPence()) / 100);
    JPanel itineraryAddons = itineraryAddons(it.getItineraryAddonNames());

    GridBagConstraints location = new GridBagConstraints();
    location.fill = GridBagConstraints.HORIZONTAL;
    location.insets = new Insets(2, 2, 2, 2);

    location.gridx = 0;
    location.gridy = 1;
    location.gridwidth = 1;
    frame.add(detailsPanel, location);

    location.gridx = 0;
    location.gridy = 2;
    location.gridwidth = 1;
    frame.add(itineraryAddons, location);

    location.gridx = 0;
    location.gridy = 3;
    location.gridwidth = 1;
    frame.add(activityInformation, location); // here

    JScrollPane scrollPane = new JScrollPane(frame.getContentPane());
    JFrame mainFrame = new JFrame("Itinerary Details");
    mainFrame.setLayout(new BorderLayout());
    mainFrame.add(scrollPane, BorderLayout.CENTER);
    mainFrame.setSize(500, 500);
    mainFrame.setVisible(true);
  }

  /**
   * Creates a JPanel that stores information to be displayed to the user with a border surrounding
   * it using GridBagLayout to control which order the information is displayed.
   *
   * @param name The lead attendee of the itinerary
   * @param people The amount of people on the itinerary
   * @param cost The total cost of the itinerary
   * @return JPanel containing the information stored in labels.
   */
  private JPanel detailsPanel(String name, String people, float cost) {
    JPanel panel = new JPanel();
    panel.setLayout(new GridBagLayout());  // fxml/css would've been nicer to use

    Border lineBorder = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
    panel.setBorder(lineBorder);

    GridBagConstraints location = new GridBagConstraints();
    location.fill = GridBagConstraints.HORIZONTAL;
    location.insets = new Insets(5, 5, 5, 5);

    JLabel labelName = new JLabel(String.format("Lead Attendee: %s", name));
    JLabel labelTotalPeople = new JLabel(String.format("Total Attendees: %s", people));
    JLabel labelTotalCost = new JLabel(String.format("Total Cost: £%.2f ", cost));

    location.gridx = 0;
    location.gridy = 0;
    panel.add(labelName, location);
    location.gridy = 1;
    panel.add(labelTotalCost, location);
    location.gridy = 2;
    panel.add(labelTotalPeople, location);

    return panel;
  }

  /**
   * Splits the add-ons to add each add-on to an individual field. If no add-ons were selected NONE
   * will take the place. Once the information is added to a table that is added to a panel with a
   * crollPane attached.
   *
   * @param addons a String containing itinerary add-ons separated by commas ','
   * @return JPanel containing the information stored in a table
   */
  private JPanel itineraryAddons(String addons) {
    String[] addonsList = addons.split(",");

    JPanel panel = new JPanel();

    String[] columns = {"Itinerary Addons"};
    Object[][] data = new Object[addonsList.length][columns.length];

    for (int i = 0; i < addonsList.length; i++) {
      data[i][0] = addonsList[i];
    }

    JTable table = new JTable(data, columns);
    JScrollPane scroller = new JScrollPane(table);
    scroller.setPreferredSize(new Dimension(400, 200));

    table.setRowHeight(20);
    table.getColumnModel().getColumn(0).setPreferredWidth(200);

    panel.add(scroller);
    return panel;
  }

  /**
   * Creates a table containing 2 columns to store the data. In one column the activity name is
   * stored and in the column next to it, the add-ons for that activity are stored separated by a
   * comma ",". We also limit the height of the table so it doesn't take up too much space. There is
   * a Scroller attached If there are too many activities to show in the displayed amount.
   *
   * @param activityInfo A Map containing the activity name and a list of add-ons
   * @return JPanel containing a table with all of the activity information
   */
  private JPanel activityInformation(Map<String, List<String>> activityInfo) {
    JPanel panel = new JPanel();

    String[] columns = {"Activity", "Activity Addons"};
    Object[][] data = new Object[activityInfo.size()][columns.length];

    int counter = 0;
    for (Map.Entry<String, List<String>> activityInfoEntry : activityInfo.entrySet()) {
      String activityName = activityInfoEntry.getKey();
      List<String> addons = activityInfoEntry.getValue();

      String addonsString = String.join(", ", addons);

      data[counter][0] = activityName;
      data[counter][1] = addonsString;

      counter++;
    }

    JTable table = new JTable(data, columns);
    JScrollPane scroller = new JScrollPane(table);
    scroller.setPreferredSize(new Dimension(400, 200));

    table.setRowHeight(20);
    table.getColumnModel().getColumn(0).setPreferredWidth(200);

    panel.add(scroller);
    return panel;
  }

  /**
   * Retrieves activity information from activity codes in an itinerary. If no add-ons were selected for a given
   * activity; None will be added to the add-ons list for display purposes
   *
   * @param codes The activity codes from an itinerary containing the activity and any add-ons
   * @param existingActivityAddon A list of existing ActivityAddon objects
   * @param existingActivity A list of existing Activity objects
   * @return (Map) containing the: activity name and a list of add-ons
   */
  private Map<String, List<String>> getActivityInfo(String codes,
          List<ActivityAddon> existingActivityAddon, List<Activity> existingActivity) {

    Map<String, List<String>> activityInfo = new HashMap<>(); // When in doubt throw a HashMap at it
    String[] newCodes = codes.split(" ");

    for (String code : newCodes) {
      List<String> addonNames = new ArrayList<>();
      String activity = code.split(":")[0].replaceAll("\\[|\\]", ""); // regex..
      String[] addonCodes = code.split(":")[1].split(",");

      // Activity name
      for (Activity existingAct : existingActivity) {
        if (existingAct.getActivityCode().equals(activity)) {
          activity = existingAct.getActivityName();
          break;
        }
      }

      // Add-on Names
      for (String shortCode : addonCodes) {
        shortCode = shortCode.replaceAll("\\[|\\]", "");
        for (ActivityAddon existingCode : existingActivityAddon) {
          if (shortCode.equals(existingCode.getShortName())) {
            addonNames.add(existingCode.getAddonName());
            break;
          }
        }
      }
      if (addonNames.isEmpty()) {
        addonNames.add("None");
      }
      activityInfo.put(activity, addonNames);
    }

    return activityInfo;
  }
}
