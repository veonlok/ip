package exception;

/**
 * Thrown when a command receives invalid arguments.
 */
public class InvalidCommandArgumentException extends YappyException {
	public InvalidCommandArgumentException(String message) {
		super(message);
	}
}
