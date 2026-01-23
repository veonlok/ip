import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ToDoList {
    private static ToDoList instance;
    private final List<String> todos;

    private ToDoList() {
        todos = new ArrayList<>();
    }

    public static synchronized ToDoList getInstance() {
        if (instance == null) {
            instance = new ToDoList();
        }
        return instance;
    }

    public void add(String item) {
        todos.add(item);
    }

    @Override
    public String toString() {
        if (todos.isEmpty()) {
            return "Your To-Do List is empty! Time for a nap?";
        }

        return IntStream.range(0, todos.size())
        .mapToObj(i -> (i + 1) + ". " + todos.get(i))
        .collect(Collectors.joining("\n", "--- My To-Do List ---\n","\n"));
    }
}
