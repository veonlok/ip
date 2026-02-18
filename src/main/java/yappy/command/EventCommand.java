package yappy.command;

import static yappy.Messages.MESSAGE_EVENT_START_AFTER_END;
import static yappy.Messages.MESSAGE_EVENT_ZERO_LENGTH;
import static yappy.Messages.MESSAGE_TASK_ADDED;

import java.time.LocalDateTime;

import yappy.exception.InvalidEventTimeException;
import yappy.exception.YappyException;
import yappy.task.Event;
import yappy.task.TaskList;

/**
 * Adds an event task to the task list.
 */
public class EventCommand extends Command {
    private final String description;
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Creates an EventCommand to add an event with the specified description and time range.
     *
     * @param description The description of the event
     * @param from The start date and time
     * @param to   The end date and time
     */
    public EventCommand(String description, LocalDateTime from, LocalDateTime to) {
        super("event");
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the event command by adding a new Event task to the task list.
     * Validates that the start time is before the end time.
     *
     * @param tasks The task list to add the event to.
     * @return A confirmation message indicating the task was added.
     * @throws InvalidEventTimeException If the start time is after or equal to the end time.
     */
    @Override
    public String execute(TaskList tasks) throws YappyException {
        if (from.isAfter(to)) {
            throw new InvalidEventTimeException(MESSAGE_EVENT_START_AFTER_END);
        }
        if (from.isEqual(to)) {
            throw new InvalidEventTimeException(MESSAGE_EVENT_ZERO_LENGTH);
        }
        tasks.addTask(new Event(description, from, to));
        return String.format(MESSAGE_TASK_ADDED, description);
    }
}
