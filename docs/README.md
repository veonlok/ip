<h1 align="center">
  <br>
  <img src="Ui.png" alt="Yappy" width="400">
  <br>
  🗣️ Yappy
  <br>
</h1>

<h4 align="center">Your friendly task management chatbot that loves to yap! Built with <a href="https://openjfx.io/">JavaFX</a>.</h4>

<p align="center">
  <img src="https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 17+">
  <img src="https://img.shields.io/badge/JavaFX-17-3776AB?style=for-the-badge" alt="JavaFX 17">
  <img src="https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white" alt="Gradle">
  <img src="https://img.shields.io/badge/License-MIT-green?style=for-the-badge" alt="License">
</p>

<p align="center">
  <a href="#-quick-start"><img src="https://img.shields.io/badge/🚀_Quick_Start-4CAF50?style=flat-square" alt="Quick Start"></a>
  <a href="#-features"><img src="https://img.shields.io/badge/✨_Features-9C27B0?style=flat-square" alt="Features"></a>
  <a href="#-command-summary"><img src="https://img.shields.io/badge/📖_Commands-2196F3?style=flat-square" alt="Commands"></a>
  <a href="#-task-reference"><img src="https://img.shields.io/badge/📚_Reference-FF9800?style=flat-square" alt="Reference"></a>
</p>

---

## 🚀 Quick Start

<table>
<tr>
<td>

> 💡 **Prerequisites:** Java 17 or above installed on your system

```bash
# Download and run
java -jar yappy.jar
```

| Step | Action |
|:----:|:-------|
| 1️⃣ | 📥 Download the latest `yappy.jar` from releases |
| 2️⃣ | 💻 Open a terminal in the download folder |
| 3️⃣ | ▶️ Run `java -jar yappy.jar` |
| 4️⃣ | 💬 Start yapping with Yappy! |

</td>
</tr>
</table>

---

## ✨ Features

<details>
<summary>📋 <b>List all tasks</b> — <img src="https://img.shields.io/badge/list-4CAF50?style=flat-square" alt="list"></summary>

<br>

Shows all tasks in your task list.

> **Format:** ![list](https://img.shields.io/badge/list-4CAF50?style=flat-square)

**Output:**
```diff
--- My To-Do List ---
+ 1. [T][ ] read book
+ 2. [D][ ] submit report (by: Dec 10 2024, 5:00 PM)
+ 3. [E][ ] team meeting (from: Dec 10 2024, 2:00 PM to: Dec 10 2024, 3:00 PM)
```
</details>

<details>
<summary>📝 <b>Add a todo</b> — <img src="https://img.shields.io/badge/todo-2196F3?style=flat-square" alt="todo"></summary>

<br>

Adds a simple todo task without any date/time attached.

> **Format:** ![todo](https://img.shields.io/badge/todo_<description>-2196F3?style=flat-square)

**Example:**
```
todo read book
```

**Output:**
```diff
+ Got it! `read book` is in the list
```
</details>

<details>
<summary>⏰ <b>Add a deadline</b> — <img src="https://img.shields.io/badge/deadline-FF5722?style=flat-square" alt="deadline"></summary>

<br>

Adds a task with a deadline.

> **Format:** ![deadline](https://img.shields.io/badge/deadline_<desc>_/by_<date>-FF5722?style=flat-square)

> 📅 **Date format:** `YYYY-MM-DDTHH:MM` (e.g., `2024-12-10T17:00`)

**Example:**
```
deadline submit report /by 2024-12-10T17:00
```

**Output:**
```diff
+ Got it! `submit report` is in the list
```
</details>

<details>
<summary>📅 <b>Add an event</b> — <img src="https://img.shields.io/badge/event-9C27B0?style=flat-square" alt="event"></summary>

<br>

Adds an event with a start and end time.

> **Format:** ![event](https://img.shields.io/badge/event_<desc>_/from_<start>_/to_<end>-9C27B0?style=flat-square)

> 📅 **Date format:** `YYYY-MM-DDTHH:MM`

**Example:**
```
event team meeting /from 2024-12-10T14:00 /to 2024-12-10T15:00
```

**Output:**
```diff
+ Got it! `team meeting` is in the list
```
</details>

<details>
<summary>✅ <b>Mark task as done</b> — <img src="https://img.shields.io/badge/mark-00C853?style=flat-square" alt="mark"></summary>

<br>

Marks a task as completed.

> **Format:** ![mark](https://img.shields.io/badge/mark_<number>-00C853?style=flat-square)

**Example:**
```
mark 1
```

**Output:**
```diff
+ slayyy, cleared tasks? that's productivity core fr
+ [T][X] read book
```
</details>

<details>
<summary>↩️ <b>Unmark a task</b> — <img src="https://img.shields.io/badge/unmark-FFC107?style=flat-square" alt="unmark"></summary>

<br>

Marks a task as not done.

> **Format:** ![unmark](https://img.shields.io/badge/unmark_<number>-FFC107?style=flat-square)

**Example:**
```
unmark 1
```

**Output:**
```diff
! lowkey proud of you for even adding it instead of ignoring it
! [T][ ] read book
```
</details>

<details>
<summary>🗑️ <b>Delete a task</b> — <img src="https://img.shields.io/badge/delete-F44336?style=flat-square" alt="delete"></summary>

<br>

Removes a task from the list.

> **Format:** ![delete](https://img.shields.io/badge/delete_<number>-F44336?style=flat-square)

**Example:**
```
delete 1
```

**Output:**
```diff
- sheeeesh, task deleted? that's main character productivity energy fr
- [T][ ] read book
- Now you've got 2 tasks vibin' in the list.
```
</details>

<details>
<summary>🔍 <b>Find tasks</b> — <img src="https://img.shields.io/badge/find-03A9F4?style=flat-square" alt="find"></summary>

<br>

Searches for tasks containing the given keyword.

> **Format:** ![find](https://img.shields.io/badge/find_<keyword>-03A9F4?style=flat-square)

**Example:**
```
find book
```

**Output:**
```diff
@@ Here are the matching tasks in your list: @@
+ 1. [T][ ] read book
```
</details>

<details>
<summary>👋 <b>Exit application</b> — <img src="https://img.shields.io/badge/exit-607D8B?style=flat-square" alt="exit"></summary>

<br>

Closes Yappy.

> **Format:** ![exit](https://img.shields.io/badge/exit-607D8B?style=flat-square)

**Output:**
```
Ohhh you're going now! Anw thanks for yapping with me
```
</details>

---

## 📚 Task Reference

### Task Types

| Icon | Symbol | Type | Badge | Description |
|:----:|:------:|:-----|:-----:|:------------|
| 📝 | `[T]` | Todo | ![Todo](https://img.shields.io/badge/T-2196F3?style=flat-square) | Simple task without date |
| ⏰ | `[D]` | Deadline | ![Deadline](https://img.shields.io/badge/D-FF5722?style=flat-square) | Task with a due date |
| 📅 | `[E]` | Event | ![Event](https://img.shields.io/badge/E-9C27B0?style=flat-square) | Task with start and end time |

### Task Status

| Symbol | Badge | Status | Meaning |
|:------:|:-----:|:-------|:--------|
| `[X]` | ![Done](https://img.shields.io/badge/X-00C853?style=flat-square) | ✅ Completed | Task is done! |
| `[ ]` | ![Pending](https://img.shields.io/badge/_-gray?style=flat-square) | ⬜ Pending | Task not done yet |

---

## 💾 Data Storage

<table>
<tr>
<td>
<img src="https://img.shields.io/badge/Auto_Save-00C853?style=for-the-badge&logo=files&logoColor=white" alt="Auto Save">

Tasks are **automatically saved** to `data/tasks.txt` and loaded when you restart Yappy.

**No manual saving required!** 🎉
</td>
</tr>
</table>

---

## 📖 Command Summary

| Command | Badge | Format | Description |
|:--------|:-----:|:-------|:------------|
| 📋 List | ![list](https://img.shields.io/badge/list-4CAF50?style=flat-square) | `list` | Show all tasks |
| 📝 Todo | ![todo](https://img.shields.io/badge/todo-2196F3?style=flat-square) | `todo <description>` | Add a simple task |
| ⏰ Deadline | ![deadline](https://img.shields.io/badge/deadline-FF5722?style=flat-square) | `deadline <desc> /by <date>` | Add task with deadline |
| 📅 Event | ![event](https://img.shields.io/badge/event-9C27B0?style=flat-square) | `event <desc> /from <start> /to <end>` | Add an event |
| ✅ Mark | ![mark](https://img.shields.io/badge/mark-00C853?style=flat-square) | `mark <number>` | Mark task as done |
| ↩️ Unmark | ![unmark](https://img.shields.io/badge/unmark-FFC107?style=flat-square) | `unmark <number>` | Mark task as not done |
| 🗑️ Delete | ![delete](https://img.shields.io/badge/delete-F44336?style=flat-square) | `delete <number>` | Remove a task |
| 🔍 Find | ![find](https://img.shields.io/badge/find-03A9F4?style=flat-square) | `find <keyword>` | Search for tasks |
| 👋 Exit | ![exit](https://img.shields.io/badge/exit-607D8B?style=flat-square) | `exit` | Close Yappy |

---

<p align="center">
  <img src="https://img.shields.io/badge/Made_with-❤️-red?style=for-the-badge" alt="Made with love">
  <img src="https://img.shields.io/badge/and_lots_of-yapping-yellow?style=for-the-badge" alt="and lots of yapping">
</p>
