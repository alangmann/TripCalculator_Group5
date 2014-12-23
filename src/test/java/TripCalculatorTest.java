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
        assertThat(tc.calculateCO2onDistance(), equalTo(27.096249999999998));
    }


    @Test
    public void calculateCO2onDistanceAndSlope() {
        assertThat(tc.calculateCO2onDistanceAndSlope(), equalTo(24.1274656));
    }

    @Test
    public void calculateCO2onRoute() {
        assertThat(tc.calculateCO2onRoute(), equalTo(26.7005361));
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