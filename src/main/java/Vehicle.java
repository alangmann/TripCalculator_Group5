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

    public void setAverageConsumption(double averageConsumption) {
        this.averageConsumption = averageConsumption;
    }

    public FuelType getTypeOfFuel() {
        return typeOfFuel;
    }

    public void setTypeOfFuel(FuelType typeOfFuel) {
        this.typeOfFuel = typeOfFuel;
    }

    public int getCargo() {
        return cargo;
    }

    public void setCargo(int cargo) {
        this.cargo = cargo;
    }
}
