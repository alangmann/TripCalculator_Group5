import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Juergen on 27.11.2014.
 */
public class TripCalculator {


    public void readRoutesCSV() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir")+"\\trunk\\src\\main\\resources\\routes.csv"));
        String line = "";
        int count = 0;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(";");
            if(count>0)
            {
                int km = Integer.parseInt(parts[0]);
                int slope = Integer.parseInt(parts[1]);
                String routeType = parts[2];
                int specialFee = Integer.parseInt(parts[3]);

                Routes route = new Routes(km,slope,routeType,specialFee);
            }
            System.out.println(line);
            count++;
        }
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
