import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by Juergen on 27.11.2014.
 */


public class TripCalculator {

    private LinkedList<Route> routeList = new LinkedList<Route>();

    private static TripCalculator theInstance;
    private TripCalculator(){}
    public static TripCalculator getInstance()
    {
        if(theInstance==null)
        {
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

                switch(routeType)
                {
                    case "Highway": rt = RouteType.Highway;break;
                    case "GravelRoad": rt = RouteType.GravelRoad;break;
                    default: rt = RouteType.CountryRoad;break;
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
        String both="";
        String line ="";
        int count = 0;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(";");
            if (count > 0) {
                String dayOfWeekMethod = parts[0];
                if(dayOfWeekMethod.equalsIgnoreCase(dayOfWeek)) {
                    double diesel = Double.parseDouble(parts[1].replace(',', '.'));
                    double patrol = Double.parseDouble(parts[2].replace(',', '.'));
                    both=diesel+";"+patrol;
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
            if(route.getSlope()>=0) {
                co2 += route.getKm() * 0.1325 * (1 + (route.getSlope() / 10000));
            }
            else
            {
                co2+=0;
            }
        }
        return co2;
    }

    // User Story 4
    public double calculateCO2onRoute() {
        double co2 = 0;
        for (Route route : routeList) {
            if(route.getSlope()>=0) {
                co2 += route.getKm() * 0.1325 * (1 + (route.getSlope() / 10000)) * route.getRouteType().getFactor();
            }
            else
            {
                co2+=0;
            }
        }
        return co2;
    }
    /**
     * 0,0265kg/km at 1l/100km (Diesel)
     * 0,0236kg/km at 1l/100km (Patrol)
     *
     **/
    public double calculateCO2onCar()
    {   double co2 = 0;
        Car car = new Car(5.0,FuelType.Diesel,100);
        Truck truck = new Truck(5.0,FuelType.Diesel,100,4,true);
        for(Route route : routeList)
        {
            if(route.getSlope()>=0) {

                co2 += route.getKm() * ((car.getAverageConsumption() * 0.0265) + (0.005 * car.getCargo()*0.0265)) * (1 + (route.getSlope() / 10000)) * route.getRouteType().getFactor();
            }
            else
            {
                co2+=0;
            }
        }


        return co2;

    }

    public double calculateCO2onTruck(Truck truck)
    {
        double co2 = 0;

        for(Route route : routeList)
        {
            if(route.getSlope()>=0) {
                co2 += (route.getKm() * ((truck.getAverageConsumption() * 0.0265) + (0.0005 * truck.getCargo()*0.0265)) * (1 + (route.getSlope() / 10000)) * route.getRouteType().getFactor())*93/100;
            }
            else
            {
                co2+=0;
            }

        }
        return co2;
    }

    public double calculateCostOfRouteSegment() throws IOException {
        double co2=0;
        String dayOfWeek="Monday";
        Vehicle vehicle = new Truck(35.0,FuelType.Diesel,20000,4,false);
        for(Route route : routeList)
        {

            if(vehicle instanceof Truck)
            {
                if(vehicle.getTypeOfFuel() == FuelType.Diesel)
                {
                    double diesel = Double.parseDouble(this.readFuelsCSV(dayOfWeek).split(";")[0]);
                    co2+= route.getKm() * (vehicle.getAverageConsumption()+ vehicle.getCargo()/100 * 0.05) * diesel + route.getSpecialFee() * 5.0625;
                }
                else
                {
                    double patrol = Double.parseDouble(this.readFuelsCSV(dayOfWeek).split(";")[0]);
                    co2+= route.getKm() * (vehicle.getAverageConsumption()+ vehicle.getCargo()/100 * 0.05) * patrol + route.getSpecialFee() * 5.0625;

                }

            }

            else if(vehicle instanceof Car)
            {
                if(vehicle.getTypeOfFuel() == FuelType.Diesel)
                {
                    double diesel = Double.parseDouble(this.readFuelsCSV(dayOfWeek).split(";")[0]);
                    co2+= route.getKm() * (vehicle.getAverageConsumption()+ vehicle.getCargo()/100 * 0.5) * diesel + route.getSpecialFee() * 5.0625;
                }

                else
                {
                    double patrol = Double.parseDouble(this.readFuelsCSV(dayOfWeek).split(";")[0]);
                    co2+= route.getKm() * (vehicle.getAverageConsumption()+ vehicle.getCargo()/100 * 0.5) * patrol + route.getSpecialFee() * 5.0625;

                }
            }
        }

        return co2;
    }

}
