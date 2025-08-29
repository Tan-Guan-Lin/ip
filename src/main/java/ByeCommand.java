public class ByeCommand extends Command {
    public ByeCommand() {
        super(CommandType.BYE);
    }

    @Override
    public void execute(TaskList taskList) {
        UI.showExit();
    }
}
