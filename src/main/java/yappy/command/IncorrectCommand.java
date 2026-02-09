package yappy.command;

import yappy.exception.YappyException;
import yappy.task.TaskList;

/**
 * Represents an incorrect command. Returns an error message when executed.
 */
public class IncorrectCommand extends Command {
    private final String errorMessage;

    /**
     * Creates an IncorrectCommand with the specified error message.
     *
     * @param errorMessage The error message to display
     */
    public IncorrectCommand(String errorMessage) {
        super("incorrect");
        this.errorMessage = errorMessage;
    }

    /**
     * Executes the incorrect command by returning the error message.
     *
     * @param tasks The task list (not used for incorrect command).
     * @return The error message describing what went wrong.
     */
    @Override
    public String execute(TaskList tasks) throws YappyException {
        return errorMessage;
    }
}
