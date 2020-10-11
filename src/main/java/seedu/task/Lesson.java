package seedu.task;

import seedu.Module;

import java.time.LocalDateTime;

public class Lesson extends Task {
    public Lesson(String description, Module moduleCode, int[] frequency, LocalDateTime time) {
        super(description, moduleCode, frequency, time);
    }
}
