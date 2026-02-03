package yappy.exception;

/**
 * Thrown when a date string cannot be parsed into a valid date/time.
 */
public class InvalidDateFormatException extends InvalidCommandArgumentException {
    public InvalidDateFormatException(String message) {
        super(message);
    }
}
