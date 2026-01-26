import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import exception.InvalidTaskIndexException;

public class TaskList {

    // TODO: Make the elements of tasklist unique
    private static TaskList instance;
    private final List<Task> todos;

    private TaskList() {
        this.todos = new ArrayList<>();
    }

    public static synchronized TaskList getInstance() {
        if (instance == null) {
            instance = new TaskList();
        }
        return instance;
    }

    public void add(Task item) {
        this.todos.add(item);
    }

    public Task setTaskCompletion(int taskIndex, boolean is_completed) throws InvalidTaskIndexException {
        if (taskIndex < 0 || taskIndex >= this.todos.size()) {
            throw new InvalidTaskIndexException(String.format("Yikesssss! You don't have that many todos. There's only %d tasks atm", this.todos.size()));
        } 
        
        Task task = this.todos.get(taskIndex);
        task.setCompletion(is_completed);
        return task;
    }

    @Override
    public String toString() {
        if (this.todos.isEmpty()) {
            return "Your To-Do List is empty! Time for a nap?";
        }

        return IntStream.range(0, this.todos.size())
        .mapToObj(i -> (i + 1) + ". " + this.todos.get(i))
        .collect(Collectors.joining("\n", "--- My To-Do List ---\n","\n"));
    }
}
