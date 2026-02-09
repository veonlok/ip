package yappy.Task;

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
	 * Creates a new Deadline task with the specified name and due date.
	 *
	 * @param name The description of the deadline task.
	 * @param deadlineBy   The due date and time for the deadline.
	 */
	public Deadline(String name, LocalDateTime deadlineBy) {
		super(name);
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
	 * Format: "[D][status] name (deadlineBy: formatted_date)"
	 *
	 * @return The formatted string representation.
	 */
	@Override
	public String toString() {
		return "[D]" + super.toString() + String.format(" (by: %s)", this.deadlineBy.format(OUTPUT_FORMAT));
	}
}
