import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TripCalculatorTest {
    private TripCalculator tc;

    @Before
    public void setUp() {
        tc = new TripCalculator();
    }

    @Test
    public void calculateCO2onDistance() {
        assertThat(tc.calculateCO2onDistance(), equalTo(205.69249999999997));
    }

    /*
    @Test
    public void calculateCO2onDistanceAndSlope()
    {
        assertThat(tc.calculateCO2onDistanceAndSlope(), equalTo());
    }*/

}