import ie.interview.extract.RecordExtractor;
import ie.interview.exception.CustomerFuntimeRuntimeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class RecordExtractorTest {

    @Test
    public void should_throw_exception_if_file_does_not_exist() {
        RecordExtractor recordExtractor = new RecordExtractor("non_exist_file.txt");
        assertThrows(CustomerFuntimeRuntimeException.class, new Executable() {
            @Override
            public void execute() {
                recordExtractor.readFile();
            }
        });
    }

    @Test
    public void should_skip_the_line_if_file_has_invalid_json() {
        String fileName = getClass().getResource("customers_invalid_json.txt").getFile();
        RecordExtractor recordExtractor = new RecordExtractor(fileName);
        Assertions.assertEquals(31, recordExtractor.readFile().count());
    }

    @Test
    public void should_skip_the_line_if_field_has_null_value() {
        String fileName = getClass().getResource("customers_with_null_field.txt").getFile();
        RecordExtractor recordExtractor = new RecordExtractor(fileName);
        Assertions.assertEquals(recordExtractor.readFile().count(), 31);
    }

    @Test
    public void should_skip_the_line_if_field_does_not_exist() {
        String fileName = getClass().getResource("customers_with_one_field_missing.txt").getFile();
        RecordExtractor recordExtractor = new RecordExtractor(fileName);
        Assertions.assertEquals(31, recordExtractor.readFile().count());
    }
}
