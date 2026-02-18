package yappy.command;

import static yappy.Messages.MESSAGE_TASK_ADDED;

<<< HEAD
import yappy.task.TaskList;
import yappy.task.Todo;
=======
>>>>>>> A-checkstyle
import yappy.exception.YappyException;
import yappy.task.TaskList;
import yappy.task.Todo;

/**
 * Adds a todo task to the task list.
 */
public class TodoCommand extends Command {
    private final String description;

    /**
     * Creates a TodoCommand to add a todo with the specified description.
     *
     * @param description The description of the todo task
     */
    public TodoCommand(String description) {
        super("todo");
        this.description = description;
    }

    /**
     * Executes the todo command by adding a new Todo task to the task list.
     *
     * @param tasks The task list to add the todo to.
     * @return A confirmation message indicating the task was added.
     * @throws YappyException If the task cannot be added.
     */
    @Override
    public String execute(TaskList tasks) throws YappyException {
        tasks.addTask(new Todo(description));
        return String.format(MESSAGE_TASK_ADDED, description);
    }
}
