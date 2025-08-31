package command;

import java.util.List;

import task.Task;
import tasklist.TaskList;
import ui.UI;

/**
 * Represents a command to find tasks containing a specific search string.
 * Searches through task descriptions and displays matching results or a no-results message.
 */
public class FindCommand extends Command {

    private String search;

    /**
     * Constructs a FindCommand with the specified search string.
     *
     * @param search the search string to match against task descriptions
     */
    public FindCommand(String search) {
        super(CommandType.FIND);
        this.search = search;
    }

    /**
     * {@inheritDoc}
     * Executes the find command by searching for tasks containing the search string
     * and displaying the results. Shows a message if no matching tasks are found.
     *
     * @param taskList the task list to search through
     * @throws Exception if an error occurs during the search operation
     */
    @Override
    public void execute(TaskList taskList) throws Exception {
        List<Task> searchResult = taskList.findTasks(search);
        if(searchResult.isEmpty()) {
            UI.showMessage("No result found");
            return;
        }
        UI.showMessage("Here are the matching tasks in your list:");
        for(Task task : searchResult) {
            UI.showMessage(task.toString());
        }
    }
}