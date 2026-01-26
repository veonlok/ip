import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import exception.InvalidTaskIndexException;

public class TaskList {

    // TODO: Make the elements of tasklist unique
    private static TaskList instance;
    private final List<Task> tasks;

    private TaskList() {
        this.tasks = new ArrayList<>();
    }

    public static synchronized TaskList getInstance() {
        if (instance == null) {
            instance = new TaskList();
        }
        return instance;
    }

    public void add(Task item) {
        this.tasks.add(item);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public Task setTaskCompletion(int taskIndex, boolean isCompleted) throws InvalidTaskIndexException {
        if (taskIndex < 0 || taskIndex >= this.tasks.size()) {
            throw new InvalidTaskIndexException(String.format("Yikesssss! You don't have that many tasks. There's only %d tasks atm", this.tasks.size()));
        } 
        
        Task task = this.tasks.get(taskIndex);
        task.setCompletion(isCompleted);
        return task;
    }

    public Task removeTask(int taskIndex) throws InvalidTaskIndexException {
        if (taskIndex < 0 || taskIndex >= this.tasks.size()) {
            throw new InvalidTaskIndexException(String.format("Yikesssss! You don't have that many tasks. There's only %d tasks atm", this.tasks.size()));
        } 

        Task task = this.tasks.remove(taskIndex);
        return task;
    }

    @Override
    public String toString() {
        if (this.tasks.isEmpty()) {
            return "Your To-Do List is empty! Time for a nap?";
        }

        return IntStream.range(0, this.tasks.size())
        .mapToObj(i -> (i + 1) + ". " + this.tasks.get(i))
        .collect(Collectors.joining("\n", "--- My To-Do List ---\n","\n"));
    }
}
