package beans;

import org.springframework.stereotype.Repository;

/**
 * Created by Juergen on 04.12.2014.
 */
@Repository("Car")
public class Car extends Vehicle {

    public Car(double averageConsumption, FuelType typeOfFuel, int cargo) {
        super(averageConsumption, typeOfFuel, cargo);
    }


}
