/**
 * Created by Juergen on 27.11.2014.
 */
public class Routes {
    private int km;
    private int slope;
    private String routtype;
    private int specialfee;

    public Routes(int km, int slope, String routtype, int specialfee) {
        this.km = km;
        this.slope = slope;
        this.routtype = routtype;
        this.specialfee = specialfee;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public String getRouttype() {
        return routtype;
    }

    public void setRouttype(String routtype) {
        this.routtype = routtype;
    }

    public int getSlope() {

        return slope;
    }

    public void setSlope(int slope) {
        this.slope = slope;
    }

    public int getSpecialfee() {
        return specialfee;
    }

    public void setSpecialfee(int specialfee) {
        this.specialfee = specialfee;
    }

}
