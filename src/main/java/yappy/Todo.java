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

	@Override
	public String toString() {
		return "[T]" + super.toString();
	}
}