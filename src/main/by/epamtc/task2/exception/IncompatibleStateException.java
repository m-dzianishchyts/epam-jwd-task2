package by.epamtc.task2.exception;

public class IncompatibleStateException extends Exception {

    public IncompatibleStateException() {
        super();
    }

    public IncompatibleStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncompatibleStateException(Throwable cause) {
        super(cause);
    }

    public IncompatibleStateException(String message) {
        super(message);
    }
}
