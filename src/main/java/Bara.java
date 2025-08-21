import java.util.Scanner;

public class Bara {


    public static void main(String[] args) {
        Operations.greet();
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            String input = sc.nextLine();
            try{
                Command command = Parser.parse(input);
                Operations.printLine();
                switch(command) {
                    case BYE -> {
                        Operations.exit();
                        sc.close();
                        return;
                    }
                    case LIST -> Operations.printList();
                    case MARK -> Operations.mark(input);
                    case UNMARK -> Operations.unMark(input);
                    case CREATE_TODO -> Operations.createTodo(input);
                    case CREATE_DEADLINE -> Operations.createDeadline(input);
                    case CREATE_EVENT -> Operations.createEvent(input);
                    case DELETE -> Operations.delete(input);
                    case INVALID -> throw new IllegalArgumentException("bara-bara cannot recognize this command -> please try again");
                }
            } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                Operations.printLine();
            }


            Operations.printLine();
        }
    }
}