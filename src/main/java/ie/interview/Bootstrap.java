package ie.interview;

import com.beust.jcommander.JCommander;
import ie.interview.calculation.DistanceCalculator;
import ie.interview.extract.RecordExtractor;
import ie.interview.model.Customer;
import ie.interview.model.Pair;

import java.util.*;
import java.util.stream.Stream;

public class Bootstrap {

    private static final double OFFICE_LAT = 53.339428;
    private static final double OFFICE_LONG = -6.257664;
    private static final double EARTH_RADIUS = 6371.009;
    private static final double MAX_RADIUS_THRESHOLD = 100d;

    public static void main(String[] args) {

        final RunArgs runArgs = new RunArgs();
        JCommander jCommander = JCommander.newBuilder()
                .addObject(runArgs)
                .build();
        jCommander.parse(args);

        if (runArgs.isHelp()) {
            jCommander.usage();
            return;
        }

        RecordExtractor recordExtractor = new RecordExtractor(runArgs.getInputFileName());
        Stream<Optional<Customer>> customers = recordExtractor.readFile();

        DistanceCalculator distanceCalculator = new DistanceCalculator(OFFICE_LAT, OFFICE_LONG, EARTH_RADIUS);
        Stream<Pair<Customer, Double>> customerDistancePair = customers.map(
                customer -> new Pair<>(customer.get(), distanceCalculator.getDistance(customer)));
        Stream<Customer> inviteList = getInvitedList(customerDistancePair);
        Stream<Customer> sortedInviteList = inviteList.sorted();

        FileWriter fileWriter = new FileWriter();
        fileWriter.writeFile(sortedInviteList);
    }

    /**
     * getInvitedList method gets the pair<customer, distance> and returns the customers who are in the radius
     * of less than 100km to office.
     *
     * @param customers
     * @return
     */
    public static Stream<Customer> getInvitedList(Stream<Pair<Customer, Double>> customers) {
        return customers
                .filter(customer -> customer.getValue() - MAX_RADIUS_THRESHOLD < 0)
                .map(customer -> customer.getKey());
    }
}
