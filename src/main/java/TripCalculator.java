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
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(";");
            System.out.println(line);
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
