package ui;

public class UI {
    protected static String horizontalLine = "____________________________________________________________";

    public static String greet() {
        String capybara2 = """
                              // __ //
                            /    O   \\_____________
                           /                        \\
                          | Y                        \\
                           \\ ___ /|    |   ||   |   |
                         // __ //  \\  /\\  /\\  /\\  /
                        /    O   \\_________________
                       /                            \\
                      | Y                            \\
                      \\ ___ /|    |   |______|    |   |
                              \\  /\\  /       \\   /\\  /
                """;

        return "Hello! I'm bara-bara! \n"
                + "What can I do for you?";
    }

    public static String printLine() {
        return horizontalLine + "\n";
    }

    public static String showMessage(String message) {
        return message + "\n";
    }

    public static String showExit() {
        return "Bye. Hope to see you again soon! \n";
    };

}
