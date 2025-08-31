package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import parser.DateTimeParser;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        this.by = parseDateTimeWithHandling(by);
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = parseDateTimeWithHandling(by);
    }

    private LocalDateTime parseDateTimeWithHandling(String dateTimeString) {
        try {
            return DateTimeParser.parseDateTime(dateTimeString);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format for deadline. Use: yyyy-MM-dd or yyyy-MM-dd HH:mm");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeParser.formatDisplay(by) + ")";
    }

    @Override
    public String store() {
        return "D | " + (isDone ? 1 : 0) + " | " + description + " | " + by + "\n";
    }
}