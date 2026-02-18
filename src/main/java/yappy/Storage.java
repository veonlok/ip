package yappy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.Collectors;

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

        try (Stream<String> lines = Files.lines(file.toPath())) {
            return lines
                .map(this::parseTask)
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
        }
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

        String fileContent = tasks.stream()
            .map(this::formatTask)
            .collect(Collectors.joining(System.lineSeparator()));
        Files.writeString(file.toPath(), fileContent);
    }

    /**
     * Parses a line from the file into a Task object.
     * Format: TYPE | COMPLETED | DESCRIPTION [| additional fields...]
     *
     * @param line The line to parse
     * @return The parsed Task, or null if the line is invalid
     */
    private Optional<Task> parseTask(String line) {
        String[] parts = line.split(Pattern.quote(DELIMITER));
        if (parts.length < 3) {
            return Optional.empty();
        }

        String type = parts[0].trim();
        boolean isCompleted = parts[1].trim().equals("1");
        String description = parts[2].trim();

        Task task = switch (type) {
            case "T" -> new Todo(description);
            case "D" -> parts.length >= 4
                    ? new Deadline(description, LocalDateTime.parse(parts[3].trim()))
                    : null;
            case "E" -> parts.length >= 5
                    ? new Event(description, LocalDateTime.parse(parts[3].trim()),
                            LocalDateTime.parse(parts[4].trim()))
                    : null;
            default -> null;
        };

        if (task != null && isCompleted) {
            task.setCompletion(true);
        }

        return Optional.ofNullable(task);
    }

    /**
     * Formats a Task object into a string for file storage.
     * Format: TYPE | COMPLETED | DESCRIPTION [| additional fields...]
     *
     * @param task The task to format.
     * @return The formatted string representation for storage.
     */
    private String formatTask(Task task) {
        String completed = task.isCompleted() ? "1" : "0";

        if (task instanceof Todo) {
            return String.join(DELIMITER, "T", completed, task.getDescription());
        } else if (task instanceof Deadline d) {
            return String.join(DELIMITER, "D", completed, d.getDescription(), d.getDeadlineBy().toString());
        } else if (task instanceof Event e) {
            return String.join(DELIMITER, "E", completed, e.getName(),
                    e.getStartTime().toString(), e.getEndTime().toString());
        }
        return "";
    }
}
