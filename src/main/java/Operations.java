import java.util.ArrayList;

public class Operations {
    protected static String horizontalLine = "____________________________________________________________";
    protected static ArrayList<Task> taskList = new ArrayList<>();
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

        Operations.printLine();
        System.out.println();
        System.out.println(capybara2);
        System.out.println("Hello! I'm bara-bara");
        System.out.println("What can I do for you?");
        Operations.printLine();

    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        Operations.printLine();
    }

    public static void printLine() {
        System.out.println(horizontalLine);
    }

    public static void printList() {
        if(getListSize() == 0) {
            System.out.println("bara-bara thinks you should add some tasks before listing them...");
            return;
        }
        int i = 1;
        for(Task task : taskList) {
            System.out.printf(String.format("%d. %s\n", i, task.toString()));
            i++;
        }
    }

    public static void mark(String input) {
        int i = Integer.parseInt(input.split("\\s")[1]);
        if(i > getListSize() || i < 1) {
            if(getListSize() == 0) {
                throw new IndexOutOfBoundsException("bara-bara can't see your tasks -- add some tasks now! " +
                        "\n usage: todo <task_name> OR deadline <task_name> /by <task_deadline> OR event <task_name> /from <start_date> /to <end_date>");
            }
            throw new IndexOutOfBoundsException(String.format("bara-bara can't find this task in the list :(\n" +
                    "range: %d - %d", 1, getListSize()));
        }

        taskList.get(i - 1).mark();
        System.out.println("Nice! I've marked this task as done:");
        printTask(i);
    }

    public static void unMark(String input) {
        int i = Integer.parseInt(input.split("\\s")[1]);
        if(i > getListSize() || i < 1) {
            if(getListSize() == 0) {
                throw new IndexOutOfBoundsException("bara-bara can't see your tasks -- add some tasks now! " +
                        "\n usage: todo <task_name> OR deadline <task_name> /by <task_deadline> OR event <task_name> /from <start_date> /to <end_date>");
            }
            throw new IndexOutOfBoundsException(String.format("bara-bara can't find this task in the list :(\n" +
                    "range: %d - %d", 1, getListSize()));
        }
        taskList.get(i - 1).unMark();
        System.out.println("OK, I've marked this task as not done yet:");
        printTask(i);
    }

    public static void printTask(int i) {
        System.out.println(taskList.get(i - 1).toString());
    }

    public static int getListSize() {
        return taskList.size();
    }

    public static void createTodo(String input) {
        String desc = input.replaceFirst("todo\\s", "");
        Task curr = new Todo(desc);
        taskList.add(curr);
        System.out.println("Got it. I've added this task:");
        System.out.println(curr.toString());
        System.out.println("Now you have " + Operations.getListSize() + " tasks in the list.");
    }

    public static void createDeadline(String input) {
        String s = input.replaceFirst("deadline\\s", "");
        String[] left = s.split("\\s/by\\s");
        String desc = left[0];
        String date = left[1];
        Task curr = new Deadline(desc, date);
        taskList.add(curr);
        System.out.println("Got it. I've added this task:");
        System.out.println(curr.toString());
        System.out.println("Now you have " + Operations.getListSize() + " tasks in the list.");
    }

    public static void createEvent(String input) {
        String s = input.replaceFirst("event\\s", "");
        String[] left = s.split("\\s/from\\s");
        String desc = left[0];
        String[] dates = left[1].split("\\s/to\\s");
        String start = dates[0];
        String end = dates[1];
        Task curr = new Event(desc, start, end);
        taskList.add(curr);
        System.out.println("Got it. I've added this task:");
        System.out.println(curr.toString());
        System.out.println("Now you have " + Operations.getListSize() + " tasks in the list.");

    }

    public static void delete(String input) {
        int i = Integer.parseInt(input.split("\\s")[1]);
        if(i > getListSize() || i < 1) {
            if(getListSize() == 0) {
                throw new IndexOutOfBoundsException("bara-bara can't see your tasks -- add some tasks now! " +
                        "\n usage: todo <task_name> OR deadline <task_name> /by <task_deadline> OR event <task_name> /from <start_date> /to <end_date>");
            }
            throw new IndexOutOfBoundsException(String.format("bara-bara can't find this task in the list :(\n" +
                    "range: %d - %d", 1, getListSize()));
        }
        Task curr = taskList.get(i - 1);
        taskList.remove(i - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println(curr.toString());
        System.out.println("Now you have " + Operations.getListSize() + " tasks in the list.");
    }
}
