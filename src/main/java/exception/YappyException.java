package exception;

/**
 * Base exception class for all Yappy-specific exceptions.
 */
public class YappyException extends RuntimeException {
    public YappyException(String message) {
        super(message);
    }
}
