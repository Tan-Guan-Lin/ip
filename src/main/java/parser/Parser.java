package parser;

import java.util.Arrays;
import java.util.regex.Pattern;

import command.ByeCommand;
import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.EventCommand;
import command.FindCommand;
import command.InvalidCommand;
import command.ListCommand;
import command.MarkCommand;
import command.TodoCommand;
import command.UnmarkCommand;
import tasklist.TaskList;

/**
 * Parses user input strings into executable Command objects.
 * Handles validation and conversion of user commands.
 */
public class Parser {

    /**
     * Parses the user input string and returns the corresponding Command object.
     * Validates input format and handles various command types with appropriate error messages.
     *
     * @param input    the user input string to parse
     * @param taskList the current task list used for validation (e.g., index bounds checking)
     * @return a Command object corresponding to the parsed input, or InvalidCommand on error
     */
    public static Command parse(String input, TaskList taskList) {
        String[] tokens = input.split("\\s");

        if (tokens.length == 0) {
            return new InvalidCommand("Empty command");
        }

        try {
            switch (tokens[0]) {
            case "bye":
                validateNoExtraArguments(tokens, "bye");
                return new ByeCommand();

            case "list":
                validateNoExtraArguments(tokens, "list");
                return new ListCommand();

            case "mark":
                int markIndex = validateAndGetIndex(tokens, input, "mark", taskList.size());
                return new MarkCommand(markIndex);

            case "unmark":
                int unmarkIndex = validateAndGetIndex(tokens, input, "unmark", taskList.size());
                return new UnmarkCommand(unmarkIndex);

            case "todo":
                validateTodo(input);
                String todoDesc = input.replaceFirst("todo\\s", "");
                return new TodoCommand(todoDesc);

            case "deadline":
                validateDeadline(input);
                String deadlineInput = input.replaceFirst("deadline\\s", "");
                String[] deadlineParts = deadlineInput.split("\\s/by\\s");
                return new DeadlineCommand(deadlineParts[0], deadlineParts[1]);

            case "event":
                validateEvent(input);
                String eventInput = input.replaceFirst("event\\s", "");
                String[] eventParts = eventInput.split("\\s/from\\s");
                String[] dates = eventParts[1].split("\\s/to\\s");
                return new EventCommand(eventParts[0], dates[0], dates[1]);

            case "delete":
                int deleteIndex = validateAndGetIndex(tokens, input, "delete", taskList.size());
                return new DeleteCommand(deleteIndex);

            case "find":
                if (!checkCommandLongerThan(tokens, 2)) {
                    return new InvalidCommand("bara-bara needs a keyword to find related tasks");
                }
                String[] searchTerms = Arrays.copyOfRange(tokens, 1, tokens.length);
                return new FindCommand(searchTerms);

            default:
                return new InvalidCommand("bara-bara cannot recognize this command -> please try again");
            }
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    private static void validateNoExtraArguments(String[] tokens, String command) {
        if (tokens.length != 1) {
            throw new IllegalArgumentException("Please do not add anything behind "
                    + command
                    + " command\ncorrect usage: "
                    + command);
        }
    }

    private static int validateAndGetIndex(String[] tokens, String input, String command, int size) {
        if (tokens.length != 2 || !Pattern.matches(command + "\\s\\d+", input)) {
            throw new IllegalArgumentException(command
                    + " requires an integer argument!\n"
                    + "correct usage: "
                    + command
                    + " <task_number>\n"
                    + "where task_number is the number in front of the task after the list command");
        }

        int index = Integer.parseInt(tokens[1]) - 1;
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    String.format("bara-bara can't find this task in the list :(\nrange: %d - %d",
                            1, size));
        }

        return index;
    }

    private static void validateTodo(String input) {
        if (!Pattern.matches("todo\\s.*", input)) {
            throw new IllegalArgumentException("todo tasks need a description :O\n"
                    + "correct usage: todo <task_description>");
        }
    }

    private static void validateDeadline(String input) {
        if (!Pattern.matches("deadline\\s.*\\s/by\\s.*", input)) {
            throw new IllegalArgumentException(":( deadline tasks need deadlines\n"
                    + "correct usage: deadline <task_description> /by <task_deadline>");
        }
    }

    private static void validateEvent(String input) {
        if (!Pattern.matches("event\\s.*\\s/from\\s.*\\s/to\\s.*", input)) {
            throw new IllegalArgumentException(":( events needs start and end dates\n"
                    + "correct usage: event <event_description> /from <start_date> /to <end_date>");
        }
    }

    private static boolean checkCommandLongerThan(String[] tokens, int n) {
        return tokens.length >= n;
    }
}
