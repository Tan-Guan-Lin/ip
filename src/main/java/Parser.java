import java.util.regex.Pattern;

public class Parser {
    public static Command parse(String input) {
        String[] tokens = input.split("\\s");
        Command ret = null;
        if(tokens.length == 0) throw new IllegalArgumentException();
        switch(tokens[0]) {
            case "bye":
                if(tokens.length == 1) ret = Command.BYE;
                else {
                    throw new IllegalArgumentException("Please do not add anything behind this command:\n" +
                            "correct usage: bye");
                }
                break;
            case "list":
                if(tokens.length == 1) ret = Command.LIST;
                else {
                    throw new IllegalArgumentException("Please do not add anything behind this command:\n" +
                            "correct usage: list");
                }
                break;
            case "mark":
                if(validMark(tokens, input, Operations.getListSize())) {
                    ret = Command.MARK;
                } else {
                    throw new IllegalArgumentException("""
                            mark requires an integer argument!
                            correct usage: unmark <task_number>
                            where task_number is the number in front of the task after the list command""");
                }
                break;
            case "unmark":
                if(validUnmark(tokens, input, Operations.getListSize())) {
                    ret = Command.UNMARK;
                } else {
                    throw new IllegalArgumentException("""
                            unmark requires an integer argument!
                            correct usage: unmark <task_number>
                            where task_number is the number in front of the task after the list command""");
                }
                break;
            case "todo":
                if(validToDo(input)) {
                    ret = Command.CREATE_TODO;
                } else {
                    throw new IllegalArgumentException("todo tasks need a description :O\n" +
                            "correct usage: todo <task_description>");
                }
                break;
            case "deadline":
                if(validDeadline(input)) {
                    ret = Command.CREATE_DEADLINE;
                } else {
                    throw new IllegalArgumentException(":( deadline tasks need deadlines\n" +
                            "correct usage: deadline <task_description> /by <task_deadline>");
                }
                break;
            case "event":
                if(validEvent(input)) {
                    ret = Command.CREATE_EVENT;
                } else {
                    throw new IllegalArgumentException(":( events needs start and end dates\n" +
                            "correct usage: event <event_description> /from <start_date> /to <end_date>");
                }
                break;
            case "delete":
                if(validDelete(tokens, input)) {
                    ret = Command.DELETE;
                } else {
                    throw new IllegalArgumentException("""
                            delete requires an integer argument!
                            correct usage: unmark <task_number>
                            where task_number is the number in front of the task after the list command""");
                }
                break;
            default:
                ret = Command.INVALID;
        }
        return ret;

    }


    private static boolean validMark (String[] tokens, String input, int size) {
        return tokens.length == 2 &&
                Pattern.matches("mark\\s\\d+", input);
    }
    private static boolean validUnmark (String[] tokens, String input, int size) {
        return tokens.length == 2 &&
                Pattern.matches("unmark\\s\\d+", input);
    }
    private static boolean validToDo(String input) {
        return Pattern.matches("todo\\s.*", input);
    }

    private static boolean validDeadline(String input) {
        return Pattern.matches("deadline\\s.*\\s/by\\s.*", input);
    }

    private static boolean validEvent(String input) {
        return Pattern.matches("event\\s.*\\s/from\\s.*\\s/to\\s.*", input);
    }
    private static boolean validDelete(String[] tokens, String input) {
        return tokens.length == 2 &&
                Pattern.matches("delete\\s\\d+", input);
    }
}
