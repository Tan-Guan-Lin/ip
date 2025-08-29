public class EventCommand extends Command {
    private String description;
    private String from;
    private String to;

    public EventCommand(String description, String from, String to) {
        super(CommandType.CREATE_EVENT);
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList taskList) {
        Task event = new Event(description, from, to);
        taskList.addTask(event);
        UI.showMessage("Got it. I've added this task:");
        UI.showMessage(event.toString());
        UI.showMessage("Now you have " + taskList.size() + " tasks in the list.");
    }
}