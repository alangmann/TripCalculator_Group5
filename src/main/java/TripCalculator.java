import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by Juergen on 27.11.2014.
 */


public class TripCalculator {

    private LinkedList<Route> routeList = new LinkedList<Route>();


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
                    case "CountryRoad": rt = RouteType.CountryRoad;break;
                }
                double specialFee = Double.parseDouble(parts[3].replace(',', '.'));
                routeList.add(new Route(km, slope, rt, specialFee));
            }
            System.out.println(line);
            count++;
        }
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
    public double calculateCO2onDistanceWithWeight()
    {   double co2 = 0;
        Car car = new Car(5.0,FuelType.Diesel,100);

        for(Route route : routeList)
        {
            co2+=route.getKm() * (0.1325+(car.getCargo()%100)*0.5);
        }
        return co2;

    }


}
