package yappy;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static yappy.Messages.*;
import yappy.exception.InvalidTaskIndexException;


/**
 * Main class for the Yappy chatbot application.
 * Handles user input, command parsing, and task management.
 */
public class Yappy {
    private static final String DATA_FILE_PATH = "data/tasks.txt";
    
    
    private static class Formatter {
        private static final String HORIZONTAL_RULE = "\n" + "_".repeat(75) + "\n";
        static final Function<Object, String> addBorder = input -> HORIZONTAL_RULE + input + HORIZONTAL_RULE;
        static final Function<Object, String> addBottomBorder = input -> input + "\n" + HORIZONTAL_RULE;
    }

    private static void printWelcomeBanner() {
        String welcomeMessage = Formatter.addBorder.apply(WELCOME_MESSAGE);
        System.out.println(welcomeMessage);
    }

    private record Input(String command, String args) {
        static Input parse(String input) {
            String[] parts = (input + " ").split(" ", 2);
            return new Input(parts[0].toLowerCase(), parts[1].strip());
        }
    }

    private static void startChatLoop() {
        Scanner sc = new Scanner(System.in);
        Storage storage = new Storage(DATA_FILE_PATH);
        TaskList tasks = TaskList.getInstance();

        // Load tasks from file
        try {
            tasks.loadTasks(storage.load());
            if (tasks.getSize() > 0) {
                System.out.println(String.format(MESSAGE_TASKS_LOADED, tasks.getSize()));
            }
        } catch (IOException e) {
            System.out.println(MESSAGE_TASKS_LOAD_ERROR);
        }

        while (true) {
            System.out.print("You: ");
            String input = sc.nextLine().strip();
            Input parsed = Input.parse(input);
            String cmd = parsed.command();
            String arg = parsed.args();

            switch (cmd) {
                case "exit" -> {
                    if (!arg.isBlank()) {
                        System.out.println(MESSAGE_EXIT_EXTRA_ARGS);
                        continue;
                    }

                    System.out.println(MESSAGE_EXIT);
                    sc.close();
                    return;
                }
                case "list" -> {
                    if (!arg.isBlank()) {
                        System.out.println(MESSAGE_LIST_EXTRA_ARGS);
                    }
                    System.out.println(Formatter.addBottomBorder.apply("Yappy: \n" + tasks));
                }
                case "delete" -> {
                    if (arg.isBlank()) {
                        System.out.println(MESSAGE_INVALID_FORMAT_DELETE);
                        continue;
                    }

                    try {
                        int taskIndex = Integer.parseInt(arg) - 1;
                        Task task = tasks.removeTask(taskIndex);
                        storage.save(tasks.getTasks());
                        System.out.println(
                                Formatter.addBottomBorder
                                        .apply(String.format(MESSAGE_TASK_DELETED, task, tasks.getSize())));

                    } catch (NumberFormatException e) {
                        System.out.println(MESSAGE_INVALID_FORMAT_DELETE);
                    } catch (InvalidTaskIndexException e) {
                        System.out.println("Yappy: " + e.getMessage());
                    } catch (IOException e) {
                        System.out.println(MESSAGE_FILE_WRITE_ERROR);
                    }
                }

                case "mark" -> {
                    if (arg.isBlank()) {
                        System.out.println(
                                MESSAGE_INVALID_FORMAT_MARK);
                        continue;
                    }

                    try {
                        int taskIndex = Integer.parseInt(arg) - 1;
                        Task task = tasks.setTaskCompletion(taskIndex, true);
                        storage.save(tasks.getTasks());
                        System.out.println(
                                Formatter.addBottomBorder
                                        .apply(String.format(MESSAGE_TASK_MARKED, task)));
                    } catch (NumberFormatException e) {
                        System.out.println(
                                MESSAGE_INVALID_FORMAT_MARK);
                    } catch (InvalidTaskIndexException e) {
                        System.out.println("Yappy: " + e.getMessage());
                    } catch (IOException e) {
                        System.out.println(MESSAGE_FILE_WRITE_ERROR);
                    }
                }
                case "unmark" -> {
                    if (arg.isBlank()) {
                        System.out.println(
                                MESSAGE_INVALID_FORMAT_UNMARK);
                        continue;
                    }

                    try {
                        int taskIndex = Integer.parseInt(arg) - 1;
                        Task task = tasks.setTaskCompletion(taskIndex, false);
                        storage.save(tasks.getTasks());
                        System.out.println(
                                Formatter.addBottomBorder
                                        .apply(String.format(MESSAGE_TASK_UNMARKED, task)));
                    } catch (NumberFormatException e) {
                        System.out.println(
                                MESSAGE_INVALID_FORMAT_UNMARK);
                    } catch (InvalidTaskIndexException e) {
                        System.out.println("Yappy: " + e.getMessage());
                    } catch (IOException e) {
                        System.out.println(MESSAGE_FILE_WRITE_ERROR);
                    }

                }
                case "deadline" -> {
                    if (arg.isBlank()) {
                        System.out.println();
                        continue;
                    }

                    Pattern deadlinePattern = Pattern.compile("(.+)\\s+/by\\s+(.+)", Pattern.CASE_INSENSITIVE);
                    Matcher matcher = deadlinePattern.matcher(arg);

                    if (!matcher.find()) {
                        System.out.println(MESSAGE_INVALID_FORMAT_DEADLINE);
                        continue;
                    }

                    String name = matcher.group(1).strip();
                    String by = matcher.group(2).strip();

                    try {
                        LocalDateTime byDate = LocalDateTime.parse(by);
                        tasks.add(new Deadline(name, byDate));
                        storage.save(tasks.getTasks());
                        System.out.println(
                            Formatter.addBottomBorder
                                    .apply(String.format(MESSAGE_TASK_ADDED, name)));
                    } catch (DateTimeParseException e) {
                        System.out.println(MESSAGE_INVALID_DATE);
                    } catch (IOException e) {
                        System.out.println(MESSAGE_FILE_WRITE_ERROR);
                    }
                }
                case "event" -> {
                    if (arg.isBlank()) {
                        System.out.println(MESSAGE_INVALID_FORMAT_EVENT);
                        continue;
                    }

                    Pattern namePattern = Pattern.compile("^(.+?)\\s+/(?:from|to)", Pattern.CASE_INSENSITIVE);
                    Pattern fromPattern = Pattern.compile("/from\\s+([^/]+)", Pattern.CASE_INSENSITIVE);
                    Pattern toPattern = Pattern.compile("/to\\s+([^/]+)", Pattern.CASE_INSENSITIVE);

                    Matcher nameMatcher = namePattern.matcher(arg);
                    Matcher fromMatcher = fromPattern.matcher(arg);
                    Matcher toMatcher = toPattern.matcher(arg);

                    boolean hasName = nameMatcher.find();
                    boolean hasFrom = fromMatcher.find();
                    boolean hasTo = toMatcher.find();

                    if (!(hasName && hasFrom && hasTo)) {
                        System.out.println(MESSAGE_INVALID_FORMAT_EVENT);
                        continue;
                    }
                    String name = nameMatcher.group(1).strip();
                    String from = fromMatcher.group(1).strip();
                    String to = toMatcher.group(1).strip();

                    try {
                        LocalDateTime fromDate = LocalDateTime.parse(from);
                        LocalDateTime toDate = LocalDateTime.parse(to);
                        
                        if (fromDate.isAfter(toDate)) {
                            System.out.println(MESSAGE_EVENT_START_AFTER_END);
                            continue;
                        }
                        if (fromDate.isEqual(toDate)) {
                            System.out.println(MESSAGE_EVENT_ZERO_LENGTH);
                            continue;
                        }
                        
                        tasks.add(new Event(name, fromDate, toDate));
                        storage.save(tasks.getTasks());
                        System.out.println(
                                Formatter.addBottomBorder
                                        .apply(String.format(MESSAGE_TASK_ADDED, name)));
                    } catch (DateTimeParseException e) {
                        System.out.println(MESSAGE_INVALID_DATE);
                    } catch (IOException e) {
                        System.out.println(MESSAGE_FILE_WRITE_ERROR);
                    }
                }
                case "todo" -> {
                    if (arg.isBlank()) {
                        System.out.println(MESSAGE_INVALID_FORMAT_TODO);
                        continue;
                    }

                    tasks.add(new Todo(arg));
                    try {
                        storage.save(tasks.getTasks());
                    } catch (IOException e) {
                        System.out.println(MESSAGE_FILE_WRITE_ERROR);
                    }
                    System.out.println(
                            Formatter.addBottomBorder
                                    .apply(String.format(MESSAGE_TASK_ADDED, arg)));

                }
                default -> {
                    System.out.println(Formatter.addBottomBorder.apply(MESSAGE_UNKNOWN_COMMAND));
                }
            }
        }
    }

    public static void main(String[] args) {
        printWelcomeBanner();
        startChatLoop();
    }
}