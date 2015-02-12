package beans;

/**
 * Created by Julian on 04.12.2014.
 */
public enum RouteType {

    Highway(1),
    CountryRoad(1.2),
    GravelRoad(2);
    private final double factor;
    RouteType(double factor) {
        this.factor = factor;
    }

    public double getFactor()
    {
        return this.factor;
    }
}
