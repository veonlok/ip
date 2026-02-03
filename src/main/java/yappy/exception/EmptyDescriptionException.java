package yappy.exception;

/**
 * Thrown when a task is created with an empty description.
 */
public class EmptyDescriptionException extends YappyException {
    /**
     * Creates a new EmptyDescriptionException for the specified task type.
     *
     * @param taskType The type of task that has an empty description (e.g., "todo", "deadline").
     */
    public EmptyDescriptionException(String taskType) {
        super("Oops! The description of a " + taskType + " cannot be empty.");
    }
}
