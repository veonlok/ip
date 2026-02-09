package yappy.command;

import yappy.Task.TaskList;
import yappy.exception.YappyException;

/**
 * Lists all tasks in the task list.
 */
public class ListCommand extends Command {
    private static final String COMMAND_WORD = "list";

    /**
     * Executes the list command by displaying all tasks in the task list.
     *
     * @param tasks The task list to display.
     * @return A string containing all tasks, or a message if the list is empty.
     */
    @Override
    public String execute(TaskList tasks) throws YappyException {
        if (tasks.getSize() == 0) {
            return "Yappy: Your list is empty! Time to add some tasks!";
        }
        return "Yappy: Here's your list:\n" + tasks;
    }
}
