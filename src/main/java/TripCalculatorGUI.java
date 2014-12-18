import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

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

        pnVehicle = new JPanel(new GridLayout(1, 2));
        lblSpecifications = new JLabel("Specifications");
        bgVehicle = new ButtonGroup();
        rbCar = new JRadioButton("Car");
        rbCar.setSelected(true);
        rbTruck = new JRadioButton("Truck");
        bgVehicle.add(rbCar);
        bgVehicle.add(rbTruck);
        pnVehicle.add(rbCar);
        pnVehicle.add(rbTruck);

        pnFuelType = new JPanel(new GridLayout(1, 2));
        lblFuelType = new JLabel("Fueltype");
        bgFuel = new ButtonGroup();
        rbDiesel = new JRadioButton("Diesel");
        rbDiesel.setSelected(true);
        rbPatrol = new JRadioButton("Patrol");
        bgFuel.add(rbDiesel);
        bgFuel.add(rbPatrol);
        pnFuelType.add(rbDiesel);
        pnFuelType.add(rbPatrol);

        lblCargo = new JLabel("Cargo in kg");
        txtCargo = new JTextField();

        pnTruck = new JPanel(new GridLayout(1,3));
        lblAxles = new JLabel("Nr Axles");
        txtAxles = new JTextField();
        txtAxles.setEditable(false);
        cbAdBlue = new JCheckBox("AdBlue");
        cbAdBlue.setEnabled(false);
        pnTruck.add(lblAxles);
        pnTruck.add(txtAxles);
        pnTruck.add(cbAdBlue);

        lblCO2fullCalculation = new JLabel("CO2 consumption full calculation");
        txtCO2fullCalculation = new JTextField();
        txtCO2fullCalculation.setEditable(false);

        rbTruck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(rbTruck.isSelected())
                {
                    txtAxles.setEditable(true);
                    cbAdBlue.setEnabled(true);

                }
            }
        });

        rbCar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(rbCar.isSelected())
                {
                    txtAxles.setEditable(false);
                    cbAdBlue.setEnabled(false);
                }
            }
        });

        rbDiesel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(rbDiesel.isSelected())
                {

                }
            }
        });

        SimpleDateFormat sdf = new SimpleDateFormat("E");
        lblDayOfWeek = new JLabel("DayOfWeek: ");
        String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        cbDayOfWeek = new JComboBox(daysOfWeek);
        switch (sdf.format(new Date()))
        {
            case "Mo": cbDayOfWeek.setSelectedIndex(0);break;
            case "Di": cbDayOfWeek.setSelectedIndex(1);break;
            case "Mi": cbDayOfWeek.setSelectedIndex(2);break;
            case "Do": cbDayOfWeek.setSelectedIndex(3);break;
            case "Fr": cbDayOfWeek.setSelectedIndex(4);break;
            case "Sa": cbDayOfWeek.setSelectedIndex(5);break;
            default: cbDayOfWeek.setSelectedIndex(6);
        }

        lblCostAverageConsumption = new JLabel("Cost Average Consumption");
        txtCostAverageConsumption = new JTextField();
        txtCostAverageConsumption.setEditable(false);
        lblShowSpecialFees = new JLabel("+ special fees");
        txtShowSpecialFees = new JTextField();
        txtShowSpecialFees.setEditable(false);
        lblShowAxles = new JLabel("+ axles");
        txtShowAxles = new JTextField();
        txtShowAxles.setEditable(false);

        pnBigPanel = new JPanel();
        pnBigPanel.setLayout(new GridLayout(12, 2, 5, 5));

        pnBigPanel.add(lblCO2onDistance);
        pnBigPanel.add(txtCO2onDistance);
        pnBigPanel.add(lblCO2withSlope);
        pnBigPanel.add(txtCO2withSlope);
        pnBigPanel.add(lblCO2withRoutetype);
        pnBigPanel.add(txtCO2withRoutetype);
        pnBigPanel.add(lblSpecifications);
        pnBigPanel.add(pnVehicle);
        pnBigPanel.add(lblFuelType);
        pnBigPanel.add(pnFuelType);
        pnBigPanel.add(lblCargo);
        pnBigPanel.add(txtCargo);
        pnBigPanel.add(new JLabel("only for truck"));
        pnBigPanel.add(pnTruck);
        pnBigPanel.add(lblCO2fullCalculation);
        pnBigPanel.add(txtCO2fullCalculation);
        pnBigPanel.add(lblDayOfWeek);
        pnBigPanel.add(cbDayOfWeek);
        pnBigPanel.add(lblCostAverageConsumption);
        pnBigPanel.add(txtCostAverageConsumption);
        pnBigPanel.add(lblShowSpecialFees);
        pnBigPanel.add(txtShowSpecialFees);
        pnBigPanel.add(lblShowAxles);
        pnBigPanel.add(txtShowAxles);

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

        TripCalculator tc = TripCalculator.getInstance();
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

    private JPanel pnVehicle;
    private JLabel lblSpecifications;
    private JRadioButton rbCar;
    private JRadioButton rbTruck;
    private ButtonGroup bgVehicle;

    private JPanel pnFuelType;
    private JLabel lblFuelType;
    private JRadioButton rbDiesel;
    private JRadioButton rbPatrol;
    private ButtonGroup bgFuel;

    private JLabel lblCargo;
    private JTextField txtCargo;

    private JPanel pnTruck;
    private JLabel lblAxles;
    private JTextField txtAxles;
    private JCheckBox cbAdBlue;

    private JLabel lblCO2fullCalculation;
    private JTextField txtCO2fullCalculation;

    private JLabel lblCostAverageConsumption;
    private JTextField txtCostAverageConsumption;
    private JLabel lblShowSpecialFees;
    private JTextField txtShowSpecialFees;
    private JLabel lblShowAxles;
    private JTextField txtShowAxles;

    private JLabel lblDayOfWeek;
    private JComboBox cbDayOfWeek;
}
