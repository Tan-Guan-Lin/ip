package command;

import tasklist.TaskList;

/**
 * Abstract base class for all commands that can be executed by a user.
 * Each command has a specific type represented by an enum.
 */
public abstract class Command {

    /**
     * Command type of this command
     */
    protected CommandType type;

    /**
     * Creates a Command with the specified type.
     *
     * @param type the command type
     */
    public Command(CommandType type) {
        this.type = type;
    }

    /**
     * Executes the command on the given task list.
     *
     * @param taskList the task list to operate on
     * @throws Exception if execution fails
     */
    public abstract void execute(TaskList taskList) throws Exception;

    /**
     * Checks if this command is an exit command.
     *
     * @return true if this is an exit command, false otherwise
     */
    public boolean isExit() {
        return type == CommandType.BYE;
    }
}
