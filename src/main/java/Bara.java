import java.util.ArrayList;
import java.util.Scanner;

public class Bara {
    public static String horizontalLine = "____________________________________________________________";

    public static void main(String[] args) {
        greet();
        ArrayList<String> taskList = new ArrayList<>();
        while(true) {
            Scanner sc = new Scanner(System.in);
            String command = sc.nextLine();
            System.out.println(horizontalLine);
            if(command.equals("bye")) {
                exit();
                break;
            } else if (command.equals("list")) {
                int i = 1;
                for(String s : taskList) {
                    System.out.printf(String.format("%d. %s\n", i, s));
                    i++;
                }
            } else {
                System.out.println("added: " + command);
                taskList.add(command);
            }
            System.out.println(horizontalLine);
        }



    }

    public static void greet() {
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

    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }
}