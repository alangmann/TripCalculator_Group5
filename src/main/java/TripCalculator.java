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
                int km = Integer.parseInt(parts[0]);
                int slope = Integer.parseInt(parts[1]);
                String routeType = parts[2];
                double specialFee = Double.parseDouble(parts[3]);

                routeList.add(new Routes(km, slope, routeType, specialFee));
            }
            System.out.println(line);
            count++;
        }
    }

    public double calculateCO2onDistance()
    {
        double co2value = 0;
        for(Routes route : routeList)
        {
            //0.1325 = CO2 Wert für 5l/100km
            co2value += route.getKm() + 0.1325;
        }
        return co2value;
    }

    public static void main(String[] args) {
        TripCalculator tc = new TripCalculator();
        try {
            tc.readRoutesCSV();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
