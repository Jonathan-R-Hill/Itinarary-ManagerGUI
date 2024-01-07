package GUI;

import Objects.Itinerary;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Jonathan Hill (d3344758)
 */
public class ExtraDetails {

  public ExtraDetails(Itinerary index) {
    openItineraryDetails(index);
  }

  private void openItineraryDetails(Itinerary itinerary) {
    JFrame newFrame = new JFrame("Itinerary Details");
    newFrame.setSize(400, 400);

    JLabel labelName = new JLabel(String.format("Lead Attendee: %s", itinerary.getAttendeeName()));
    JLabel labelTotalPeople = new JLabel(String.format("Total Attendees: %s",
            itinerary.getTotalPeople()));
    JLabel labelTotalCost = new JLabel(String.format("Total Cost: %.2f ", 
            Float.parseFloat(itinerary.getTotalCostPence()) / 100));
    
    labelName.setHorizontalAlignment(JLabel.LEFT);
    labelTotalCost.setHorizontalAlignment(JLabel.LEFT);
    labelTotalPeople.setHorizontalAlignment(JLabel.LEFT);

    newFrame.add(labelName);
    newFrame.add(labelTotalCost);
    newFrame.add(labelTotalPeople);
    
    newFrame.pack();
    newFrame.setVisible(true);
  }
}
