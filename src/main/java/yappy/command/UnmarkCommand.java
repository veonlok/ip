package yappy.command;

import static yappy.Messages.MESSAGE_TASK_UNMARKED;

import yappy.Task;
import yappy.TaskList;
import yappy.exception.YappyException;

/**
 * Marks a task as not completed.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    private final int targetIndex;

    /**
     * Creates an UnmarkCommand to mark the task at the specified index as not done.
     *
     * @param targetIndex The zero-based index of the task to unmark
     */
    public UnmarkCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Executes the unmark command by marking the specified task as not completed.
     *
     * @param tasks The task list containing the task to unmark.
     * @return A confirmation message with the unmarked task.
     * @throws InvalidTaskIndexException If the task index is invalid.
     */
    @Override
    public String execute(TaskList tasks) throws YappyException {
        Task task = tasks.setTaskCompletion(targetIndex, false);
        return String.format(MESSAGE_TASK_UNMARKED, task);
    }
}
