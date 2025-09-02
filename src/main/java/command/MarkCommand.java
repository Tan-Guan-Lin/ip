package command;

import tasklist.TaskList;
import ui.UI;

/**
 * Represents a command to mark a task as completed.
 * Updates the task status and displays confirmation.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructs a MarkCommand for the specified task index.
     *
     * @param index the 1-based index of the task to mark as done
     */
    public MarkCommand(int index) {
        super(CommandType.MARK);
        this.index = index;
    }

    /**
     * {@inheritDoc}
     * Marks the task at the specified index as completed
     * and displays a confirmation message with the marked task.
     *
     * @param taskList the task list containing the task to mark
     */
    @Override
    public void execute(TaskList taskList) {
        taskList.markTask(index);
        UI.showMessage("Nice! I've marked this task as done:");
        UI.showMessage(taskList.getAllTasks().get(index).toString());
    }
}
