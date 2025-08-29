package command;

import tasklist.TaskList;

public class ListCommand extends Command {
    public ListCommand() {
        super(CommandType.LIST);
    }

    @Override
    public void execute(TaskList taskList) {
        taskList.printTasks();
    }
}