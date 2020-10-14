package seedu.task;

import java.time.LocalDate;

/**
 *Inherited from Task object, Deadline class has additional attributes.
 * to store deadline timing.
 */
public class Deadline extends Task {


    public Deadline(String description, String by) {
        super(description);
        super.taskType = "D";
        this.by = by;
    }

    public Deadline(String description, String by,Boolean isDone) {
        super(description);
        super.taskType = "D";
        this.by = by;
        super.isDone = isDone;
    }


    public String getDeadline() {
        return by;
    }


    public String getFullDescription() {
        return description + " (by:" + by + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public String getTimingInfo() {
        return by;
    }

    public LocalDate getDate() {
        return convertStringToDate(by);
    }

    public LocalDate convertStringToDate(String stringDate) {
        LocalDate date = LocalDate.parse(stringDate.trim());
        return date;
    }
}
