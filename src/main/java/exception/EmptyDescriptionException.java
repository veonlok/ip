package exception;

/**
 * Thrown when a task is created with an empty description.
 */
public class EmptyDescriptionException extends YappyException {
    public EmptyDescriptionException(String taskType) {
        super("Oops! The description of a " + taskType + " cannot be empty.");
    }
}
