package bara;

import java.util.Scanner;

import command.Command;
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.UI;

public class Bara {
    private static TaskList taskList = new TaskList();

    public static void main(String[] args) {
        UI.greet();
        Storage.initialize();
        taskList.loadFromStorage();

        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning && sc.hasNextLine()) {
            String input = sc.nextLine();
            UI.printLine();

            try {
                Command command = Parser.parse(input, taskList);
                command.execute(taskList);

                if (command.isExit()) {
                    isRunning = false;
                }


            } catch (Exception e) {
                UI.showMessage("An unexpected error occurred: " + e.getMessage());
            }

            UI.printLine();
        }

        sc.close();
    }

}
