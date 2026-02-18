package yappy.task;

/**
 * Represents a task with a name and completion status.
 * Base class for specific task types like Todo, Deadline, and Event.
 */
public class Task {
    private final String name;
    private boolean isCompleted;

    /**
     * Creates a new Task with the specified name.
     * The task is initially marked as not completed.
     *
     * @param name The name/description of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param isCompleted {@code true} to mark the task as completed,
     *                    {@code false} to mark it as not completed.
     */
    public void setCompletion(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    /**
     * Gets the name/description of the task.
     *
     * @return The task name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Checks if the task is completed.
     *
     * @return true if the task is completed, false otherwise
     */
    public boolean isCompleted() {
        return this.isCompleted;
    }

    /**
     * Returns a string representation of the task.
     * Format: "[X] name" if completed, "[ ] name" if not completed.
     *
     * @return The formatted string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s",
                             this.isCompleted ? "X" : " ",
                             this.name);
    }
}