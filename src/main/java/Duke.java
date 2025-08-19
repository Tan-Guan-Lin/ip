

public class Duke {
    public static void main(String[] args) {
        greet();
    }

    public static void greet() {
        String horizontalLine = "____________________________________________________________";
        String capybara = """
               
                        // __ //
                       /    O   \\_________________
                      /                            \\
                     | Y                            \\
                     \\ ___ /|    |   |______|    |   | 
                             \\  /\\  /       \\   /\\  /
                                  
                """;
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

        System.out.println(horizontalLine);
        System.out.println();
        System.out.println(capybara2);
        System.out.println("Hello! I'm bara-bara");
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }
}