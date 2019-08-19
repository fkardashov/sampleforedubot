package simplebot.utils.task;

import lombok.Data;
import lombok.Getter;

@Getter
public class Task {
    private String task;

    public Task(String task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return task;
    }
}
