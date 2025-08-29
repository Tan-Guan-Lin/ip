public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        super(CommandType.MARK);
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList) {
        taskList.markTask(index);
        UI.showMessage("Nice! I've marked this task as done:");
        UI.showMessage(taskList.getAllTasks().get(index).toString());
    }
}