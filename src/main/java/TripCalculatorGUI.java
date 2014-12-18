import javax.swing.*;
import java.awt.*;

/**
 * Created by Juergen on 11.12.2014
 */
public class TripCalculatorGUI extends JFrame {

    public void initComponents() {
        Container con = this.getContentPane();
        con.setLayout(new BorderLayout());
        lblTitle = new JLabel("Tripcalculator Luttenberger u. Korosec");
        lblTitle.setHorizontalAlignment(JLabel.CENTER);

        lblCO2onDistance = new JLabel("CO2 consumption based on distance:");
        txtCO2onDistance = new JTextField();
        txtCO2onDistance.setEditable(false);

        lblCO2withSlope = new JLabel("CO2 consumption with slope:");
        txtCO2withSlope = new JTextField();
        txtCO2withSlope.setEditable(false);

        lblCO2withRoutetype = new JLabel("CO2 consumption with route type");
        txtCO2withRoutetype = new JTextField();
        txtCO2withRoutetype.setEditable(false);

        lblCO2fullCalculation = new JLabel("CO2 consumption full calculation");
        txtCO2fullCalculation = new JTextField();
        txtCO2fullCalculation.setEditable(false);

        pnBigPanel = new JPanel();
        pnBigPanel.setLayout(new GridLayout(10, 2, 5, 5));

        pnBigPanel.add(lblCO2onDistance);
        pnBigPanel.add(txtCO2onDistance);
        pnBigPanel.add(lblCO2withSlope);
        pnBigPanel.add(txtCO2withSlope);
        pnBigPanel.add(lblCO2withRoutetype);
        pnBigPanel.add(txtCO2withRoutetype);

        con.add(lblTitle, BorderLayout.NORTH);
        con.add(pnBigPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        TripCalculatorGUI tgui = new TripCalculatorGUI();
        tgui.initComponents();
        tgui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tgui.setSize(500, 500);
        tgui.setVisible(true);
        tgui.setLocationRelativeTo(null);

        TripCalculator tc = new TripCalculator();
        try {
            tc.readRoutesCSV();
            tgui.txtCO2onDistance.setText("" + tc.calculateCO2onDistance());
            tgui.txtCO2withSlope.setText("" + tc.calculateCO2onDistanceAndSlope());
            tgui.txtCO2withRoutetype.setText("" + tc.calculateCO2onRoute());
            System.out.println("CO2 on new distance: " + tc.calculateCO2onCar());
            System.out.println("CO2 on car: "+tc.calculateCO2onCar());
            System.out.println("End of writing");
        } catch (Exception ex) {

        }
    }

    private JLabel lblTitle;
    private JPanel pnBigPanel;
    private JLabel lblCO2onDistance;
    private JTextField txtCO2onDistance;
    private JLabel lblCO2withSlope;
    private JTextField txtCO2withSlope;
    private JLabel lblCO2withRoutetype;
    private JTextField txtCO2withRoutetype;

    private JLabel lblCO2fullCalculation;
    private JTextField txtCO2fullCalculation;

    private JLabel lblSpecifications;
    private JRadioButton rbCar;
    private JRadioButton rbTruck;
    private ButtonGroup bgVehicle;
}
