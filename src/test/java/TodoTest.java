package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TodoTest {
    @Test
    public void createTodo() {
        assertDoesNotThrow(() -> {
            Todo todo = new Todo("Test task");
        });
    }

    @Test
    public void loadTodo() {
        assertDoesNotThrow(() -> {
            Todo todo = new Todo("Test task", true);
            Todo todo2 = new Todo("Test task", false);
        });
    }

    @Test
    public void toStringTodo_unmarkedTodo() {
        Todo todo = new Todo("Test task", false);
        assertEquals("[T][ ] Test task", todo.toString());
    }

    @Test
    public void toStringTodo_markedTodo() {
        Todo todo = new Todo("Test task", true);
        assertEquals("[T][X] Test task", todo.toString());
    }


}
