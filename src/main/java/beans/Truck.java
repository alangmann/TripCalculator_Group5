package beans;

import beans.Vehicle;

/**
 * Created by Juergen on 04.12.2014.
 */
public class Truck extends Vehicle {

    private int axles;
    private boolean adBlue;

    public Truck(double averageConsumption, FuelType typeOfFuel, int cargo,int axles, boolean adBlue) {
        super( averageConsumption,  typeOfFuel,  cargo);
        this.axles = axles;
        this.adBlue = adBlue;
    }


    public int getAxles() {
        return axles;
    }
    public boolean isAdBlue() {
        return adBlue;
    }
}