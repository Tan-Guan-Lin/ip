package tasklist;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import storage.Storage;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import ui.UI;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void loadFromStorage() {
        File file = Storage.getFilePath().toFile();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String curr = sc.nextLine();
                String[] taskData = curr.split("\\s\\|\\s");

                try {
                    if (!taskData[1].equals("1") && !taskData[1].equals("0")) {
                        throw new IllegalArgumentException("Invalid mark status");
                    }

                    boolean isDone = Integer.parseInt(taskData[1]) == 1;
                    String description = taskData[2];

                    switch (taskData[0]) {
                    case "T":
                        if (taskData.length != 3) {
                            throw new IllegalArgumentException("Format error");
                        }
                        tasks.add(new Todo(description, isDone));
                        break;
                    case "D":
                        if (taskData.length != 4) {
                            throw new IllegalArgumentException("Format error");
                        }
                        String by = taskData[3].replace('T', ' ');
                        tasks.add(new Deadline(description, isDone, by));
                        break;
                    case "E":
                        if (taskData.length != 5) {
                            throw new IllegalArgumentException("Format error");
                        }
                        String from = taskData[3].replace('T', ' ');
                        String to = taskData[4].replace('T', ' ');
                        tasks.add(new Event(description, isDone, from, to));
                        break;
                    default:
                        throw new IllegalArgumentException("task.Task type invalid");
                    }
                } catch (IllegalArgumentException e) {
                    UI.showMessage("Corrupted file: " + e.getMessage());
                    UI.printLine();
                }
            }
        } catch (FileNotFoundException e) {
            UI.showMessage(e.getMessage());
            UI.printLine();
        }
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
        Storage.saveTasks(this);
    }

    public void markTask(int index) {
        validateIndex(index);
        tasks.get(index).mark();
        Storage.saveTasks(this);
    }

    public void unmarkTask(int index) {
        validateIndex(index);
        tasks.get(index).unMark();
        Storage.saveTasks(this);
    }

    public void deleteTask(int index) {
        validateIndex(index);
        Task removed = tasks.remove(index);
        Storage.saveTasks(this);
        UI.showMessage("Noted. I've removed this task:");
        UI.showMessage(removed.toString());
        UI.showMessage("Now you have " + size() + " tasks in the list.");
    }

    public void printTasks() {
        if (size() == 0) {
            UI.showMessage("bara-bara thinks you should add some tasks before listing them...");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            UI.showMessage((i + 1) + ". " + tasks.get(i).toString());
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= tasks.size()) {
            if (tasks.isEmpty()) {
                throw new IndexOutOfBoundsException("bara-bara can't see your tasks -- add some tasks now!");
            }
            throw new IndexOutOfBoundsException(
                    String.format("bara-bara can't find this task in the list :(\nrange: %d - %d",
                            1, tasks.size()));
        }
    }
}