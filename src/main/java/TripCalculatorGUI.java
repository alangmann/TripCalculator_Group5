import javax.swing.*;

/**
 * Created by Juergen on 11.12.2014
 */
public class TripCalculatorGUI extends JFrame {



    public static void main(String[] args) {
        TripCalculatorGUI tgui = new TripCalculatorGUI();
        tgui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tgui.setSize(500,500);
        tgui.setVisible(true);
        tgui.setLocationRelativeTo(null);
        TripCalculator tc = new TripCalculator();
        try {
            tc.readRoutesCSV();
            System.out.println("CO2 on distance: " + tc.calculateCO2onDistance());
            System.out.println("CO2 on distance and slope: " + tc.calculateCO2onDistanceAndSlope());
            System.out.println("CO2 on route: " + tc.calculateCO2onRoute());
            System.out.println("CO2 on new distance: " + tc.calculateCO2onDistanceWithWeight());
        } catch (Exception ex) {

        }
    }
}
