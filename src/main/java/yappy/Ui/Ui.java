package yappy.Ui;

import java.util.Scanner;
import java.util.function.Function;
import static yappy.Messages.MESSAGE_WELCOME;

/**
 * Handles all user interface interactions for the Yappy chatbot.
 * Provides methods for displaying messages and reading user input.
 */
public class Ui {
    private static final String INPUT_PROMPT = "You: ";
    private final Scanner scanner;

    /**
     * Nested class for formatting output messages.
     */
    private static class Formatter {
        private static final String HORIZONTAL_RULE = "_".repeat(75);
        
        static final Function<Object, String> addBorder = input -> 
                "\n" + HORIZONTAL_RULE + "\n" + input + "\n" + HORIZONTAL_RULE + "\n";
        
        static final Function<Object, String> addBottomBorder = input -> 
                input + "\n" + HORIZONTAL_RULE + "\n";
    }

    /**
     * Constructs a new Ui instance with a Scanner for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message when the application starts.
     */
    public void showWelcome() {
        System.out.println(Formatter.addBorder.apply(MESSAGE_WELCOME));
    }

    /**
     * Reads a command from the user.
     *
     * @return The user's input as a trimmed string.
     */
    public String readCommand() {
        System.out.print(INPUT_PROMPT);
        return scanner.nextLine().strip();
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMessage The error message to display.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Displays a success message with formatting.
     *
     * @param successMessage The success message to display.
     */
    public void showSuccess(String successMessage) {
        System.out.println(Formatter.addBottomBorder.apply(successMessage));
    }

    /**
     * Displays a message indicating tasks were loaded from file.
     *
     * @param message The message to display.
     */
    public void showLoadedMessage(String message) {
        System.out.println(Formatter.addBottomBorder.apply(message));
    }

    /**
     * Closes the scanner and releases resources.
     */
    public void close() {
        scanner.close();
    }
}