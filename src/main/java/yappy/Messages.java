package yappy;

/**
 * Container for user visible messages.
 */
public class Messages { 
    private static final String LOGO = "$$\\     $$\\  $$$$$$\\  $$$$$$$\\  $$$$$$$\\ $$\\     $$\\ \n" +
                                       "\\$$\\   $$  |$$  __$$\\ $$  __$$\\ $$  __$$\\\\$$\\   $$  |\n" +
                                       " \\$$\\ $$  / $$ /  $$ |$$ |  $$ |$$ |  $$ |\\$$\\ $$  / \n" +
                                       "  \\$$$$  /  $$$$$$$$ |$$$$$$$  |$$$$$$$  | \\$$$$  /  \n" +
                                       "   \\$$  /   $$  __$$ |$$  ____/ $$  ____/   \\$$  /   \n" +
                                       "    $$ |    $$ |  $$ |$$ |      $$ |         $$ |    \n" +
                                       "    $$ |    $$ |  $$ |$$ |      $$ |         $$ |    \n" +
                                       "    \\__|    \\__|  \\__|\\__|      \\__|         \\__|    \n";
    public static final String WELCOME_MESSAGE = "Wadduppppp!! The name's \n\n" + LOGO + "\nCome over, yap with me!";
    public static final String MESSAGE_UNKNOWN_COMMAND = "Yappy: Hey buddy! Appreciate the enthusiasm but I don't recognise this input command ;(";
    public static final String MESSAGE_FILE_WRITE_ERROR = "Yappy: Couldn't save tasks to file!";
    public static final String MESSAGE_INVALID_DATE = "Yappy: I don't recognise this date!!! (YYYY-MM-DDTHH:MM) pleaseeeee";
    
    public static final String MESSAGE_INVALID_FORMAT_PREFIX = "Yappy: Oops! Format should be: ";
    public static final String MESSAGE_INVALID_FORMAT_DEADLINE = MESSAGE_INVALID_FORMAT_PREFIX + "deadline <name> /by <date>";
    public static final String MESSAGE_INVALID_FORMAT_DELETE = MESSAGE_INVALID_FORMAT_PREFIX + "delete <task number>";
    public static final String MESSAGE_INVALID_FORMAT_MARK = MESSAGE_INVALID_FORMAT_PREFIX + "mark <task number>";
    public static final String MESSAGE_INVALID_FORMAT_UNMARK = MESSAGE_INVALID_FORMAT_PREFIX + "unmark <task number>";
    public static final String MESSAGE_INVALID_FORMAT_EVENT = MESSAGE_INVALID_FORMAT_PREFIX + "event <name> /from <start> /to <end>";
    public static final String MESSAGE_INVALID_FORMAT_TODO = MESSAGE_INVALID_FORMAT_PREFIX + "todo <name>";

    // Task loaded/error messages
    public static final String MESSAGE_TASKS_LOADED = "Yappy: Loaded %d tasks from file!";
    public static final String MESSAGE_TASKS_LOAD_ERROR = "Yappy: Couldn't load tasks from file, starting fresh!";

    // Command response messages
    public static final String MESSAGE_EXIT = "Yappy: Ohhh you're going now! Anw thanks for yapping with me";
    public static final String MESSAGE_EXIT_EXTRA_ARGS = "Yappy: Bruhhhh! Just type 'exit' to leave!";
    public static final String MESSAGE_LIST_EXTRA_ARGS = "Yappy: Just type 'list' to list the todo list!";
    public static final String MESSAGE_TASK_ADDED = "Yappy: Got it! `%s` is in the list";
    public static final String MESSAGE_TASK_DELETED = "Yappy: sheeeesh, task deleted? that's main character productivity energy fr\n%s\nNow you've got %d tasks vibin' in the list.";
    public static final String MESSAGE_TASK_MARKED = "Yappy: slayyy, cleared tasks? that's productivity core fr\n%s";
    public static final String MESSAGE_TASK_UNMARKED = "Yappy: lowkey proud of you for even adding it instead of ignoring it \n%s";

    // Event validation messages
    public static final String MESSAGE_EVENT_START_AFTER_END = "Yappy: Ummm the start time can't be after the end time bud!";
    public static final String MESSAGE_EVENT_ZERO_LENGTH = "Yappy: The start and end time are the same... that's a zero-length event!";
}