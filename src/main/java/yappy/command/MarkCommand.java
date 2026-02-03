package yappy.command;

import static yappy.Messages.MESSAGE_TASK_MARKED;

import yappy.Task;
import yappy.TaskList;
import yappy.exception.YappyException;

/**
 * Marks a task as completed.
 */
public class MarkCommand extends Command {
    private static final String COMMAND_WORD = "mark";
    private final int targetIndex;

    /**
     * Creates a MarkCommand to mark the task at the specified index as done.
     *
     * @param targetIndex The zero-based index of the task to mark
     */
    public MarkCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Executes the mark command by marking the specified task as completed.
     *
     * @param tasks The task list containing the task to mark.
     * @return A confirmation message with the marked task.
     * @throws InvalidTaskIndexException If the task index is invalid.
     */
    @Override
    public String execute(TaskList tasks) throws YappyException {
        Task task = tasks.setTaskCompletion(targetIndex, true);
        return String.format(MESSAGE_TASK_MARKED, task);
    }
}
