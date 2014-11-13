package java;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TripCalculatorTest {
    private TripCalculator tripCalculator;

    @Before
    public void setUp(){ tripCalculator = new TripCalculator();}

    @Test
    public void testDummy() {
        assertTrue(true);
    }

    @Test
    public void testInitialise() { tripCalculator.initialise();}
}
