package command;

import tasklist.TaskList;
import task.Task;
import task.Todo;
import ui.UI;

public class TodoCommand extends Command {
    private String description;

    public TodoCommand(String description) {
        super(CommandType.CREATE_TODO);
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList) {
        Task todo = new Todo(description);
        taskList.addTask(todo);
        UI.showMessage("Got it. I've added this task:");
        UI.showMessage(todo.toString());
        UI.showMessage("Now you have " + taskList.size() + " tasks in the list.");
    }
}