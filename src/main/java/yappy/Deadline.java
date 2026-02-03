package yappy;

/**
 * Represents a task with a deadline.
 * Extends Task with a "by" date/time field.
 */
public class Deadline extends Task {
	private String by;
	
	public Deadline(String name, String by) {
		super(name);
		this.by = by;
	}

	/**
	 * Gets the due date/time of the deadline.
	 *
	 * @return The deadline's due date/time
	 */
	public String getBy() {
		return this.by;
	}

	@Override
	public String toString() {
		return "[D]" + super.toString() + String.format(" (by: %s)", this.by);
	}
}
