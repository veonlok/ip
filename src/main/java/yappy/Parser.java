package yappy;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static yappy.Messages.MESSAGE_INVALID_ARGS_EXIT;
import static yappy.Messages.MESSAGE_INVALID_ARGS_LIST;
import static yappy.Messages.MESSAGE_INVALID_DATE;
import static yappy.Messages.MESSAGE_INVALID_FORMAT_DEADLINE;
import static yappy.Messages.MESSAGE_INVALID_FORMAT_DELETE;
import static yappy.Messages.MESSAGE_INVALID_FORMAT_EVENT;
import static yappy.Messages.MESSAGE_INVALID_FORMAT_MARK;
import static yappy.Messages.MESSAGE_INVALID_FORMAT_UNMARK;
import static yappy.Messages.MESSAGE_UNKNOWN_COMMAND;
import yappy.command.Command;
import yappy.command.DeadlineCommand;
import yappy.command.DeleteCommand;
import yappy.command.EventCommand;
import yappy.command.ExitCommand;
import yappy.command.ListCommand;
import yappy.command.MarkCommand;
import yappy.command.TodoCommand;
import yappy.command.UnmarkCommand;
import yappy.exception.EmptyDescriptionException;
import yappy.exception.InvalidCommandArgumentException;
import yappy.exception.InvalidDateFormatException;
import yappy.exception.UnknownCommandException;

/**
 * Parses user input commands for the Yappy chatbot.
 * 
 * <p>Grammar:
 * <pre>
 * expression       ::= exit-expression
 *                    | list-expression
 *                    | mark-expression
 *                    | unmark-expression
 *                    | delete-expression
 *                    | todo-expression
 *                    | deadline-expression
 *                    | event-expression
 *
 * exit-expression     ::= EXIT
 * list-expression     ::= LIST
 * mark-expression     ::= MARK int
 * unmark-expression   ::= UNMARK int
 * delete-expression   ::= DELETE int
 * todo-expression     ::= TODO String
 * deadline-expression ::= DEADLINE String /by String
 * event-expression    ::= EVENT String /from String /to String
 * </pre>
 */
public class Parser {
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input and returns the corresponding Command.
     *
     * @param userInput The raw user input string
     * @return The Command to execute
     * @throws UnknownCommandException         If the command is not recognized
     * @throws InvalidCommandArgumentException If the command arguments are invalid
     */
    public Command parseCommand(String userInput) throws UnknownCommandException, InvalidCommandArgumentException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.strip());

        if (!matcher.matches()) {
            throw new UnknownCommandException(MESSAGE_UNKNOWN_COMMAND);
        }

        final String commandWord = matcher.group("commandWord").toLowerCase();
        final String arguments = matcher.group("arguments").strip();

        return switch (commandWord) {
            case "exit" -> parseExitCommand(arguments);
            case "list" -> parseListCommand(arguments);
            case "delete" -> parseDeleteCommand(arguments);
            case "mark" -> parseMarkCommand(arguments);
            case "unmark" -> parseUnmarkCommand(arguments);
            case "todo" -> parseTodoCommand(arguments);
            case "deadline" -> parseDeadlineCommand(arguments);
            case "event" -> parseEventCommand(arguments);
            default -> throw new UnknownCommandException(MESSAGE_UNKNOWN_COMMAND);
        };
    }

    /**
     * Parses an exit command.
     */
    private Command parseExitCommand(String arguments) throws InvalidCommandArgumentException {
        if (!arguments.isBlank()) {
            throw new InvalidCommandArgumentException(MESSAGE_INVALID_ARGS_EXIT);
        }
        return new ExitCommand();
    }

    /**
     * Parses a list command.
     */
    private Command parseListCommand(String arguments) throws InvalidCommandArgumentException {
        if (!arguments.isBlank()) {
            throw new InvalidCommandArgumentException(MESSAGE_INVALID_ARGS_LIST);
        }
        return new ListCommand();
    }

    /**
     * Parses a delete command.
     */
    private Command parseDeleteCommand(String arguments) throws InvalidCommandArgumentException {
        if (arguments.isBlank()) {
            throw new InvalidCommandArgumentException(MESSAGE_INVALID_FORMAT_DELETE);
        }
        try {
            int taskIndex = Integer.parseInt(arguments) - 1;
            return new DeleteCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new InvalidCommandArgumentException(MESSAGE_INVALID_FORMAT_DELETE);
        }
    }

    /**
     * Parses a mark command.
     */
    private Command parseMarkCommand(String arguments) throws InvalidCommandArgumentException {
        if (arguments.isBlank()) {
            throw new InvalidCommandArgumentException(MESSAGE_INVALID_FORMAT_MARK);
        }
        try {
            int taskIndex = Integer.parseInt(arguments) - 1;
            return new MarkCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new InvalidCommandArgumentException(MESSAGE_INVALID_FORMAT_MARK);
        }
    }

    /**
     * Parses an unmark command.
     */
    private Command parseUnmarkCommand(String arguments) throws InvalidCommandArgumentException {
        if (arguments.isBlank()) {
            throw new InvalidCommandArgumentException(MESSAGE_INVALID_FORMAT_UNMARK);
        }
        try {
            int taskIndex = Integer.parseInt(arguments) - 1;
            return new UnmarkCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new InvalidCommandArgumentException(MESSAGE_INVALID_FORMAT_UNMARK);
        }
    }

    /**
     * Parses a todo command.
     */
    private Command parseTodoCommand(String arguments) throws EmptyDescriptionException {
        if (arguments.isBlank()) {
            throw new EmptyDescriptionException("todo");
        }
        return new TodoCommand(arguments);
    }

    /**
     * Parses a deadline command.
     */
    private Command parseDeadlineCommand(String arguments) throws InvalidCommandArgumentException {
        if (arguments.isBlank()) {
            throw new EmptyDescriptionException("deadline");
        }

        Pattern deadlinePattern = Pattern.compile("(.+)\\s+/by\\s+(.+)", Pattern.CASE_INSENSITIVE);
        Matcher deadlineMatcher = deadlinePattern.matcher(arguments);

        if (!deadlineMatcher.find()) {
            throw new InvalidCommandArgumentException(MESSAGE_INVALID_FORMAT_DEADLINE);
        }

        String name = deadlineMatcher.group(1).strip();
        String by = deadlineMatcher.group(2).strip();

        try {
            LocalDateTime byDate = LocalDateTime.parse(by);
            return new DeadlineCommand(name, byDate);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException(MESSAGE_INVALID_DATE);
        }
    }

    /**
     * Parses an event command.
     */
    private Command parseEventCommand(String arguments) throws InvalidCommandArgumentException {
        if (arguments.isBlank()) {
            throw new EmptyDescriptionException("event");
        }

        Pattern namePattern = Pattern.compile("^(.+?)\\s+/(?:from|to)", Pattern.CASE_INSENSITIVE);
        Pattern fromPattern = Pattern.compile("/from\\s+([^/]+)", Pattern.CASE_INSENSITIVE);
        Pattern toPattern = Pattern.compile("/to\\s+([^/]+)", Pattern.CASE_INSENSITIVE);

        Matcher nameMatcher = namePattern.matcher(arguments);
        Matcher fromMatcher = fromPattern.matcher(arguments);
        Matcher toMatcher = toPattern.matcher(arguments);

        boolean hasName = nameMatcher.find();
        boolean hasFrom = fromMatcher.find();
        boolean hasTo = toMatcher.find();

        if (!(hasName && hasFrom && hasTo)) {
            throw new InvalidCommandArgumentException(MESSAGE_INVALID_FORMAT_EVENT);
        }

        String name = nameMatcher.group(1).strip();
        String from = fromMatcher.group(1).strip();
        String to = toMatcher.group(1).strip();

        try {
            LocalDateTime fromDate = LocalDateTime.parse(from);
            LocalDateTime toDate = LocalDateTime.parse(to);
            return new EventCommand(name, fromDate, toDate);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException(MESSAGE_INVALID_DATE);
        }
    }
}