package yappy.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static yappy.Messages.MESSAGE_TASKLIST_HEADER;
import static yappy.Messages.MESSAGE_INVALID_TASK_INDEX;
import static yappy.Messages.MESSAGE_EMPTY_LIST;

import yappy.exception.DuplicateTaskException;
import yappy.exception.InvalidTaskIndexException;

/**
 * Manages a collection of tasks using the singleton pattern.
 * Provides methods to add, remove, and manipulate tasks.
 */
public class TaskList {
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

        assert instance != null : "Singleton instance should not be null after initialization";
        return instance;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to add.
     * @throws DuplicateTaskException if a duplicate task already exists in the list.
     */
    public void addTask(Task task) {
        assert task != null : "Task to add should not be null";

        int initialSize = this.tasks.size();
        boolean isDuplicate = this.tasks.stream()
            .anyMatch(t -> t.equals(task));
        
        if (isDuplicate) {
            throw new DuplicateTaskException();
        }

        this.tasks.add(task);

        assert this.tasks.size() == initialSize + 1 : "Task list size should increase by 1";
        assert this.tasks.contains(task) : "Task list should contain the added task";
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int getSize() {
        int size = this.tasks.size();
        assert size >= 0 : "Task list size should never be negative";
        return size;
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
        assert task != null : "Task retrieved from valid index should not be null";

        task.setCompletion(isCompleted);
        assert task.isCompleted() == isCompleted : "Task completion status should match requested status";

        return task;
    }

    /**
     * Resets the singleton instance for testing purposes.
     * This allows tests to start with a fresh TaskList instance.
     * <p>
     * <b>Warning:</b> This method should only be used in test code.
     */
    public static void resetForTesting() {
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

        int initialSize = this.tasks.size();
        Task task = this.tasks.remove(taskIndex);

        // Postconditions
        assert task != null : "Removed task should not be null";
        assert this.tasks.size() == initialSize - 1 : "Task list size should decrease by 1";
        assert !this.tasks.contains(task) : "Task list should no longer contain removed task";

        return task;
    }

    /**
     * Gets an unmodifiable view of the task list.
     * Used for saving tasks to storage.
     *
     * @return Unmodifiable list of tasks
     */
    public List<? extends Task> getTasks() {
        List<? extends Task> result = Collections.unmodifiableList(this.tasks);
        assert result != null : "Returned task list should not be null";
        assert result.size() == this.tasks.size() : "Returned list size should match internal list";
        return result;
    }

    /**
     * Loads tasks from a list into the task list.
     * Clears any existing tasks before loading.
     *
     * @param tasks The list of tasks to load
     */
    public void loadTasks(List<? extends Task> tasks) {
        assert tasks != null : "Tasks list to load should not be null";

        this.tasks.clear();
        this.tasks.addAll(tasks);

        assert this.tasks.size() == tasks.size() : "Loaded tasks size should match input size";
    }

    /**
     * Finds all tasks containing the specified keyword in their description.
     * The search is case-insensitive.
     *
     * @param keyword The keyword to search for.
     * @return A list of tasks containing the keyword.
     */
    public List<Task> findTasks(String keyword) {
        assert keyword != null : "Search keyword should not be null";

        String lowerKeyword = keyword.toLowerCase();
        List<Task> result = this.tasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(lowerKeyword))
                .collect(Collectors.toList());

        assert result != null : "Result list should not be null";
        assert result.size() <= this.tasks.size() : "Result size should not exceed total tasks";

        return result;
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
            return MESSAGE_EMPTY_LIST;
        }

        String result = IntStream.range(0, this.tasks.size())
            .mapToObj(i -> (i + 1) + ". " + this.tasks.get(i))
            .collect(Collectors.joining("\n", "--- My To-Do List ---\n","\n"));

        assert result != null : "toString result should not be null";
        assert !result.isEmpty() : "toString result should not be empty when tasks exist";

        return result;
    }
}
