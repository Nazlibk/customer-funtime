package ie.interview.exception;

public class CustomerFuntimeRuntimeException extends RuntimeException {

    public CustomerFuntimeRuntimeException(Throwable cause) {
        super(cause);
    }

    public CustomerFuntimeRuntimeException(String message) {
        super(message);
    }

}
