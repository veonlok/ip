package yappy.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start and end time.
 * Extends Task with "startTime" and "endTime" fields.
 */
public class Event extends Task {
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

	/**
	 * Creates a new Event task with the specified description and time range.
	 *
	 * @param description The description of the event.
	 * @param startTime The start date and time of the event.
	 * @param endTime   The end date and time of the event.
	 */
	public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
		super(description);
		this.startTime = startTime;
		this.endTime = endTime;
	}

    /**
     * Gets the start date/time of the event.
     *
     * @return The event's start date/time
     */
    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    /**
     * Gets the end date/time of the event.
     *
     * @return The event's end date/time
     */
    public LocalDateTime getEndTime() {
        return this.endTime;
    }

	/**
	 * Returns a string representation of the event task.
	 * Format: "[E][status] description (from: start_date, to: end_date)"
	 *
	 * @return The formatted string representation.
	 */
	@Override
	public String toString() {
		return "[E]" + super.toString() + String.format(" (from: %s, to: %s)", 
					this.startTime.format(OUTPUT_FORMAT),
					this.endTime.format(OUTPUT_FORMAT));
	}
}
