package yappy;

/**
 * Represents an event task with a start and end time.
 * Extends Task with "from" and "to" fields.
 */
public class Event extends Task {
	private String from;
	private String to;

	public Event(String name, String from, String to) {
		super(name);
		this.from = from;
		this.to = to;
	}

	@Override
	public String toString() {
		return "[E]" + super.toString() + String.format(" (from: %s, to: %s)", 
								this.from,
								this.to);
	}
}
