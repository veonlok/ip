package yappy.exception;

/**
 * Thrown when a command receives invalid arguments.
 */
public class InvalidCommandArgumentException extends YappyException {
	/**
	 * Creates a new InvalidCommandArgumentException with the specified error message.
	 *
	 * @param message The error message describing the invalid argument.
	 */
	public InvalidCommandArgumentException(String message) {
		super(message);
	}
}
