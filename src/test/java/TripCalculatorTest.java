import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TripCalculatorTest {
    private TripCalculator tc;

    @Before
    public void setUp() {
        tc = TripCalculator.getInstance();
        try {
            tc.readRoutesCSV();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void calculateCO2onDistance() {
        assertThat(tc.calculateCO2onDistance(), equalTo(54.1925));
    }

    @Test
    public void calculateCO2onDistanceAndSlope() {
        assertThat(tc.calculateCO2onDistanceAndSlope(), equalTo(96.50986240000002));
    }

    @Test
    public void calculateCO2onRoute() {
        assertThat(tc.calculateCO2onRoute(), equalTo(80.1016083));
    }

    @Test
    public void calculateTotalCostOfRouteWithCar() {
        try {
            assertThat(tc.calculateTotalCostOfRoute(new Car(5.0, FuelType.Diesel, 100), "Monday", false, false), equalTo(1485.79475));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void calculateCo2ConsumptionTruckWithoutAdBlue()
    {
        assertThat(tc.calculateCo2Consumption(new Truck(35.0, FuelType.Diesel, 100, 4, false)), equalTo(211.320208061));
    }

    @Test
    public void calculateCo2ConsumptionTruckWithAdBlue()
    {
        assertThat(tc.calculateCo2Consumption(new Truck(35.0, FuelType.Diesel, 100, 4, true)), equalTo(196.52779349673));
    }


    /*
    @Test
    public void calculateCO2onCar() {
        assertThat(tc.calculateCO2onCar(), equalTo(29.37058971));
    }

    @Test
    public void calculateCO2onTruck() {
        assertThat(tc.calculateCO2onTruck(new Truck(5.0, FuelType.Diesel, 100, 4, true)), equalTo(25.07981355873));
    }
    */
}