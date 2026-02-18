package yappy;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;
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
import yappy.command.FindCommand;
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
 *                    | find-expression
 *                    | mark-expression
 *                    | unmark-expression
 *                    | delete-expression
 *                    | todo-expression
 *                    | deadline-expression
 *                    | event-expression
 *
 * exit-expression     ::= EXIT
 * list-expression     ::= LIST
 * find-expression     ::= FIND String
 * mark-expression     ::= MARK int
 * unmark-expression   ::= UNMARK int
 * delete-expression   ::= DELETE int
 * todo-expression     ::= TODO String
 * find-expression     ::= FIND String
 * deadline-expression ::= DEADLINE String /by String
 * event-expression    ::= EVENT String /from String /to String
 * </pre>
 */
public class Parser {
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Pattern DEADLINE_FORMAT = Pattern.compile(
            "(?<description>.+)\\s+/by\\s+(?<by>.+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern EVENT_FORMAT = Pattern.compile(
            "(?<description>.+?)\\s+/from\\s+(?<from>[^/]+)\\s*/to\\s+(?<to>.+)", Pattern.CASE_INSENSITIVE);

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

        TokenType tokenType = matchTokenType(commandWord)
            .orElseThrow(() -> new UnknownCommandException(MESSAGE_UNKNOWN_COMMAND));

        return switch (tokenType) {
            case EXIT       -> parseExitCommand(arguments);
            case LIST       -> parseListCommand(arguments);
            case FIND       -> parseFindCommand(arguments);
            case DELETE     -> parseDeleteCommand(arguments);
            case MARK       -> parseMarkCommand(arguments);
            case UNMARK     -> parseUnmarkCommand(arguments);
            case TODO       -> parseTodoCommand(arguments);
            case DEADLINE   -> parseDeadlineCommand(arguments);
            case EVENT      -> parseEventCommand(arguments);
        };
    }

    /**
     * Matches a command word to its corresponding TokenType.
     *
     * @param commandWord The command word to match.
     * @return An Optional containing the matched TokenType, or empty if no match found.
     */
    private Optional<TokenType> matchTokenType(String commandWord) {
        for (TokenType tokenType : TokenType.values()) {
            if (Pattern.matches(tokenType.getPattern(), commandWord)) {
                return Optional.of(tokenType);
            }
        }
        return Optional.empty();
    }

    /**
     * Parses an exit command.
     *
     * @param arguments The arguments provided after the exit command (should be empty).
     * @return An {@link ExitCommand} to terminate the application.
     * @throws InvalidCommandArgumentException If any arguments are provided.
     */
    private Command parseExitCommand(String arguments) throws InvalidCommandArgumentException {
        if (!arguments.isBlank()) {
            throw new InvalidCommandArgumentException(MESSAGE_INVALID_ARGS_EXIT);
        }
        return new ExitCommand();
    }

    /**
     * Parses a list command.
     *
     * @param arguments The arguments provided after the list command (should be empty).
     * @return A {@link ListCommand} to display all tasks.
     * @throws InvalidCommandArgumentException If any arguments are provided.
     */
    private Command parseListCommand(String arguments) throws InvalidCommandArgumentException {
        if (!arguments.isBlank()) {
            throw new InvalidCommandArgumentException(MESSAGE_INVALID_ARGS_LIST);
        }
        return new ListCommand();
    }

    /**
     * Parses a find command.
     *
     * @param arguments The keyword to search for.
     * @return A {@link FindCommand} to search for tasks containing the keyword.
     * @throws EmptyDescriptionException If the keyword is empty or blank.
     */
    private Command parseFindCommand(String arguments) throws EmptyDescriptionException {
        if (arguments.isBlank()) {
            throw new EmptyDescriptionException("find keyword");
        }
        return new FindCommand(arguments);
    }

    /**
     * Parses a task index from the arguments string.
     *
     * @param arguments The arguments containing the task number (1-indexed).
     * @param errorMessage The error message to use if parsing fails.
     * @return The zero-based task index.
     * @throws InvalidCommandArgumentException If the argument is empty or not a valid integer.
     */
    private int parseTaskIndex(String arguments, String errorMessage) 
            throws InvalidCommandArgumentException {
        return Optional.of(arguments)
            .filter(s -> !s.isBlank())
            .map(String::strip)
            .map(s -> {
                try { 
                    return Integer.parseInt(s) - 1; 
                } catch (NumberFormatException e) { 
                    return null; 
                }
            })
            .filter(i -> i != null)
            .orElseThrow(() -> new InvalidCommandArgumentException(errorMessage));
    }

    /**
     * Parses a delete command.
     *
     * @param arguments The task number to delete (1-indexed).
     * @return A {@link DeleteCommand} to remove the specified task.
     * @throws InvalidCommandArgumentException If the argument is empty or not a valid integer.
     */
    private Command parseDeleteCommand(String arguments) throws InvalidCommandArgumentException {
        int taskIndex = parseTaskIndex(arguments, MESSAGE_INVALID_FORMAT_DELETE);
        return new DeleteCommand(taskIndex);
    }

    /**
     * Parses a mark command.
     *
     * @param arguments The task number to mark as done (1-indexed).
     * @return A {@link MarkCommand} to mark the specified task as completed.
     * @throws InvalidCommandArgumentException If the argument is empty or not a valid integer.
     */
    private Command parseMarkCommand(String arguments) throws InvalidCommandArgumentException {
        int taskIndex = parseTaskIndex(arguments, MESSAGE_INVALID_FORMAT_MARK);
        return new MarkCommand(taskIndex);
    }

    /**
     * Parses an unmark command.
     *
     * @param arguments The task number to unmark (1-indexed).
     * @return An {@link UnmarkCommand} to mark the specified task as not completed.
     * @throws InvalidCommandArgumentException If the argument is empty or not a valid integer.
     */
    private Command parseUnmarkCommand(String arguments) throws InvalidCommandArgumentException {
        int taskIndex = parseTaskIndex(arguments, MESSAGE_INVALID_FORMAT_UNMARK);
        return new UnmarkCommand(taskIndex);
    }

    /**
     * Parses a todo command.
     *
     * @param arguments The description of the todo task.
     * @return A {@link TodoCommand} to create a new todo task.
     * @throws EmptyDescriptionException If the description is empty or blank.
     */
    private Command parseTodoCommand(String arguments) throws EmptyDescriptionException {
        if (arguments.isBlank()) {
            throw new EmptyDescriptionException("todo");
        }
        return new TodoCommand(arguments);
    }

    /**
     * Parses a deadline command.
     *
     * <p>Expected format: {@code deadline <description> /by <datetime>}
     *
     * @param arguments The deadline description and due date in format "description /by datetime".
     * @return A {@link DeadlineCommand} to create a new deadline task.
     * @throws EmptyDescriptionException If the description is empty or blank.
     * @throws InvalidCommandArgumentException If the format is invalid or missing the /by clause.
     * @throws InvalidDateFormatException If the date cannot be parsed.
     */
    private Command parseDeadlineCommand(String arguments) throws InvalidCommandArgumentException {
        if (arguments.isBlank()) {
            throw new EmptyDescriptionException("deadline");
        }

        final Matcher matcher = DEADLINE_FORMAT.matcher(arguments);

        if (!matcher.matches()) {
            throw new InvalidCommandArgumentException(MESSAGE_INVALID_FORMAT_DEADLINE);
        }

        String description = matcher.group("description").strip();
        String by = matcher.group("by").strip();

        try {
            LocalDateTime byDate = LocalDateTime.parse(by);
            return new DeadlineCommand(description, byDate);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException(MESSAGE_INVALID_DATE);
        }
    }

    /**
     * Parses an event command.
     *
     * <p>Expected format: {@code event <description> /from <datetime> /to <datetime>}
     *
     * @param arguments The event description and time range in format "description /from datetime /to datetime".
     * @return An {@link EventCommand} to create a new event task.
     * @throws EmptyDescriptionException If the description is empty or blank.
     * @throws InvalidCommandArgumentException If the format is invalid or missing /from or /to clauses.
     * @throws InvalidDateFormatException If the dates cannot be parsed.
     */
    private Command parseEventCommand(String arguments) throws InvalidCommandArgumentException {
        if (arguments.isBlank()) {
            throw new EmptyDescriptionException("event");
        }

        final Matcher matcher = EVENT_FORMAT.matcher(arguments);

        if (!matcher.matches()) {
            throw new InvalidCommandArgumentException(MESSAGE_INVALID_FORMAT_EVENT);
        }

        String description = matcher.group("description").strip();
        String from = matcher.group("from").strip();
        String to = matcher.group("to").strip();

        try {
            LocalDateTime fromDate = LocalDateTime.parse(from);
            LocalDateTime toDate = LocalDateTime.parse(to);
            return new EventCommand(description, fromDate, toDate);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException(MESSAGE_INVALID_DATE);
        }
    }
}