package command;

import tasklist.TaskList;
import ui.UI;

/**
 * Represents a command to unmark a completed task.
 * Updates the task status and displays confirmation.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs an UnmarkCommand for the specified task index.
     *
     * @param index the 1-based index of the task to unmark
     */
    public UnmarkCommand(int index) {
        super(CommandType.UNMARK);
        this.index = index;
    }

    /**
     * {@inheritDoc}
     * Unmarks the task at the specified index (sets as not done)
     * and displays a confirmation message with the unmarked task.
     *
     * @param taskList the task list containing the task to unmark
     */
    @Override
    public void execute(TaskList taskList) {
        taskList.unmarkTask(index);
        UI.showMessage("OK, I've marked this task as not done yet:");
        UI.showMessage(taskList.getAllTasks().get(index).toString());
    }
}
