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

> 💡 **Prerequisites:** Java 17 or above installed on your system

```bash
# Download and run
java -jar yappy.jar
```

1. 📥 Download the latest `yappy.jar` from releases
2. 💻 Open a terminal in the download folder
3. ▶️ Run `java -jar yappy.jar`
4. 💬 Start yapping with Yappy!

---

## ✨ Features

### 📋 List all tasks — ![list](https://img.shields.io/badge/list-4CAF50?style=flat-square)

Shows all tasks in your task list.

> **Format:** `list`

**Output:**
```diff
--- My To-Do List ---
+ 1. [T][ ] read book
+ 2. [D][ ] submit report (by: Dec 10 2024, 5:00 PM)
+ 3. [E][ ] team meeting (from: Dec 10 2024, 2:00 PM to: Dec 10 2024, 3:00 PM)
```

---

### 📝 Add a todo — ![todo](https://img.shields.io/badge/todo-2196F3?style=flat-square)

Adds a simple todo task without any date/time attached.

> **Format:** `todo <description>`

**Example:**
```
todo read book
```

**Output:**
```diff
+ Got it! `read book` is in the list
```

---

### ⏰ Add a deadline — ![deadline](https://img.shields.io/badge/deadline-FF5722?style=flat-square)

Adds a task with a deadline.

> **Format:** `deadline <description> /by <date>`

> 📅 **Date format:** `YYYY-MM-DDTHH:MM` (e.g., `2024-12-10T17:00`)

**Example:**
```
deadline submit report /by 2024-12-10T17:00
```

**Output:**
```diff
+ Got it! `submit report` is in the list
```

---

### 📅 Add an event — ![event](https://img.shields.io/badge/event-9C27B0?style=flat-square)

Adds an event with a start and end time.

> **Format:** `event <description> /from <start> /to <end>`

> 📅 **Date format:** `YYYY-MM-DDTHH:MM`

**Example:**
```
event team meeting /from 2024-12-10T14:00 /to 2024-12-10T15:00
```

**Output:**
```diff
+ Got it! `team meeting` is in the list
```

---

### ✅ Mark task as done — ![mark](https://img.shields.io/badge/mark-00C853?style=flat-square)

Marks a task as completed.

> **Format:** `mark <task number>`

**Example:**
```
mark 1
```

**Output:**
```diff
+ slayyy, cleared tasks? that's productivity core fr
+ [T][X] read book
```

---

### ↩️ Unmark a task — ![unmark](https://img.shields.io/badge/unmark-FFC107?style=flat-square)

Marks a task as not done.

> **Format:** `unmark <task number>`

**Example:**
```
unmark 1
```

**Output:**
```diff
! lowkey proud of you for even adding it instead of ignoring it
! [T][ ] read book
```

---

### 🗑️ Delete a task — ![delete](https://img.shields.io/badge/delete-F44336?style=flat-square)

Removes a task from the list.

> **Format:** `delete <task number>`

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

---

### 🔍 Find tasks — ![find](https://img.shields.io/badge/find-03A9F4?style=flat-square)

Searches for tasks containing the given keyword.

> **Format:** `find <keyword>`

**Example:**
```
find book
```

**Output:**
```diff
@@ Here are the matching tasks in your list: @@
+ 1. [T][ ] read book
```

---

### 👋 Exit application — ![exit](https://img.shields.io/badge/exit-607D8B?style=flat-square)

Closes Yappy.

> **Format:** `exit`

**Output:**
```
Ohhh you're going now! Anw thanks for yapping with me
```

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

![Auto Save](https://img.shields.io/badge/Auto_Save-00C853?style=for-the-badge&logo=files&logoColor=white)

Tasks are **automatically saved** to `data/tasks.txt` and loaded when you restart Yappy.

**No manual saving required!** 🎉

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
