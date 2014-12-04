import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by Juergen on 27.11.2014.
 */
public class TripCalculator {

    private LinkedList<Routes> routeList = new LinkedList<Routes>();


    public void readRoutesCSV() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\trunk\\src\\main\\resources\\routes.csv"));
        String line = "";
        int count = 0;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(";");
            if (count > 0) {
                double km = Double.parseDouble(parts[0].replace(',', '.'));
                double slope = Double.parseDouble(parts[1].replace(',', '.'));
                String routeType = parts[2];
                double specialFee = Double.parseDouble(parts[3].replace(',', '.'));
                routeList.add(new Routes(km, slope, routeType, specialFee));
            }
            System.out.println(line);
            count++;
        }
    }


    // User Story 2
    public double calculateCO2onDistance() {
        double co2value = 0;
        for (Routes route : routeList) {
            //0.1325 = CO2 Wert für 5l/km
            co2value += route.getKm() * 0.1325;
        }
        return co2value;
    }

    // User Story 3
    public double calculateCO2onDistanceAndSlope() {
        double co2 =0;
        for (Routes route : routeList) {
            co2 += calculateCO2onDistance()* ( 1 + (route.getSlope() / 10000));
        }
        return co2;
    }

    public double calculateCO2onRoute()
    {   double co2=0;
        for(Routes route : routeList)
        {   double factor = 0;
            if(route.getRouteType().equals("Highway"))
            {
                factor = 1;
            }
            else if(route.getRouteType().equals("CountryRoad"))
            {
                factor = 1.2;
            }

            else if(route.getRouteType().equals("GravelRoad"))
            {
                factor = 2;
            }
            co2+=calculateCO2onDistance()*factor;
        }
        return co2;
    }

    public static void main(String[] args) {
        TripCalculator tc = new TripCalculator();
        try {
            tc.readRoutesCSV();
            System.out.println("CO2 on distance: " + tc.calculateCO2onDistance());
            System.out.println("CO2 on distance and slope: " + tc.calculateCO2onDistanceAndSlope());
            System.out.println("CO2 on route: "+tc.calculateCO2onRoute());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
