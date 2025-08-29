package storage;

import tasklist.TaskList;
import task.Task;
import ui.UI;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Storage {
    protected static Path path = Paths.get("data", "Bara.txt");

    public static void initialize() {
        File file = path.toFile();
        try {
            if (!file.exists()) {
                Path parentDir = path.getParent();
                if (parentDir != null && !Files.exists(parentDir)) {
                    Files.createDirectories(parentDir);
                }
                Files.createFile(path);
            }
        } catch (IOException e) {
            UI.showMessage(e.getMessage());
        }
    }

    public static void saveTasks(TaskList taskList) {
        StringBuilder data = new StringBuilder();
        for (Task t : taskList.getAllTasks()) {
            data.append(t.store());
        }
        try {
            Files.writeString(path, data, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            UI.showMessage(e.getMessage());
        }
    }

    public static Path getFilePath() {
        return path;
    }
}
