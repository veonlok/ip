package yappy.command;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import yappy.exception.YappyException;
import yappy.task.Task;
import yappy.task.TaskList;

/**
 * Finds tasks containing a keyword in their description.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Creates a FindCommand to search for tasks containing the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        super("find");
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching for tasks containing the keyword.
     *
     * @param tasks The task list to search.
     * @return A formatted string of matching tasks, or a message if none found.
     */
    @Override
    public String execute(TaskList tasks) throws YappyException {
        List<Task> matchingTasks = tasks.findTasks(keyword);

        if (matchingTasks.isEmpty()) {
            return "Yappy: No tasks found matching '" + keyword + "' :(";
        }

        String resultList = IntStream.range(0, matchingTasks.size())
                .mapToObj(i -> String.valueOf((i + 1) + ". " + matchingTasks.get(i)))
                .collect(Collectors.joining("\n"));

        return "Yappy: Here are the matching tasks in your list:\n" + resultList;
    }
}
