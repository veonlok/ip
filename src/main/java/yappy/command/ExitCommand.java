package yappy.command;

import static yappy.Messages.MESSAGE_EXIT;

import yappy.TaskList;
import yappy.exception.YappyException;

/**
 * Exits the application.
 */
public class ExitCommand extends Command {
    private static final String COMMAND_WORD = "exit";

    @Override
    public String execute(TaskList tasks) throws YappyException {
        return MESSAGE_EXIT;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
