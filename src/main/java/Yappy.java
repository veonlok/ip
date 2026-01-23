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

    private static void startChatLoop() {
        Scanner sc = new Scanner(System.in);
        TaskList todos = TaskList.getInstance();
        Pattern markPattern = Pattern.compile("(mark|unmark)\\s+(.+)", Pattern.CASE_INSENSITIVE);

        while (true) {
            System.out.print("You: ");
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Yappy: Ohhh you're going now! Anw thanks for yapping with me");
                break;
            }

            if (input.equalsIgnoreCase("list")) {
                System.out.println(Formatter.addBottomBorder.apply("Yappy: \n" + todos));
                continue;
            }

            Matcher matcher = markPattern.matcher(input);
            if (matcher.matches()) {
                try {
                    String command = matcher.group(1).toLowerCase();
                    int taskIndex = Integer.parseInt(matcher.group(2)) - 1;

                    if (command.equalsIgnoreCase("mark")) {
                        Task task = todos.setTaskCompletion(taskIndex,  true);
                        System.out.println(Formatter.addBottomBorder.apply("Yappy: slayyy, cleared tasks? that's productivity core fr 😤✨\n" + task));
                        continue;
                    } else {
                        Task task = todos.setTaskCompletion(taskIndex, false);
                        System.out.println(Formatter.addBottomBorder.apply("Yappy: lowkey proud of you for even adding it instead of ignoring it \n" + task));
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Oops, that didn't quite look right. Try: mark <task number> or unmark <task number>");
                    continue;
                } catch (IllegalArgumentException e) {
                    System.out.println("Yappy: " + e.getMessage());
                    continue;
                }
            }
            
            todos.add(input);

            System.out.println(
                    Formatter.addBottomBorder.apply(String.format("Yappy: Got it! `%s` is in the list", input)));
        }

        sc.close();
    }

    public static void main(String[] args) {
        printWelcomeBanner();
        startChatLoop();
    }
}
