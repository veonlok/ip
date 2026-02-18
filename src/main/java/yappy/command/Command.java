package yappy.command;

import yappy.task.TaskList;
import yappy.exception.YappyException;
import yappy.task.TaskList;

/**
 * Abstract base class for all commands in the Yappy chatbot.
 */
public abstract class Command {
    private final String commandWord;

    /**
     * Creates a Command with the specified command word.
     *
     * @param commandWord The command word for this command
     */
    protected Command(String commandWord) {
        this.commandWord = commandWord;
    }

    /**
     * Executes the command.
     *
     * @param tasks The task list to operate on
     * @return The response message to display
     * @throws YappyException If the command execution fails
     */
    public abstract String execute(TaskList tasks) throws YappyException;

    /**
     * Returns whether this command should exit the chat loop.
     *
     * @return true if the application should exit after this command
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Returns the command word for this command.
     *
     * @return The command word string
     */
    public String getCommandWord() {
        return commandWord;
    }
}
