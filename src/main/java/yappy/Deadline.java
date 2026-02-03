package yappy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 * Extends Task with a "by" date/time field.
 */
public class Deadline extends Task {
	private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
	private LocalDateTime by;

	/**
	 * Creates a new Deadline task with the specified name and due date.
	 *
	 * @param name The description of the deadline task.
	 * @param by   The due date and time for the deadline.
	 */
	public Deadline(String name, LocalDateTime by) {
		super(name);
		this.by = by;
	}

	/**
	 * Gets the due date/time of the deadline.
	 *
	 * @return The deadline's due date/time
	 */
	public LocalDateTime getBy() {
		return this.by;
	}

	/**
	 * Returns a string representation of the deadline task.
	 * Format: "[D][status] name (by: formatted_date)"
	 *
	 * @return The formatted string representation.
	 */
	@Override
	public String toString() {
		return "[D]" + super.toString() + String.format(" (by: %s)", this.by.format(OUTPUT_FORMAT));
	}
}
