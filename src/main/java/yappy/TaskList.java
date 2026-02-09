package yappy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static yappy.Messages.MESSAGE_INVALID_TASK_INDEX;
import yappy.exception.InvalidTaskIndexException;

/**
 * Manages a collection of tasks using the singleton pattern.
 * Provides methods to add, remove, and manipulate tasks.
 */
public class TaskList {
    // TODO: Make the elements of tasklist unique
    private static TaskList instance;
    private final List<Task> tasks;

    private TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the singleton instance of the TaskList.
     * Creates a new instance if one does not exist.
     *
     * @return The singleton TaskList instance.
     */
    public static synchronized TaskList getInstance() {
        if (instance == null) {
            instance = new TaskList();
        }
        return instance;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to add.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Sets the completion status of a task at the specified index.
     *
     * @param taskIndex   The zero-based index of the task.
     * @param isCompleted {@code true} to mark as completed, {@code false} to mark as not completed.
     * @return The modified task.
     * @throws InvalidTaskIndexException If the index is out of bounds.
     */
    public Task setTaskCompletion(int taskIndex, boolean isCompleted) throws InvalidTaskIndexException {
        if (taskIndex < 0 || taskIndex >= this.tasks.size()) {
            throw new InvalidTaskIndexException(String.format(MESSAGE_INVALID_TASK_INDEX, this.tasks.size()));
        }

        Task task = this.tasks.get(taskIndex);
        task.setCompletion(isCompleted);
        return task;
    }

    /**
     * Resets the singleton instance for testing purposes.
     * This allows tests to start with a fresh TaskList instance.
     * <p>
     * <b>Warning:</b> This method should only be used in test code.
     */
    static void resetForTesting() {
        instance = null;
    }

    /**
     * Removes a task at the specified index from the list.
     *
     * @param taskIndex The zero-based index of the task to remove.
     * @return The removed task.
     * @throws InvalidTaskIndexException If the index is out of bounds.
     */
    public Task removeTask(int taskIndex) throws InvalidTaskIndexException {
        if (taskIndex < 0 || taskIndex >= this.tasks.size()) {
            throw new InvalidTaskIndexException(String.format(MESSAGE_INVALID_TASK_INDEX, this.tasks.size()));
        }

        Task task = this.tasks.remove(taskIndex);
        return task;
    }

    /**
     * Gets an unmodifiable view of the task list.
     * Used for saving tasks to storage.
     *
     * @return Unmodifiable list of tasks
     */
    public List<? extends Task> getTasks() {
        return Collections.unmodifiableList(this.tasks);
    }

    /**
     * Loads tasks from a list into the task list.
     * Clears any existing tasks before loading.
     *
     * @param tasks The list of tasks to load
     */
    public void loadTasks(List<? extends Task> tasks) {
        this.tasks.clear();
        this.tasks.addAll(tasks);
    }

    /**
     * Finds all tasks containing the specified keyword in their name.
     * The search is case-insensitive.
     *
     * @param keyword The keyword to search for.
     * @return A list of tasks containing the keyword.
     */
    public List<Task> findTasks(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        return this.tasks.stream()
                .filter(task -> task.getName().toLowerCase().contains(lowerKeyword))
                .collect(Collectors.toList());
    }

    /**
     * Returns a formatted string representation of all tasks in the list.
     * Each task is numbered starting from 1.
     *
     * @return The formatted task list, or a message if the list is empty.
     */
    @Override
    public String toString() {
        if (this.tasks.isEmpty()) {
            return "Your To-Do List is empty! Time for a nap?";
        }

        return IntStream.range(0, this.tasks.size())
        .mapToObj(i -> (i + 1) + ". " + this.tasks.get(i))
        .collect(Collectors.joining("\n", "--- My To-Do List ---\n","\n"));
    }
}
