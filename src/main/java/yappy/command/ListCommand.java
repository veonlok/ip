package yappy.command;

import yappy.TaskList;
import yappy.exception.YappyException;

/**
 * Lists all tasks in the task list.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    @Override
    public String execute(TaskList tasks) throws YappyException {
        if (tasks.getSize() == 0) {
            return "Yappy: Your list is empty! Time to add some tasks!";
        }
        return "Yappy: Here's your list:\n" + tasks;
    }
}
