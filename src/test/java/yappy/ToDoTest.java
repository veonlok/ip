package yappy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import yappy.exception.InvalidTaskIndexException;

public class ToDoTest {

    @BeforeEach
    public void setUp() {
        TaskList.resetForTesting();
    }

    @Test
    public void addTask_success() {
        TaskList tasks = TaskList.getInstance();
        Todo todo = new Todo("Task 1");

        tasks.addTask(todo);

        List<? extends Task> result = tasks.getTasks();
        assertEquals(1, result.size());
        assertEquals(todo, result.get(0));
    }

    @Test
    public void removeTask_validIndex_success() throws InvalidTaskIndexException {
        TaskList tasks = TaskList.getInstance();
        Todo todo = new Todo("Task 1");
        tasks.addTask(todo);

        Task removed = tasks.removeTask(0);

        assertEquals(todo, removed);
        assertEquals(0, tasks.getSize());
    }

    @Test
    public void removeTask_invalidIndex_exceptionThrown() {
        TaskList tasks = TaskList.getInstance();

        assertThrows(InvalidTaskIndexException.class, () -> tasks.removeTask(0));
    }

    @Test
    public void setTaskCompletion_validIndex_success() throws InvalidTaskIndexException {
        TaskList tasks = TaskList.getInstance();
        Todo todo = new Todo("Task 1");
        tasks.addTask(todo);

        tasks.setTaskCompletion(0, true);

        assertTrue(tasks.getTasks().get(0).isCompleted());
    }

    @Test
    public void setTaskCompletion_invalidIndex_exceptionThrown() {
        TaskList tasks = TaskList.getInstance();

        assertThrows(InvalidTaskIndexException.class, () -> tasks.setTaskCompletion(0, true));
    }

    @Test
    public void todoToString_returnsCorrectFormat() {
        Todo todo = new Todo("Buy groceries");

        String result = todo.toString();

        assertTrue(result.startsWith("[T]"));
        assertTrue(result.contains("Buy groceries"));
    }

    @Test
    public void findTasks_matchingKeyword_returnsMatchingTasks() {
        TaskList tasks = TaskList.getInstance();
        tasks.addTask(new Todo("Buy milk"));
        tasks.addTask(new Todo("Read book"));
        tasks.addTask(new Todo("Buy bread"));

        List<Task> found = tasks.findTasks("buy");

        assertEquals(2, found.size());
    }
}
