package ie.interview.calculation;

import ie.interview.model.Customer;

import java.util.Optional;

/**
 * This class implements the algorithm used to calculate distance of a customer from office
 */
public class DistanceCalculator {

    private final double officeLat;
    private final double officeLong;
    private final double earthRadius;

    public DistanceCalculator(double officeLatitude, double officeLong, double earthRadius) {
        this.officeLat = officeLatitude;
        this.officeLong = officeLong;
        this.earthRadius = earthRadius;
    }

    /**
     * return distance of a customer from the office
     *
     * @param customer
     * @return distance of customer from office
     */
    public double getDistance(Optional<Customer> customer) {
        double deltaLong = Math.abs(officeLong - customer.get().getLongitude());
        double cosDeltaLong = Math.cos(deltaLong);

        double sinOfLat = Math.sin(officeLat) * Math.sin(customer.get().getLatitude());
        double cosOfLat = Math.cos(officeLat) * Math.cos(customer.get().getLatitude()) * cosDeltaLong;
        double deltaSigma = Math.acos(sinOfLat + cosOfLat);

        double radian = deltaSigma * Math.PI / 180;
        return earthRadius * radian;
    }
}
