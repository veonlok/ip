package yappy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import yappy.exception.InvalidTaskIndexException;

/**
 * Manages a collection of tasks using the singleton pattern.
 * Provides methods to add, remove, and manipulate tasks.
 */
public class TaskList {

    // TODO: Make the elements of tasklist unique
    private static TaskList instance;
    private final List<Task> TASKS;

    private TaskList() {
        this.TASKS = new ArrayList<>();
    }

    public static synchronized TaskList getInstance() {
        if (instance == null) {
            instance = new TaskList();
        }
        return instance;
    }

    public void add(Task item) {
        this.TASKS.add(item);
    }

    public int getSize() {
        return this.TASKS.size();
    }

    public Task setTaskCompletion(int taskIndex, boolean isCompleted) throws InvalidTaskIndexException {
        if (taskIndex < 0 || taskIndex >= this.TASKS.size()) {
            throw new InvalidTaskIndexException(String.format("Yikesssss! You don't have that many tasks. There's only %d tasks atm", this.TASKS.size()));
        } 
        
        Task task = this.TASKS.get(taskIndex);
        task.setCompletion(isCompleted);
        return task;
    }

    public Task removeTask(int taskIndex) throws InvalidTaskIndexException {
        if (taskIndex < 0 || taskIndex >= this.TASKS.size()) {
            throw new InvalidTaskIndexException(String.format("Yikesssss! You don't have that many tasks. There's only %d tasks atm", this.TASKS.size()));
        } 

        Task task = this.TASKS.remove(taskIndex);
        return task;
    }

    @Override
    public String toString() {
        if (this.TASKS.isEmpty()) {
            return "Your To-Do List is empty! Time for a nap?";
        }

        return IntStream.range(0, this.TASKS.size())
        .mapToObj(i -> (i + 1) + ". " + this.TASKS.get(i))
        .collect(Collectors.joining("\n", "--- My To-Do List ---\n","\n"));
    }
}
