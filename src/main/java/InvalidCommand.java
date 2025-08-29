public class InvalidCommand extends Command {
    private String message;

    public InvalidCommand(String message) {
        super(CommandType.INVALID);
        this.message = message;
    }

    @Override
    public void execute(TaskList taskList) {
        UI.showMessage(message);
    }
}
