package yappy.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 * Extends Task with a "deadlineBy" date/time field.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
    private final LocalDateTime deadlineBy;

    /**
     * Creates a new Deadline task with the specified description and due date.
     *
     * @param description The description of the deadline task.
     * @param deadlineBy   The due date and time for the deadline.
     */
    public Deadline(String description, LocalDateTime deadlineBy) {
        super(description);
        this.deadlineBy = deadlineBy;
    }

    /**
     * Gets the due date/time of the deadline.
     *
     * @return The deadline's due date/time
     */
    public LocalDateTime getDeadlineBy() {
        return this.deadlineBy;
    }

    /**
     * Returns a string representation of the deadline task.
     * Format: "[D][status] description (deadlineBy: formatted_date)"
     *
     * @return The formatted string representation.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", this.deadlineBy.format(OUTPUT_FORMAT));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deadline other) {
            return super.equals(obj) && this.deadlineBy.equals(other.deadlineBy);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(getDescription(), deadlineBy);
    }
}
