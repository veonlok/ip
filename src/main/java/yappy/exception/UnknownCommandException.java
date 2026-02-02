package yappy.exception;

/**
 * Thrown when an unrecognized command is entered.
 */
public class UnknownCommandException extends YappyException {
 	public UnknownCommandException(String command) {
		super("Hey buddy! I don't recognise the command: " + command);
	}
}
