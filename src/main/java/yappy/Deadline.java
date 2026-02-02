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

	@Override
	public String toString() {
		return "[D]" + super.toString() + String.format(" (by: %s)", this.by);
	}
}
