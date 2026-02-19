# Yappy User Guide

Yappy is a **task management chatbot** that helps you keep track of your todos, deadlines, and events through a simple GUI interface.

![Yappy Screenshot](Ui.png)

## Quick Start

1. Ensure you have Java 17 or above installed.
2. Download the latest `yappy.jar` from the releases page.
3. Run `java -jar yappy.jar` to start the application.
4. Type commands in the text box and press Enter or click the Send button.

## Features

### Listing all tasks: `list`

Shows all tasks in your task list.

Format: `list`

```
--- My To-Do List ---
1. [T][ ] read book
2. [D][ ] submit report (by: Dec 10 2024, 5:00 PM)
3. [E][ ] team meeting (from: Dec 10 2024, 2:00 PM to: Dec 10 2024, 3:00 PM)
```

### Adding a todo: `todo`

Adds a simple todo task without any date/time attached.

Format: `todo <description>`

Example: `todo read book`

```
Got it! `read book` is in the list
```

### Adding a deadline: `deadline`

Adds a task with a deadline.

Format: `deadline <description> /by <date>`

- Date format: `YYYY-MM-DDTHH:MM` (e.g., `2024-12-10T17:00`)

Example: `deadline submit report /by 2024-12-10T17:00`

```
Got it! `submit report` is in the list
```

### Adding an event: `event`

Adds an event with a start and end time.

Format: `event <description> /from <start> /to <end>`

- Date format: `YYYY-MM-DDTHH:MM`

Example: `event team meeting /from 2024-12-10T14:00 /to 2024-12-10T15:00`

```
Got it! `team meeting` is in the list
```

### Marking a task as done: `mark`

Marks a task as completed.

Format: `mark <task number>`

Example: `mark 1`

```
slayyy, cleared tasks? that's productivity core fr
[T][X] read book
```

### Unmarking a task: `unmark`

Marks a task as not done.

Format: `unmark <task number>`

Example: `unmark 1`

```
lowkey proud of you for even adding it instead of ignoring it
[T][ ] read book
```

### Deleting a task: `delete`

Removes a task from the list.

Format: `delete <task number>`

Example: `delete 1`

```
sheeeesh, task deleted? that's main character productivity energy fr
[T][ ] read book
Now you've got 2 tasks vibin' in the list.
```

### Finding tasks: `find`

Searches for tasks containing the given keyword.

Format: `find <keyword>`

Example: `find book`

```
Here are the matching tasks in your list:
1. [T][ ] read book
```

### Exiting the application: `exit`

Closes Yappy.

Format: `exit`

```
Ohhh you're going now! Anw thanks for yapping with me
```

## Task Types

| Symbol  | Type     | Description                  |
| ------- | -------- | ---------------------------- |
| `[T]` | Todo     | Simple task without date     |
| `[D]` | Deadline | Task with a due date         |
| `[E]` | Event    | Task with start and end time |

## Task Status

| Symbol  | Status    |
| ------- | --------- |
| `[X]` | Completed |
| `[ ]` | Not done  |

## Data Storage

Tasks are automatically saved to `data/tasks.txt` and loaded when you restart Yappy. No manual saving required!

## Command Summary

| Command  | Format                                          |
| -------- | ----------------------------------------------- |
| List     | `list`                                        |
| Todo     | `todo <description>`                          |
| Deadline | `deadline <description> /by <date>`           |
| Event    | `event <description> /from <start> /to <end>` |
| Mark     | `mark <task number>`                          |
| Unmark   | `unmark <task number>`                        |
| Delete   | `delete <task number>`                        |
| Find     | `find <keyword>`                              |
| Exit     | `exit`                                        |
