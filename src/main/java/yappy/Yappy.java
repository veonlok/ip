package yappy;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import yappy.exception.InvalidTaskIndexException;


/**
 * Main class for the Yappy chatbot application.
 * Handles user input, command parsing, and task management.
 */
public class Yappy {
    private static final String DATA_FILE_PATH = "data/tasks.txt";
    private static final String HORIZONTAL_RULE = "\n" + "_".repeat(75) + "\n";
    private static final String LOGO = "$$\\     $$\\  $$$$$$\\  $$$$$$$\\  $$$$$$$\\ $$\\     $$\\ \n" +
                                       "\\$$\\   $$  |$$  __$$\\ $$  __$$\\ $$  __$$\\\\$$\\   $$  |\n" +
                                       " \\$$\\ $$  / $$ /  $$ |$$ |  $$ |$$ |  $$ |\\$$\\ $$  / \n" +
                                       "  \\$$$$  /  $$$$$$$$ |$$$$$$$  |$$$$$$$  | \\$$$$  /  \n" +
                                       "   \\$$  /   $$  __$$ |$$  ____/ $$  ____/   \\$$  /   \n" +
                                       "    $$ |    $$ |  $$ |$$ |      $$ |         $$ |    \n" +
                                       "    $$ |    $$ |  $$ |$$ |      $$ |         $$ |    \n" +
                                       "    \\__|    \\__|  \\__|\\__|      \\__|         \\__|    \n";

    private static class Formatter {
        static final Function<Object, String> addBorder = input -> HORIZONTAL_RULE + input + HORIZONTAL_RULE;
        static final Function<Object, String> addBottomBorder = input -> input + "\n" + HORIZONTAL_RULE;
    }

    private static void printWelcomeBanner() {
        String welcomeMessage = Formatter.addBorder.apply(
                "Wadduppppp!! The name's \n\n" + LOGO + "\nCome over, yap with me!");
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
                System.out.println("Yappy: Loaded " + tasks.getSize() + " tasks from file!");
            }
        } catch (IOException e) {
            System.out.println("Yappy: Couldn't load tasks from file, starting fresh!");
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
                        System.out.println("Yappy: Bruhhhh! Just type 'exit' to leave!");
                        continue;
                    }

                    System.out.println("Yappy: Ohhh you're going now! Anw thanks for yapping with me");
                    sc.close();
                    return;
                }
                case "list" -> {
                    if (!arg.isBlank()) {
                        System.out.println("Yappy: Just type 'list' to list the todo list!");
                    }
                    System.out.println(Formatter.addBottomBorder.apply("Yappy: \n" + tasks));
                }
                case "delete" -> {
                    if (arg.isBlank()) {
                        System.out.println("Yappy: Oops, that didn't quite look right. Try: delete <task number>");
                        continue;
                    }

                    try {
                        int taskIndex = Integer.parseInt(arg) - 1;
                        Task task = tasks.removeTask(taskIndex);
                        storage.save(tasks.getTasks());
                        System.out.println(
                                Formatter.addBottomBorder
                                        .apply(String.format(
                                                "Yappy: sheeeesh, task deleted? that's main character productivity energy fr\n%s\nNow you've got %d tasks vibin' in the list.",
                                                task, tasks.getSize())));

                    } catch (NumberFormatException e) {
                        System.out.println(
                                "Yappy: Oops, that didn't quite look right. Try: delete <task number>");
                    } catch (InvalidTaskIndexException e) {
                        System.out.println("Yappy: " + e.getMessage());
                    } catch (IOException e) {
                        System.out.println("Yappy: Couldn't save tasks to file!");
                    }
                }

                case "mark" -> {
                    if (arg.isBlank()) {
                        System.out.println(
                                "Yappy: Oops, that didn't quite look right. Try: mark <task number>");
                        continue;
                    }

                    try {
                        int taskIndex = Integer.parseInt(arg) - 1;
                        Task task = tasks.setTaskCompletion(taskIndex, true);
                        storage.save(tasks.getTasks());
                        System.out.println(
                                Formatter.addBottomBorder
                                        .apply("Yappy: slayyy, cleared tasks? that's productivity core fr\n"
                                                + task));
                    } catch (NumberFormatException e) {
                        System.out.println(
                                "Yappy: Oops, that didn't quite look right. Try: mark <task number>");
                    } catch (InvalidTaskIndexException e) {
                        System.out.println("Yappy: " + e.getMessage());
                    } catch (IOException e) {
                        System.out.println("Yappy: Couldn't save tasks to file!");
                    }
                }
                case "unmark" -> {
                    if (arg.isBlank()) {
                        System.out.println(
                                "Yappy: Oops, that didn't quite look right. Try: unmark <task number>");
                        continue;
                    }

                    try {
                        int taskIndex = Integer.parseInt(arg) - 1;
                        Task task = tasks.setTaskCompletion(taskIndex, false);
                        storage.save(tasks.getTasks());
                        System.out.println(
                                Formatter.addBottomBorder.apply(
                                        "Yappy: lowkey proud of you for even adding it instead of ignoring it \n"
                                                + task));
                    } catch (NumberFormatException e) {
                        System.out.println(
                                "Yappy: Oops, that didn't quite look right. Try: unmark <task number>");
                    } catch (InvalidTaskIndexException e) {
                        System.out.println("Yappy: " + e.getMessage());
                    } catch (IOException e) {
                        System.out.println("Yappy: Couldn't save tasks to file!");
                    }

                }
                case "deadline" -> {
                    if (arg.isBlank()) {
                        System.out.println("Yappy: Oops! Format should be: deadline <name> /by <date>");
                        continue;
                    }

                    Pattern deadlinePattern = Pattern.compile("(.+)\\s+/by\\s+(.+)", Pattern.CASE_INSENSITIVE);
                    Matcher matcher = deadlinePattern.matcher(arg);

                    if (!matcher.find()) {
                        System.out.println("Yappy: Oops! Format should be: deadline <name> /by <date>");
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
                                    .apply(String.format("Yappy: Got it! `%s` is in the list", name)));
                    } catch (DateTimeParseException e) {
                        System.out.println("Yappy: I don't recognise this date!!! (YYYY-MM-DDTHH:MM) pleaseeeee");
                    } catch (IOException e) {
                        System.out.println("Yappy: Couldn't save tasks to file!");
                    }
                }
                case "event" -> {
                    if (arg.isBlank()) {
                        System.out.println("Yappy: Oops! Format should be: event <name> /from <start> /to <end>");
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
                        System.out.println("Yappy: Oops! Format should be: event <name> /from <start> /to <end>");
                        continue;
                    }
                    String name = nameMatcher.group(1).strip();
                    String from = fromMatcher.group(1).strip();
                    String to = toMatcher.group(1).strip();

                    try {
                        LocalDateTime fromDate = LocalDateTime.parse(from);
                        LocalDateTime toDate = LocalDateTime.parse(to);
                        
                        if (fromDate.isAfter(toDate)) {
                            System.out.println("Yappy: Ummm the start time can't be after the end time bestie!");
                            continue;
                        }
                        if (fromDate.isEqual(toDate)) {
                            System.out.println("Yappy: The start and end time are the same... that's a zero-length event!");
                            continue;
                        }
                        
                        tasks.add(new Event(name, fromDate, toDate));
                        storage.save(tasks.getTasks());
                        System.out.println(
                                Formatter.addBottomBorder
                                        .apply(String.format("Yappy: Got it! `%s` is in the list", name)));
                    } catch (DateTimeParseException e) {
                        System.out.println("Yappy: I don't recognise this date!!! (YYYY-MM-DDTHH:MM) pleaseeeee");
                    } catch (IOException e) {
                        System.out.println("Yappy: Couldn't save tasks to file!");
                    }
                }
                case "todo" -> {
                    if (arg.isBlank()) {
                        System.out.println("Yappy: Oops! Format should be: todo <name>");
                        continue;
                    }

                    tasks.add(new Todo(arg));
                    try {
                        storage.save(tasks.getTasks());
                    } catch (IOException e) {
                        System.out.println("Yappy: Couldn't save tasks to file!");
                    }
                    System.out.println(
                            Formatter.addBottomBorder
                                    .apply(String.format("Yappy: Got it! `%s` is in the list", input)));

                }
                default -> {
                    System.out.println(
                            Formatter.addBottomBorder.apply(
                                    "Yappy: Hey buddy! Appreciate the enthusiasm but I don't recognise this input command ;("));
                }
            }
        }
    }

    public static void main(String[] args) {
        printWelcomeBanner();
        startChatLoop();
    }
}