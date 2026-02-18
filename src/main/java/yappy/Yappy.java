package yappy;

import java.io.IOException;
import static yappy.Messages.MESSAGE_FILE_WRITE_ERROR;
import static yappy.Messages.MESSAGE_TASKS_LOADED;
import static yappy.Messages.MESSAGE_TASKS_LOAD_ERROR;

import yappy.task.TaskList;
import yappy.ui.Ui;
import yappy.command.Command;
import yappy.exception.YappyException;

/**
 * Main class for the Yappy chatbot application.
 * Handles user input, command parsing, and task management.
 */
public class Yappy {
    private static final String DATA_FILE_PATH = "data/tasks.txt";
    private String commandType;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private Ui ui;

    /**
     * Creates a new Yappy instance and initializes components.
     */
    public Yappy() {
        this.storage = new Storage(DATA_FILE_PATH);
        this.tasks = TaskList.getInstance();
        this.parser = new Parser();
        this.ui = new Ui();

        try {
            this.tasks.loadTasks(this.storage.load());
        } catch (IOException e) {
            // Ignore load errors for GUI mode
        }
    }

    /**
     * Runs the main chat loop.
     * Continuously reads user input, parses commands, executes them,
     * and displays results until the user exits.
     * Saves task data to storage after each command execution.
     */
    private void startChatLoop() {
        if (this.tasks.getSize() > 0) {
            ui.showLoadedMessage(String.format(MESSAGE_TASKS_LOADED, this.tasks.getSize()));
        }
        ui.showWelcome();

        while (true) {
            String input = ui.readCommand();
            String result = getResponse(input);

            if (commandType.equals("error")) {
                ui.showError(result);
            } else {
                ui.showSuccess(result);
                if (commandType.equals("exit")) {
                    ui.close();
                    return;
                }
            }
        }
    }

    public String getCommandType() {
        return commandType;
    }

    /**
     * Generates a response for the user's chat message.
     *
     * @param input The user's input command
     * @return The response message
     */
    public String getResponse(String input) {
        try {
            Command command = parser.parseCommand(input);
            String result = command.execute(tasks);
            commandType = command.getCommandWord();
            this.storage.save(tasks.getTasks());
            return result;
        } catch (IOException e) {
            commandType = "error";
            return MESSAGE_FILE_WRITE_ERROR;
        } catch (YappyException e) {
            commandType = "error";
            return e.getMessage();
        }
    }

    /**
     * Main entry point for the Yappy chatbot.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        Yappy yappy = new Yappy();
        yappy.startChatLoop();
    }
}