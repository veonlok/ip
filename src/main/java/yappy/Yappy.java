package yappy;

import java.io.IOException;
import static yappy.Messages.MESSAGE_FILE_WRITE_ERROR;
import static yappy.Messages.MESSAGE_TASKS_LOADED;
import static yappy.Messages.MESSAGE_TASKS_LOAD_ERROR;

import yappy.Task.TaskList;
import yappy.Ui.Ui;
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
    private Ui ui;

    /**
     * Initializes the chatbot by loading storage and tasks.
     * Sets up the storage, task list, parser, and UI components.
     * Loads previously saved tasks from the data file if available.
     */
    private void start() {
        this.storage = new Storage(DATA_FILE_PATH);
        this.tasks = TaskList.getInstance();
        this.parser = new Parser();
        this.ui = new Ui();

        try {
            this.tasks.loadTasks(this.storage.load());
            if (this.tasks.getSize() > 0) {
                ui.showLoadedMessage(String.format(MESSAGE_TASKS_LOADED, this.tasks.getSize()));
            }
        } catch (IOException e) {
            ui.showError(MESSAGE_TASKS_LOAD_ERROR);
        }
    }

    /**
     * Runs the main chat loop.
     * Continuously reads user input, parses commands, executes them,
     * and displays results until the user exits.
     * Saves task data to storage after each command execution.
     */
    private void startChatLoop() {
        this.start();
        ui.showWelcome();

        while (true) {
            String input = ui.readCommand();

            try {
                Command command = parser.parseCommand(input);
                String result = command.execute(tasks);
                this.storage.save(tasks.getTasks());
                ui.showSuccess(result);

                if (command.isExit()) {
                    ui.close();
                    return;
                }
            } catch (IOException e) {
                ui.showError(MESSAGE_FILE_WRITE_ERROR);
            } catch (YappyException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * 
     * @param args
     */
    public String getResponse(String input) {
        return "Yappy heard: " + input;
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