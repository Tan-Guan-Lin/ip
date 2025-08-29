package command;

import tasklist.TaskList;
import task.Deadline;
import task.Task;
import ui.UI;

public class DeadlineCommand extends Command {
    private String description;
    private String by;

    public DeadlineCommand(String description, String by) {
        super(CommandType.CREATE_DEADLINE);
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList taskList) {
        Task deadline = new Deadline(description, by);
        taskList.addTask(deadline);
        UI.showMessage("Got it. I've added this task:");
        UI.showMessage(deadline.toString());
        UI.showMessage("Now you have " + taskList.size() + " tasks in the list.");
    }
}