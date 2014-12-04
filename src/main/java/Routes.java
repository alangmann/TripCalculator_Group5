/**
 * Created by Juergen on 27.11.2014.
 */
public class Routes {
    private double km;
    private double slope;
    private String routeType;
    private double specialFee;

    public Routes(double km, double slope, String routeType, double specialFee) {
        this.km = km;
        this.slope = slope;
        this.routeType = routeType;
        this.specialFee = specialFee;
    }

    public double getKm() {
        return km;
    }

    public void setKm(double km) {
        this.km = km;
    }

    public String getRouteType() {
        return routeType;
    }

    public void setRouteType(String routeType) {
        this.routeType = routeType;
    }

    public double getSlope() {
        return slope;
    }

    public void setSlope(double slope) {
        this.slope = slope;
    }

    public double getSpecialFee() {
        return specialFee;
    }

    public void setSpecialFee(double specialFee) {
        this.specialFee = specialFee;
    }

}
