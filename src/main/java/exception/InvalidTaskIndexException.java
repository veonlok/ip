package exception;

/**
 * Thrown when attempting to access a task with an invalid index.
 */
public class InvalidTaskIndexException extends YappyException {
    public InvalidTaskIndexException(String message) {
        super(message);
    }
}
