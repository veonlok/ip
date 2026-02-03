package yappy.exception;

/**
 * Thrown when an event has invalid time constraints
 * (e.g., start time after end time, or zero-length event).
 */
public class InvalidEventTimeException extends InvalidCommandArgumentException {
    /**
     * Creates a new InvalidEventTimeException with the specified error message.
     *
     * @param message The error message describing the time constraint violation.
     */
    public InvalidEventTimeException(String message) {
        super(message);
    }
}
