package command;

import tasklist.TaskList;
import ui.UI;

public class ByeCommand extends Command {
    public ByeCommand() {
        super(CommandType.BYE);
    }

    @Override
    public void execute(TaskList taskList) {
        UI.showExit();
    }
}
