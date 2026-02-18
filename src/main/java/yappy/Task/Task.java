package yappy.task;

/**
 * Represents a task with a description and completion status.
 * Base class for specific task types like Todo, Deadline, and Event.
 */
public class Task {
    private final String description;
    private boolean isCompleted;

    /**
     * Creates a new Task with the specified description.
     * The task is initially marked as not completed.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        assert description != null : "Task description should not be null";

        this.description = description;
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
     * Gets the description of the task.
     *
     * @return The task description
     */
    public String getDescription() {
        assert this.description != null : "Task description should never be null";
        return this.description;
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
     * Format: "[X] description" if completed, "[ ] description" if not completed.
     *
     * @return The formatted string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s",
                             this.isCompleted ? "X" : " ",
                             this.description);
    }
}