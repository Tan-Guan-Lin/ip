package command;

import task.Deadline;
import task.Task;
import tasklist.TaskList;
import ui.UI;

/**
 * Represents a command to create a new deadline task.
 * Adds the deadline to the task list and displays confirmation.
 */
public class DeadlineCommand extends Command {
    private String description;
    private String by;

    /**
     * Constructs a DeadlineCommand with the given description and deadline.
     *
     * @param description the description of the deadline task
     * @param by          the deadline date/time string
     */
    public DeadlineCommand(String description, String by) {
        super(CommandType.CREATE_DEADLINE);
        this.description = description;
        this.by = by;
    }

    /**
     * {@inheritDoc}
     * Creates a new deadline task, adds it to the task list,
     * and displays confirmation messages.
     *
     * @param taskList the task list to which the deadline will be added
     */
    @Override
    public void execute(TaskList taskList) {
        Task deadline = new Deadline(description, by);
        taskList.addTask(deadline);
        UI.showMessage("Got it. I've added this task:");
        UI.showMessage(deadline.toString());
        UI.showMessage("Now you have " + taskList.size() + " tasks in the list.");
    }
}