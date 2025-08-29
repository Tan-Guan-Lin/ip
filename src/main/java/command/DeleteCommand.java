package command;

import tasklist.TaskList;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        super(CommandType.DELETE);
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList) {
        taskList.deleteTask(index);
    }

}