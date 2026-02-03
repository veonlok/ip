package yappy;

/**
 * Represents a simple todo task without any date/time constraints.
 */
public class Todo extends Task {
	/**
	 * Creates a new Todo task with the specified name.
	 *
	 * @param name The description of the todo task.
	 */
	public Todo(String name) {
		super(name);
	}

	/**
	 * Returns a string representation of the todo task.
	 * Format: "[T][status] name"
	 *
	 * @return The formatted string representation.
	 */
	@Override
	public String toString() {
		return "[T]" + super.toString();
	}
}