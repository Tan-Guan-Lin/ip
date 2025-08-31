package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import parser.DateTimeParser;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = parseDateTimeWithHandling(from);
        this.to = parseDateTimeWithHandling(to);
    }

    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.from = parseDateTimeWithHandling(from);
        this.to = parseDateTimeWithHandling(to);
    }

    private LocalDateTime parseDateTimeWithHandling(String dateTimeString) {
        try {
            return DateTimeParser.parseDateTime(dateTimeString);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format for event. Use: yyyy-MM-dd or yyyy-MM-dd HH:mm");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + DateTimeParser.formatDisplay(from) +
                " to: " + DateTimeParser.formatDisplay(to) + ")";
    }

    @Override
    public String store() {
        return "E | " + (isDone ? 1 : 0) + " | " + description + " | " + from + " | " + to + "\n";
    }
}