package ie.interview.extract;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import ie.interview.model.Customer;
import ie.interview.exception.CustomerFuntimeRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

public class RecordExtractor {

    private static final Logger logger = LoggerFactory.getLogger(RecordExtractor.class);

    private final String fileName;

    private final ObjectMapper mapper;

    public RecordExtractor(String fileName) {
        this.fileName = fileName;
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);
    }

    /**
     * return stream of all the customers
     *
     * @return stream of all the customers
     */
    public Stream<Optional<Customer>> readFile() {

        Stream<Optional<Customer>> customers;
        if (!Files.exists(Paths.get(fileName))) {
            throw new CustomerFuntimeRuntimeException("Cannot find file: " + fileName);
        }

        logger.info("Reading input file: " + fileName);
        try {
            Stream<String> customersJson = Files.lines(Paths.get(fileName));
            customers = customersJson.parallel().map(line -> getCustomer(line))
                    .filter(customer -> customer.isPresent());
        } catch (Exception e) {
            logger.error("Cannot read input file: " + fileName, e);
            throw new CustomerFuntimeRuntimeException(e);
        }
        return customers;
    }

    /**
     * Gets the json line for customer and returns Customer object for that json.
     * <p>
     * Assumption: we ignore the corrupt Json record and continue with the valid Json records.
     *
     * @param line Json string that model a customer
     * @return Java Bean that model a customer
     */
    public Optional<Customer> getCustomer(final String line) {

        try {
            Customer customer = mapper.readValue(line, Customer.class);
            return Optional.of(customer);
        } catch (Exception e) {
            logger.error("Error on deserializing json. json: " + line, e);
            return Optional.empty();
        }
    }
}
