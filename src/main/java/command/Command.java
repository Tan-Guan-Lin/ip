package command;

import tasklist.TaskList;

public abstract class Command {
    protected CommandType type;

    public Command(CommandType type) {
        this.type = type;
    }

    public abstract void execute(TaskList taskList) throws Exception;

    public boolean isExit() {
        return type == CommandType.BYE;
    }

}