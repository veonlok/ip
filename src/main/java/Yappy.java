import java.util.Scanner;
import java.util.function.Function;

public class Yappy {
    private static final String HORIZONTAL_RULE = "\n" + "_".repeat(75) + "\n";
    private static final String LOGO =
            "$$\\     $$\\  $$$$$$\\  $$$$$$$\\  $$$$$$$\\ $$\\     $$\\ \n" +
            "\\$$\\   $$  |$$  __$$\\ $$  __$$\\ $$  __$$\\\\$$\\   $$  |\n" +
            " \\$$\\ $$  / $$ /  $$ |$$ |  $$ |$$ |  $$ |\\$$\\ $$  / \n" +
            "  \\$$$$  /  $$$$$$$$ |$$$$$$$  |$$$$$$$  | \\$$$$  /  \n" +
            "   \\$$  /   $$  __$$ |$$  ____/ $$  ____/   \\$$  /   \n" +
            "    $$ |    $$ |  $$ |$$ |      $$ |         $$ |    \n" +
            "    $$ |    $$ |  $$ |$$ |      $$ |         $$ |    \n" +
            "    \\__|    \\__|  \\__|\\__|      \\__|         \\__|    \n";

    private static class Formatter {
        static final Function<Object, String> addBorder =
                input -> HORIZONTAL_RULE + input + HORIZONTAL_RULE;

        static final Function<Object, String> addBottomBorder =
                input -> input + "\n" + HORIZONTAL_RULE;
    }

    private static void printWelcomeBanner() {
        String welcomeMessage =
                Formatter.addBorder.apply(
                        "Wadduppppp!! The name's \n\n" + LOGO + "\nCome over, yap to me!"
                );
        System.out.println(welcomeMessage);
    }

    private static void startChatLoop() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("You: ");
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Yappy: Ohhh you're going now! Anw thanks for yapping with me");
                break;
            }

            System.out.println(
                Formatter.addBottomBorder.apply("Yappy: " + input)
            );
        }

        sc.close();
    }

    // ====== Chat UI ======
    public static void main(String[] args) {
        printWelcomeBanner();
        startChatLoop();
    }
}
