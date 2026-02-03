package yappy.exception;

/**
 * Thrown when an event has invalid time constraints
 * (e.g., start time after end time, or zero-length event).
 */
public class InvalidEventTimeException extends InvalidCommandArgumentException {
    public InvalidEventTimeException(String message) {
        super(message);
    }
}
