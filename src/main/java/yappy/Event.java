package yappy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start and end time.
 * Extends Task with "from" and "to" fields.
 */
public class Event extends Task {
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    /**
     * Gets the start date/time of the event.
     *
     * @return The event's start date/time
     */
    public LocalDateTime getFrom() {
        return this.from;
    }

    /**
     * Gets the end date/time of the event.
     *
     * @return The event's end date/time
     */
    public LocalDateTime getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s, to: %s)",
                this.from.format(OUTPUT_FORMAT),
                this.to.format(OUTPUT_FORMAT));
    }
}
