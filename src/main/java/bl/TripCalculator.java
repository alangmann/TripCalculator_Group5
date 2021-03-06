package bl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by Juergen on 27.11.2014.
 */

import beans.*;
import org.springframework.stereotype.Component;

@Component("TripCalculator")
public class TripCalculator {

    private LinkedList<Route> routeList = new LinkedList<Route>();

    private static TripCalculator theInstance;

    private TripCalculator() {
    }

    public static TripCalculator getInstance() {
        if (theInstance == null) {
            theInstance = new TripCalculator();
        }
        return theInstance;
    }

    public void readRoutesCSV() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\trunk\\src\\main\\resources\\routes.csv"));
        String line = "";
        int count = 0;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(";");
            if (count > 0) {
                double km = Double.parseDouble(parts[0].replace(',', '.'));
                double slope = Double.parseDouble(parts[1].replace(',', '.'));
                RouteType rt;
                String routeType = parts[2];

                switch (routeType) {
                    case "Highway":
                        rt = RouteType.Highway;
                        break;
                    case "GravelRoad":
                        rt = RouteType.GravelRoad;
                        break;
                    default:
                        rt = RouteType.CountryRoad;
                        break;
                }
                double specialFee = Double.parseDouble(parts[3].replace(',', '.'));
                routeList.add(new Route(km, slope, rt, specialFee));
            }
            System.out.println(line);
            count++;
        }
    }

    public String readFuelsCSV(String dayOfWeek) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\trunk\\src\\main\\resources\\sprit_db.csv"));
        String both = "";
        String line = "";
        int count = 0;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(";");
            if (count > 0) {
                String dayOfWeekMethod = parts[0];
                if (dayOfWeekMethod.equalsIgnoreCase(dayOfWeek)) {
                    double diesel = Double.parseDouble(parts[1].replace(',', '.'));
                    double patrol = Double.parseDouble(parts[2].replace(',', '.'));
                    both = diesel + ";" + patrol;
                }
            }
            System.out.println(line);
            count++;
        }
        return both;
    }


    // User Story 2
    public double calculateCO2onDistance() {
        double co2value = 0;
        for (Route route : routeList) {
            //0.1325 = CO2 Wert für 5l/km
            co2value += route.getKm() * 0.1325;
        }
        return co2value;
    }

    // User Story 3
    public double calculateCO2onDistanceAndSlope() {
        double co2 = 0;
        for (Route route : routeList) {
            if (route.getSlope() >= 0) {
                co2 += route.getKm() * 0.1325 * (1 + (route.getSlope() / 10000));
            } else {
                co2 += 0;
            }
        }
        return co2;
    }

    // User Story 4
    public double calculateCO2onRoute() {
        double co2 = 0;
        for (Route route : routeList) {
            if (route.getSlope() >= 0) {
                co2 += route.getKm() * 0.1325 * (1 + (route.getSlope() / 10000)) * route.getRouteType().getFactor();
            } else {
                co2 += 0;
            }
        }
        return co2;
    }

    /**
     * 0,0265kg/km at 1l/100km (Diesel)
     * 0,0236kg/km at 1l/100km (Patrol)
     */
    /*
    public double calculateCO2onCar() {
        double co2 = 0;
        beans.Car car = new beans.Car(5.0, beans.FuelType.Diesel, 100);
        beans.Truck truck = new beans.Truck(5.0, beans.FuelType.Diesel, 100, 4, true);
        for (beans.Route route : routeList) {
            if (route.getSlope() >= 0) {

                co2 += route.getKm() * ((car.getAverageConsumption() * 0.0265) + (0.005 * car.getCargo() * 0.0265)) * (1 + (route.getSlope() / 10000)) * route.getRouteType().getFactor();
            } else {
                co2 += 0;
            }
        }


        return co2;

    }

    public double calculateCO2onTruck(beans.Truck truck) {
        double co2 = 0;

        for (beans.Route route : routeList) {
            if (route.getSlope() >= 0) {
                co2 += (route.getKm() * ((truck.getAverageConsumption() * 0.0265) + (0.0005 * truck.getCargo() * 0.0265)) * (1 + (route.getSlope() / 10000)) * route.getRouteType().getFactor()) * 93 / 100;
            } else {
                co2 += 0;
            }

        }
        return co2;
    }
    */

    public double calculateCo2Consumption(Vehicle vehicle) {
        double co2 = 0;
        System.out.println(vehicle.getCargo());
        for (Route route : routeList) {
            if (vehicle instanceof Truck) {
                co2 += (route.getKm() * ((vehicle.getAverageConsumption() * (vehicle.getTypeOfFuel() == FuelType.Diesel ? FuelType.Diesel.getLiterPer100km() :  FuelType.Patrol.getLiterPer100km())) + (0.0005 * vehicle.getCargo() * 0.0265)) * (route.getSlope() >= 0 ? (1 + (route.getSlope() / 10000)) : 1) * route.getRouteType().getFactor()) * (((Truck) vehicle).isAdBlue() ? 0.93 : 1);
            } else {
                co2 += route.getKm() * ((vehicle.getAverageConsumption() * (vehicle.getTypeOfFuel() == FuelType.Diesel ? FuelType.Diesel.getLiterPer100km() :  FuelType.Patrol.getLiterPer100km())) + (0.005 * vehicle.getCargo() * 0.0265)) * (route.getSlope() >= 0 ? (1 + (route.getSlope() / 10000)) : 1) * route.getRouteType().getFactor();
            }
        }
        return co2;
    }

    public double calculateTotalCostOfRoute(Vehicle vehicle, String weekDay, boolean specialFee, boolean axles) throws IOException {
        double cost = 0;
        String fuel = readFuelsCSV(weekDay);
        for(Route route : routeList)
        {

            if(vehicle instanceof Truck)
            {
                Truck truck = (Truck) vehicle;

                cost+= route.getKm() * (vehicle.getAverageConsumption()+vehicle.getCargo()/100*0.05) * (truck.getTypeOfFuel() == FuelType.Diesel ? Double.parseDouble(fuel.split(";")[0]) : Double.parseDouble(fuel.split(";")[0])) + (specialFee ? route.getSpecialFee() : 0) * (axles ? Math.pow(1.5,((Truck) vehicle).getAxles()) : 1);
            }
            else
            {
                Car car = (Car)vehicle;
                cost+= route.getKm() * (vehicle.getAverageConsumption()+vehicle.getCargo()/100*0.5) * (car.getTypeOfFuel() == FuelType.Diesel ? Double.parseDouble(fuel.split(";")[0]) : Double.parseDouble(fuel.split(";")[0])) + (specialFee ? route.getSpecialFee() : 0);
            }
        }
        return cost;
    }


}
