import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TripCalculatorTest {
    private TripCalculator tc;

    @Before
    public void setUp() {
        tc = new TripCalculator();
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
        assertThat(tc.calculateCO2onDistanceAndSlope(), equalTo(27.3670906));
    }

}