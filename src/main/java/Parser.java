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
                    throw new IllegalArgumentException("Please check the format of your command :O ");
                }
                break;
            case "list":
                if(tokens.length == 1) ret = Command.LIST;
                else {
                    throw new IllegalArgumentException("Please check the format of your command :O ");
                }
                break;
            case "mark":
                if(validMark(tokens, input, Operations.getListSize())) {
                    ret = Command.MARK;
                } else {
                    throw new IllegalArgumentException("Please check the format of your command :O ");
                }
                break;
            case "unmark":
                if(validUnmark(tokens, input, Operations.getListSize())) {
                    ret = Command.UNMARK;
                } else {
                    throw new IllegalArgumentException("Please check the format of your command :O ");
                }
                break;
            case "todo":
                if(validToDo(input)) {
                    ret = Command.CREATE_TODO;
                } else {
                    throw new IllegalArgumentException("Please check the format of your command :O ");
                }
                break;
            case "deadline":
                if(validDeadline(input)) {
                    ret = Command.CREATE_DEADLINE;
                } else {
                    throw new IllegalArgumentException("Please check the format of your command :O ");
                }
                break;
            case "event":
                if(validEvent(input)) {
                    ret = Command.CREATE_EVENT;
                } else {
                    throw new IllegalArgumentException("Please check the format of your command :O ");
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
        return input.startsWith("todo ");
    }

    private static boolean validDeadline(String input) {
        return Pattern.matches("deadline\\s.*\\s/by\\s.*", input);
    }

    private static boolean validEvent(String input) {
        return Pattern.matches("event\\s.*\\s/from\\s.*\\s/to\\s.*", input);
    }
}
