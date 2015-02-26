package beans;

import org.springframework.stereotype.Repository;

/**
 * Created by Juergen on 27.11.2014.
 */
@Repository("Route")
public class Route {
    private double km;
    private double slope;
    private RouteType routeType;
    private double specialFee;

    public Route(double km, double slope, RouteType routeType, double specialFee) {
        this.km = km;
        this.slope = slope;
        this.routeType = routeType;
        this.specialFee = specialFee;
    }

    public double getKm() {
        return km;
    }


    public RouteType getRouteType() {
        return routeType;
    }

    public double getSlope() {
        return slope;
    }


    public double getSpecialFee() {
        return specialFee;
    }


}


