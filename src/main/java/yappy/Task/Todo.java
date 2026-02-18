package yappy.task;

/**
 * Represents a simple todo task without any date/time constraints.
 */
public class Todo extends Task {
	/**
	 * Creates a new Todo task with the specified description.
	 *
	 * @param description The description of the todo task.
	 */
	public Todo(String description) {
		super(description);
	}

	@Override
	public String toString() {
		return "[T]" + super.toString();
	}
}