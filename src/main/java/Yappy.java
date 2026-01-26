import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Yappy {
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
        TaskList tasks = TaskList.getInstance();

        while (true) {
            System.out.print("You: ");
            String input = sc.nextLine().strip();
            Input parsed = Input.parse(input);
            String cmd = parsed.command();
            String arg = parsed.args();

            switch (cmd) {
                case "exit" -> {
                    if (arg.isBlank()) {
                        System.out.println("Yappy: Ohhh you're going now! Anw thanks for yapping with me");
                        sc.close();
                        return;
                    }
                }
                case "list" -> {
                    if (arg.isBlank()) {
                        System.out.println(Formatter.addBottomBorder.apply("Yappy: \n" + tasks));
                    }
                }
                case "mark" -> {
                    if (!arg.isBlank()) {
                        try {
                            int taskIndex = Integer.parseInt(arg) - 1;
                            Task task = tasks.setTaskCompletion(taskIndex, true);
                            System.out.println(
                                    Formatter.addBottomBorder
                                            .apply("Yappy: slayyy, cleared tasks? that's productivity core fr 😤✨\n"
                                                    + task));
                        } catch (NumberFormatException e) {
                            System.out.println(
                                    "Oops, that didn't quite look right. Try: mark <task number> or unmark <task number>");
                        } catch (IllegalArgumentException e) {
                            System.out.println("Yappy: " + e.getMessage());
                        }
                    }
                }
                case "unmark" -> {
                    if (!arg.isBlank()) {
                        try {
                            int taskIndex = Integer.parseInt(arg) - 1;
                            Task task = tasks.setTaskCompletion(taskIndex, false);
                            System.out.println(
                                    Formatter.addBottomBorder.apply(
                                            "Yappy: lowkey proud of you for even adding it instead of ignoring it \n"
                                                    + task));
                        } catch (NumberFormatException e) {
                            System.out.println(
                                    "Oops, that didn't quite look right. Try: mark <task number> or unmark <task number>");
                        } catch (IllegalArgumentException e) {
                            System.out.println("Yappy: " + e.getMessage());
                        }
                    }
                }
                case "deadline" -> {
                    if (!arg.isBlank()) {
                        Pattern deadlinePattern = Pattern.compile("(.+)\\s+/by\\s+(.+)", Pattern.CASE_INSENSITIVE);
                        Matcher matcher = deadlinePattern.matcher(arg);

                        if (matcher.find()) {
                            String name = matcher.group(1).strip();
                            String by = matcher.group(2).strip();
                            tasks.add(new Deadline(name, by));
                            System.out.println(
                                    Formatter.addBottomBorder
                                            .apply(String.format("Yappy: Got it! `%s` is in the list", name)));
                        } else {
                            System.out.println("Yappy: Oops! Format should be: deadline <name> /by <date>");
                        }

                    }
                }
                case "event" -> {
                    if (!arg.isBlank()) {
                        Pattern namePattern = Pattern.compile("^(.+?)\\s+/(?:from|to)", Pattern.CASE_INSENSITIVE);
                        Pattern fromPattern = Pattern.compile("/from\\s+([^/]+)", Pattern.CASE_INSENSITIVE);
                        Pattern toPattern = Pattern.compile("/to\\s+([^/]+)", Pattern.CASE_INSENSITIVE);

                        Matcher nameMatcher = namePattern.matcher(arg);
                        Matcher fromMatcher = fromPattern.matcher(arg);
                        Matcher toMatcher = toPattern.matcher(arg);

                        boolean hasName = nameMatcher.find();
                        boolean hasFrom = fromMatcher.find();
                        boolean hasTo = toMatcher.find();

                        if (hasName && hasFrom && hasTo) {
                            String name = nameMatcher.group(1).strip();
                            String from = fromMatcher.group(1).strip();
                            String to = toMatcher.group(1).strip();

                            tasks.add(new Event(name, from, to));
                            System.out.println(
                                    Formatter.addBottomBorder
                                            .apply(String.format("Yappy: Got it! `%s` is in the list", name)));
                        } else {
                            System.out.println("Yappy: Oops! Format should be: event <name> /from <start> /to <end>");
                        }
                    }
                }
                case "todo" -> {
                    if (!arg.isBlank()) {
                        tasks.add(new Todo(arg));
                        System.out.println(
                                Formatter.addBottomBorder
                                        .apply(String.format("Yappy: Got it! `%s` is in the list", input)));
                    }
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
