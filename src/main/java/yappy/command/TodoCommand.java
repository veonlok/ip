package yappy.command;

import static yappy.Messages.MESSAGE_TASK_ADDED;

import yappy.TaskList;
import yappy.Todo;
import yappy.exception.YappyException;

/**
 * Adds a todo task to the task list.
 */
public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    private final String name;

    /**
     * Creates a TodoCommand to add a todo with the specified name.
     *
     * @param name The description of the todo task
     */
    public TodoCommand(String name) {
        this.name = name;
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
        tasks.add(new Todo(name));
        return String.format(MESSAGE_TASK_ADDED, name);
    }
}
