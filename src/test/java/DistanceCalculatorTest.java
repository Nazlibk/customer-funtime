import ie.interview.model.Customer;
import ie.interview.calculation.DistanceCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class DistanceCalculatorTest {

    private static final double OFFICE_LAT = 53.339428;
    private static final double OFFICE_LONG = -6.257664;
    private static final double EARTH_RADIUS = 6371.009;
    DistanceCalculator distanceCalculator = new DistanceCalculator(OFFICE_LAT, OFFICE_LONG, EARTH_RADIUS);

    @Test
    public void should_calculate_the_correct_distance() {
        Customer customer = new Customer(52.986375, 1, "John", -6.043701);
        Assertions.assertEquals(45.46884804315653, distanceCalculator.getDistance(Optional.of(customer)));
    }

    @Test
    public void distance_from_point_to_itself_should_be_zero() {
        Customer customer = new Customer(0, 1, "John", 0);
        Assertions.assertEquals(0, new DistanceCalculator(0, 0, EARTH_RADIUS).getDistance(Optional.of(customer)));
    }
}
