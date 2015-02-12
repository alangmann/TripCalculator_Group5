package beans;

/**
 * Created by Juergen on 04.12.2014.
 */
public class Vehicle {
    private double averageConsumption;
    private FuelType typeOfFuel;
    private int cargo;

    public Vehicle(double averageConsumption, FuelType typeOfFuel, int cargo) {
        this.averageConsumption = averageConsumption;
        this.typeOfFuel = typeOfFuel;
        this.cargo = cargo;
    }

    public double getAverageConsumption() {
        return averageConsumption;
    }
    public FuelType getTypeOfFuel() {
        return typeOfFuel;
    }
    public int getCargo() {
        return cargo;
    }

}
