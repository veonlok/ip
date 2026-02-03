package yappy.exception;

/**
 * Thrown when an unrecognized command is entered.
 */
public class UnknownCommandException extends YappyException {
    public UnknownCommandException(String message) {
        super(message);
    }
}
