package yappy.command;

import static yappy.Messages.MESSAGE_TASK_DELETED;

import yappy.Task.Task;
import yappy.Task.TaskList;
import yappy.exception.YappyException;

/**
 * Deletes a task from the task list.
 */
public class DeleteCommand extends Command {
    private static final String COMMAND_WORD = "delete";
    private final int targetIndex;

    /**
     * Creates a DeleteCommand to delete the task at the specified index.
     *
     * @param targetIndex The zero-based index of the task to delete
     */
    public DeleteCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Executes the delete command by removing the specified task from the task list.
     *
     * @param tasks The task list containing the task to delete.
     * @return A confirmation message with the deleted task and remaining task count.
     * @throws InvalidTaskIndexException If the task index is invalid.
     */
    @Override
    public String execute(TaskList tasks) throws YappyException {
        Task task = tasks.removeTask(targetIndex);
        return String.format(MESSAGE_TASK_DELETED, task, tasks.getSize());
    }
}
