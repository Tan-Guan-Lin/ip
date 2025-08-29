public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        super(CommandType.UNMARK);
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList) {
        taskList.unmarkTask(index);
        UI.showMessage("OK, I've marked this task as not done yet:");
        UI.showMessage(taskList.getAllTasks().get(index).toString());
    }
}
