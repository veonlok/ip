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

    @Override
    public String toString() {
        return String.format("[%s] %s",
                             this.isCompleted ? "X" : " ",
                             this.NAME);
    }
}