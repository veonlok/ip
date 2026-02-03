package yappy.command;

import yappy.TaskList;
import yappy.exception.YappyException;

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
        this.errorMessage = errorMessage;
    }

    @Override
    public String execute(TaskList tasks) throws YappyException {
        return errorMessage;
    }
}
