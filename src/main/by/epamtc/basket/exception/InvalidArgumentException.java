package by.epamtc.basket.exception;

public class InvalidArgumentException extends Exception {

    public InvalidArgumentException() {
        super();
    }

    public InvalidArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidArgumentException(Throwable cause) {
        super(cause);
    }

    public InvalidArgumentException(String message) {
        super(message);
    }
}
