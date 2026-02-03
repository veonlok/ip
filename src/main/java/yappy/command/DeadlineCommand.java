package yappy.command;

import static yappy.Messages.MESSAGE_TASK_ADDED;

import java.time.LocalDateTime;

import yappy.Deadline;
import yappy.TaskList;
import yappy.exception.YappyException;

/**
 * Adds a deadline task to the task list.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    private final String name;
    private final LocalDateTime by;

    /**
     * Creates a DeadlineCommand to add a deadline with the specified name and due date.
     *
     * @param name The description of the deadline task
     * @param by   The due date and time
     */
    public DeadlineCommand(String name, LocalDateTime by) {
        this.name = name;
        this.by = by;
    }

    @Override
    public String execute(TaskList tasks) throws YappyException {
        tasks.add(new Deadline(name, by));
        return String.format(MESSAGE_TASK_ADDED, name);
    }
}
