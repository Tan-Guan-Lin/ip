import java.util.regex.Pattern;

public class Parser {
    public static boolean validMark (String[] tokens, String command, int size) {
        return tokens.length == 2 &&
                Pattern.matches("mark\\s\\d+", command) &&
                Integer.parseInt(tokens[1]) > 0 &&
                Integer.parseInt(tokens[1]) < size + 1;
    }
    public static boolean validUnmark (String[] tokens, String command, int size) {
        return tokens.length == 2 &&
                Pattern.matches("unmark\\s\\d+", command) &&
                Integer.parseInt(tokens[1]) > 0 &&
                Integer.parseInt(tokens[1]) < size + 1;
    }
}
