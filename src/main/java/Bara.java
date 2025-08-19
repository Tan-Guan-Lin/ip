import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Bara {
    public static String horizontalLine = "____________________________________________________________";
    public static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        greet();
        while(true) {
            Scanner sc = new Scanner(System.in);
            String command = sc.nextLine();
            String[] tokens = command.split("\\s");
            System.out.println(horizontalLine);
            // app only quits on "bye" and not any variants like "bye\\s+" with trailing whitespaces
            if(command.equals("bye")) {
                exit();
                break;
            } else if (command.equals("list")) {
                printList();
            } else if (Parser.validMark(tokens, command, taskList.size())) {
                int index = Integer.parseInt(tokens[1]);
                mark(index);
                System.out.println("Nice! I've marked this task as done:");
                printTask(index);
            } else if (Parser.validUnmark(tokens, command, taskList.size())){
                int index = Integer.parseInt(tokens[1]);
                unMark(index);
                System.out.println("OK, I've marked this task as not done yet:");
                printTask(index);
            } else {
                System.out.println("added: " + command);
                taskList.add(new Task(command));
            }
            System.out.println(horizontalLine);
        }



    }

    public static void greet() {
        String capybara = """
              \s
                        // __ //
                       /    O   \\_________________
                      /                            \\
                     | Y                            \\
                     \\ ___ /|    |   |______|    |   |
                             \\  /\\  /       \\   /\\  /
                                 \s
               \s""";
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
                     \\ ___ /|    |   |______|    |   |\s
                             \\  /\\  /       \\   /\\  /
               \s""";

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

    public static void printList() {
        int i = 1;
        for(Task task : taskList) {
            System.out.printf(String.format("%d. %s\n", i, task.getTask()));
            i++;
        }
    }

    public static void mark(int i) {
        taskList.get(i - 1).mark();
    }

    public static void unMark(int i) {
        taskList.get(i - 1).unMark();
    }

    public static void printTask(int i) {
        System.out.println(taskList.get(i - 1).getTask());
    }
}