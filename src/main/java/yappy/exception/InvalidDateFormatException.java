package yappy.exception;

/**
 * Thrown when a date string cannot be parsed into a valid date/time.
 */
public class InvalidDateFormatException extends InvalidCommandArgumentException {
    /**
     * Creates a new InvalidDateFormatException with the specified error message.
     *
     * @param message The error message describing the date format issue.
     */
    public InvalidDateFormatException(String message) {
        super(message);
    }
}
