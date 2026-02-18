package yappy.exception;

import static yappy.Messages.MESSAGE_DUPLICATE_TASK;

/**
 * Exception thrown when attempting to add a duplicate task to the task list.
 */
public class DuplicateTaskException extends YappyException {

    /**
     * Creates a new DuplicateTaskException with the default message.
     */
    public DuplicateTaskException() {
        super(MESSAGE_DUPLICATE_TASK);
    }
}
