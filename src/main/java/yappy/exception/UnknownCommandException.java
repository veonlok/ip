package yappy.exception;

/**
 * Thrown when an unrecognized command is entered.
 */
public class UnknownCommandException extends YappyException {
    /**
     * Creates a new UnknownCommandException with the specified error message.
     *
     * @param message The error message indicating the command was not recognized.
     */
    public UnknownCommandException(String message) {
        super(message);
    }
}
