package task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Task {
    protected String description;
    protected boolean isDone;
    protected List<String> tags;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        tags = new ArrayList<>();
    }

    public Task(String description, boolean isDone, List<String> tags) {
        this.description = description;
        this.isDone = isDone;
        this.tags = tags;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void mark() {
        isDone = true;
    }

    public void unMark() {
        isDone = false;
    }

    public String toString() {
        StringBuilder desc = new StringBuilder(String.format("[%s] %s ", getStatusIcon(), description));
        for(String tag : tags) {
            desc.append(String.format(tag) + " ");
        }
        return desc.toString();
    }

    public String store() {
        return "";
    }

    public String getDescription() {
        return this.description;
    }

    public void addTags(List<String> tags) {
        this.tags = Stream.concat(this.tags.stream(), tags.stream()).toList();
    }
}
