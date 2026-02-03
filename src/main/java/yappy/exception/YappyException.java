package yappy.exception;

/**
 * Base exception class for all Yappy-specific exceptions.
 */
public class YappyException extends RuntimeException {
    /**
     * Creates a new YappyException with the specified error message.
     *
     * @param message The error message describing what went wrong.
     */
    public YappyException(String message) {
        super(message);
    }
}
