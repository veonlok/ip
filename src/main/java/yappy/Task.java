package yappy;

/**
 * Represents a task with a name and completion status.
 * Base class for specific task types like Todo, Deadline, and Event.
 */
public class Task {
    private final String NAME;
    private boolean isCompleted;

    public Task(String name) {
        this.NAME = name;
        this.isCompleted = false;
    }

    public void setCompletion(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    /**
     * Gets the name/description of the task.
     *
     * @return The task name
     */
    public String getName() {
        return this.NAME;
    }

    /**
     * Checks if the task is completed.
     *
     * @return true if the task is completed, false otherwise
     */
    public boolean isCompleted() {
        return this.isCompleted;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s",
                             this.isCompleted ? "X" : " ",
                             this.NAME);
    }
}