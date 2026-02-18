package yappy.command;

import static yappy.Messages.MESSAGE_EXIT;

import yappy.task.TaskList;
import yappy.exception.YappyException;

/**
 * Exits the application.
 */
public class ExitCommand extends Command {
    /**
     * Creates an ExitCommand.
     */
    public ExitCommand() {
        super("exit");
    }

    /**
     * Executes the exit command by returning the exit message.
     *
     * @param tasks The task list (not used for exit command).
     * @return The exit message to display to the user.
     */
    @Override
    public String execute(TaskList tasks) throws YappyException {
        return MESSAGE_EXIT;
    }

    /**
     * Returns whether this command should exit the application.
     *
     * @return {@code true} as this is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
