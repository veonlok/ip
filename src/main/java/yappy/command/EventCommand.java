package yappy.command;

import static yappy.Messages.MESSAGE_EVENT_START_AFTER_END;
import static yappy.Messages.MESSAGE_EVENT_ZERO_LENGTH;
import static yappy.Messages.MESSAGE_TASK_ADDED;

import java.time.LocalDateTime;

import yappy.Event;
import yappy.TaskList;
import yappy.exception.InvalidEventTimeException;
import yappy.exception.YappyException;

/**
 * Adds an event task to the task list.
 */
public class EventCommand extends Command {
    private static final String COMMAND_WORD = "event";
    private final String name;
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Creates an EventCommand to add an event with the specified name and time range.
     *
     * @param name The description of the event
     * @param from The start date and time
     * @param to   The end date and time
     */
    public EventCommand(String name, LocalDateTime from, LocalDateTime to) {
        this.name = name;
        this.from = from;
        this.to = to;
    }

    @Override
    public String execute(TaskList tasks) throws YappyException {
        if (from.isAfter(to)) {
            throw new InvalidEventTimeException(MESSAGE_EVENT_START_AFTER_END);
        }
        if (from.isEqual(to)) {
            throw new InvalidEventTimeException(MESSAGE_EVENT_ZERO_LENGTH);
        }
        tasks.add(new Event(name, from, to));
        return String.format(MESSAGE_TASK_ADDED, name);
    }
}
