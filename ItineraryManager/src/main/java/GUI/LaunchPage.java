package GUI;

import Objects.Activity;
import Objects.ActivityAddon;
import Objects.Itinerary;

import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Jonathan Hill (d3344758)
 */
public class LaunchPage {

  private final List<Activity> existingActivities;
  private final List<ActivityAddon> existingAddons;
  
  /**
   * 
   * @param customerData A list of existing Itinerary Objects
   * @param existingActivities A list of existing Activity Objects
   * @param existingAddons A list of existing ActivityAddon
   */
  public LaunchPage(List<Itinerary> customerData,  List<Activity> existingActivities, List<ActivityAddon> existingAddons) {
  this.existingActivities = existingActivities;
  this.existingAddons = existingAddons;
  buildMainTable(customerData);
}

private void buildMainTable(List<Itinerary> customerData) {
    JFrame frame = new JFrame("Exciting Activities Ltd - Management Screen");
    String[] columns = {"Lead Attendee", "Total Attendees", "Total Activities", "Total Cost"};
    Object[][] data = new Object[customerData.size()][columns.length];

    for (int i = 0; i < customerData.size(); i++) {
      data[i][0] = customerData.get(i).getAttendeeName();
      data[i][1] = customerData.get(i).getTotalPeople();
      data[i][2] = customerData.get(i).getTotalActivities();
      data[i][3] = String.format("£%.2f", Float.parseFloat(customerData.get(i).getTotalCostPence()) / 100);
    }

    JTable table = new JTable(data, columns);
    JScrollPane scroller = new JScrollPane(table);

    table.addMouseListener(new MouseAdapter() {
      @Override
public void mouseClicked(MouseEvent e) {
        int row = table.rowAtPoint(e.getPoint());
        int col = table.columnAtPoint(e.getPoint());

        if (row >= 0 && col >= 0) {
          var newWindow = new ExtraDetails(customerData.get(row), existingActivities, existingAddons);
        }
      }
    });

    frame.add(scroller);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }

}
