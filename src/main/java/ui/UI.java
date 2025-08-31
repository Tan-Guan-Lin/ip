package ui;

public class UI {
    protected static String horizontalLine = "____________________________________________________________";

    public static void greet() {
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

        printLine();
        System.out.println();
        System.out.println(capybara2);
        System.out.println("Hello! I'm bara-bara");
        System.out.println("What can I do for you?");
        printLine();
    }

    public static void printLine() {
        System.out.println(horizontalLine);
    }

    public static void showMessage(String message) {
        System.out.println(message);
    }

    public static void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    };

}