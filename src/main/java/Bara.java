import java.util.Scanner;

public class Bara {


    public static void main(String[] args) {
        Operations.greet();
        program:
        while(true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            Command command = Parser.parse(input);
            Operations.printLine();
            switch(command) {
                case BYE -> {
                    Operations.exit();
                    break program;
                }
                case LIST -> Operations.printList();
                case MARK -> Operations.mark(input);
                case UNMARK -> Operations.unMark(input);
                case CREATE_TODO -> Operations.createTodo(input);
                case CREATE_DEADLINE -> Operations.createDeadline(input);
                case CREATE_EVENT -> Operations.createEvent(input);
                case INVALID -> throw new IllegalArgumentException("Command not found. Please try again");
            }

            Operations.printLine();
        }
    }
}