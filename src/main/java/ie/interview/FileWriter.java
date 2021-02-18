package ie.interview;

import ie.interview.exception.CustomerFuntimeRuntimeException;
import ie.interview.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileWriter {

    private static final Logger logger = LoggerFactory.getLogger(FileWriter.class);

    /**
     * Gets the stream of customers and print userId and Name in a .txt file.
     *
     * @param customers
     */
    public void writeFile(final Stream<Customer> customers) {
        String outputFilename = "output.txt";
        Path path = Paths.get(outputFilename);

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            logger.info("Writing file output.txt");
            writer.write("user_id|name\n");
            customers.forEachOrdered(customer -> {
                try {
                    StringBuilder stringBuilder = new StringBuilder();
                    String outputRecord = stringBuilder.append(customer.getUserId())
                            .append("|").append(customer.getName()).append("\n").toString();
                    logger.info(outputRecord);
                    writer.write(outputRecord);
                } catch (IOException e) {
                    logger.error("Cannot write to file " + outputFilename, e);
                    throw new CustomerFuntimeRuntimeException(e);
                }
            });
            logger.info("Finished writing " + outputFilename + " file");
        } catch (IOException e) {
            logger.error("Error on BufferedWriter", e);
        }
    }
}
