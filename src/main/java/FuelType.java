/**
 * Created by Juergen on 04.12.2014.
 */
public enum FuelType {
    Diesel(0.0265),
    Patrol(0.0236);

    // 0.0265 kg/km bei 1l/100km Diesel
    // 0.0235 kg/km bei 1l/100km Patrol

    private double literPer100km;

    FuelType(double literPer100km) {
        this.literPer100km = literPer100km;
    }

    public double getLiterPer100km() {
        return literPer100km;
    }
}
