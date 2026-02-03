package yappy.exception;

/**
 * Thrown when attempting to access a task with an invalid index.
 */
public class InvalidTaskIndexException extends YappyException {
    /**
     * Creates a new InvalidTaskIndexException with the specified error message.
     *
     * @param message The error message describing the index issue.
     */
    public InvalidTaskIndexException(String message) {
        super(message);
    }
}
