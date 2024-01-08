package GUI;

import Objects.Activity;
import Objects.ActivityAddon;
import Objects.Itinerary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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

  public ExtraDetails(Itinerary itinerary, List<Activity> existingActivities, List<ActivityAddon> existingAddons) {
    this.existingActivities = existingActivities;
    this.existingAddons = existingAddons;
    openItineraryDetails(itinerary);
  }

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
    mainFrame.setSize(400, 400);
    mainFrame.setVisible(true);
  }

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

    panel.add(scroller);
    return panel;
  }

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

    panel.add(scroller);
    return panel;
  }

  private Map<String, List<String>> getActivityInfo(String codes,
          List<ActivityAddon> existingActivityAddon, List<Activity> existingActivity) {

    Map<String, List<String>> activityInfo = new HashMap<>(); // When in doubt throw a HashMap at it
    String[] newCodes = codes.split(" ");

    for (String code : newCodes) {
      List<String> addonNames = new ArrayList<>();
      String activity = code.split(":")[0].replaceAll("\\[|\\]", ""); // regex..
      String[] addonCodes = code.split(":")[1].split(",");
      
      for (Activity existingAct : existingActivity) {
        if (existingAct.getActivityCode().equals(activity)) {
          activity = existingAct.getActivityName();
          break;
        }
      }

      for (String shortCode : addonCodes) {
        for (ActivityAddon existingCode : existingActivityAddon) {
          if (shortCode.equals(existingCode.getShortName())) {
            addonNames.add(existingCode.getAddonName());
            break;
          }
        }
      }
      activityInfo.put(activity, addonNames);

    }

    return activityInfo;
  }
}
