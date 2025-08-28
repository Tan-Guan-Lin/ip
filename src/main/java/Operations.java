import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Operations {
    protected static String horizontalLine = "____________________________________________________________";
    protected static ArrayList<Task> taskList = new ArrayList<>();
    protected static java.nio.file.Path path = Paths.get("data", "Bara.txt");

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

    public static void initialize() {
        File file = path.toFile();
        try {
            if (!file.exists()) {
                Path parentDir = path.getParent();
                if (parentDir != null && !Files.exists(parentDir)) {
                    Files.createDirectories(parentDir);
                }
                Files.createFile(path);
                System.out.println("file created!"); // test
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()) {
                String curr = sc.nextLine();
                String[] task = curr.split("\\s\\|\\s");

                try{
                    if(!task[1].equals("1") && !task[1].equals("0")){
                        throw new IllegalArgumentException("Invalid mark status");
                    }
                    switch (task[0]) {
                    case "T":
                        if(task.length != 3) {
                            throw new IllegalArgumentException("Format error");
                        }
                        taskList.add(new Todo(task[2], Integer.parseInt(task[1]) == 1));
                        break;
                    case "D":
                        if(task.length != 4) {
                            throw new IllegalArgumentException("Format error");
                        }
                        taskList.add(new Deadline(task[2], Integer.parseInt(task[1]) == 1, task[3]));
                        break;
                    case "E":
                        if(task.length != 5) {
                            throw new IllegalArgumentException("Format error");
                        }
                        taskList.add(new Event(task[2], Integer.parseInt(task[1]) == 1, task[3], task[4]));
                        break;
                    default:
                        throw new IllegalArgumentException("Task type invalid");

                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Corrupted file: " + e.getMessage());
                    exit();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            exit();
        }


    }
    public static void exit() {
        StringBuilder data = new StringBuilder();
        for(Task t : taskList) {
            data.append(t.store());
        }
        try {
            Files.writeString(path, data, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
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
