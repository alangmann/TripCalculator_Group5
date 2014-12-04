/**
 * Created by Julian on 27.11.2014.
 */
public class Sprit {

    private String weekDay;
    private double patrolPrice;
    private double dieselPrice;

    public Sprit(String weekDay, double patrolPrice, double dieselPrice) {
        this.weekDay = weekDay;
        this.patrolPrice = patrolPrice;
        this.dieselPrice = dieselPrice;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public double getPatrolPrice() {
        return patrolPrice;
    }

    public void setPatrolPrice(double patrolPrice) {
        this.patrolPrice = patrolPrice;
    }

    public double getDieselPrice() {
        return dieselPrice;
    }

    public void setDieselPrice(double dieselPrice) {
        this.dieselPrice = dieselPrice;
    }
}
