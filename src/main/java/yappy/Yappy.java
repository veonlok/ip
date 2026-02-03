package yappy;

import java.io.IOException;
import java.util.Scanner;
import java.util.function.Function;
import static yappy.Messages.MESSAGE_FILE_WRITE_ERROR;
import static yappy.Messages.MESSAGE_TASKS_LOADED;
import static yappy.Messages.MESSAGE_TASKS_LOAD_ERROR;
import static yappy.Messages.MESSAGE_WELCOME;
import yappy.command.Command;
import yappy.exception.YappyException;

/**
 * Main class for the Yappy chatbot application.
 * Handles user input, command parsing, and task management.
 */
public class Yappy {
    private static final String DATA_FILE_PATH = "data/tasks.txt";
    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    
    private static class Formatter {
        private static final String HORIZONTAL_RULE = "\n" + "_".repeat(75) + "\n";
        static final Function<Object, String> addBorder = input -> HORIZONTAL_RULE + input + HORIZONTAL_RULE;
        static final Function<Object, String> addBottomBorder = input -> input + "\n" + HORIZONTAL_RULE;
    }

    /**
     * Initializes the chatbot by loading storage and tasks.
     * Sets up the storage, task list, and parser components.
     * Loads previously saved tasks from the data file if available.
     */
    private void start() {
        this.storage = new Storage(DATA_FILE_PATH);
        this.tasks = TaskList.getInstance();
        this.parser = new Parser();

        try {
            this.tasks.loadTasks(this.storage.load());
            if (this.tasks.getSize() > 0) {
                System.out.println(String.format(MESSAGE_TASKS_LOADED, this.tasks.getSize()));
            }
        } catch (IOException e) {
            System.out.println(MESSAGE_TASKS_LOAD_ERROR);
        }
    }

    /**
     * Runs the main chat loop.
     * Continuously reads user input, parses commands, executes them,
     * and displays results until the user exits.
     * Saves task data to storage after each command execution.
     */
    private void startChatLoop() {
        Scanner sc = new Scanner(System.in);
        this.start();

        while (true) {
            System.out.print("You: ");
            String input = sc.nextLine().strip();

            try {
                Command command = parser.parseCommand(input);
                String result = command.execute(tasks);
                System.out.println(Formatter.addBottomBorder.apply(result));

                // Save after any command that might modify tasks
                try {
                    this.storage.save(tasks.getTasks());
                } catch (IOException e) {
                    System.out.println(MESSAGE_FILE_WRITE_ERROR);
                }

                if (command.isExit()) {
                    sc.close();
                    return;
                }
            } catch (YappyException e) {
                System.out.println(Formatter.addBottomBorder.apply(e.getMessage()));
            }
        }
    }

    /**
     * Main entry point for the Yappy chatbot.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        Yappy yappy = new Yappy();
        System.out.println(Formatter.addBorder.apply(MESSAGE_WELCOME));
        yappy.startChatLoop();
    }
}