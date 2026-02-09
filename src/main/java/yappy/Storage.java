package yappy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import yappy.task.Deadline;
import yappy.task.Event;
import yappy.task.Task;
import yappy.task.Todo;

/**
 * Handles loading and saving tasks to a file.
 * Uses a simple text format for persistence.
 */
public class Storage {
    private static final String DELIMITER = " | ";
    private final String filePath;

    /**
     * Creates a Storage instance with the specified file path.
     *
     * @param filePath The path to the file for storing tasks
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file.
     * Creates the file and parent directories if they don't exist.
     *
     * @return List of tasks loaded from file, or empty list if file doesn't exist
     * @throws IOException if there's an error reading the file
     */
    public List<? extends Task> load() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        if (!file.exists()) {
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        }

        return tasks;
    }

    /**
     * Saves the list of tasks to the file.
     *
     * @param tasks The list of tasks to save
     * @throws IOException if there's an error writing to the file
     */
    public void save(List<? extends Task> tasks) throws IOException {
        File file = new File(filePath);

        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                writer.write(formatTask(task));
                writer.newLine();
            }
        }
    }

    /**
     * Parses a line from the file into a Task object.
     * Format: TYPE | COMPLETED | NAME [| additional fields...]
     *
     * @param line The line to parse
     * @return The parsed Task, or null if the line is invalid
     */
    private Task parseTask(String line) {
        String[] parts = line.split(Pattern.quote(DELIMITER));
        if (parts.length < 3) {
            return null;
        }

        String type = parts[0].trim();
        boolean isCompleted = parts[1].trim().equals("1");
        String name = parts[2].trim();

        Task task = switch (type) {
        case "T" -> new Todo(name);
        case "D" -> parts.length >= 4
                ? new Deadline(name, LocalDateTime.parse(parts[3].trim()))
                : null;
        case "E" -> parts.length >= 5
                ? new Event(name, LocalDateTime.parse(parts[3].trim()),
                        LocalDateTime.parse(parts[4].trim()))
                : null;
        default -> null;
        };

        if (task != null && isCompleted) {
            task.setCompletion(true);
        }

        return task;
    }

    /**
     * Formats a Task object into a string for file storage.
     * Format: TYPE | COMPLETED | NAME [| additional fields...]
     *
     * @param task The task to format.
     * @return The formatted string representation for storage.
     */
    private String formatTask(Task task) {
        String completed = task.isCompleted() ? "1" : "0";

        if (task instanceof Todo) {
            return String.join(DELIMITER, "T", completed, task.getName());
        } else if (task instanceof Deadline d) {
            return String.join(DELIMITER, "D", completed, d.getName(), d.getDeadlineBy().toString());
        } else if (task instanceof Event e) {
            return String.join(DELIMITER, "E", completed, e.getName(),
                    e.getStartTime().toString(), e.getEndTime().toString());
        }
        return "";
    }
}
