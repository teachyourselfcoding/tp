package seedu.task;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Inherited from Task object, Deadline class has additional attributes.
 * to store deadline timing.
 */
public class Deadline extends Task {
    public Deadline(String description, String by) {
        super(description);
        super.taskType = "D";
        this.by = by;
    }

    public Deadline(String moduleCode, String description, String by) {
        super(description, moduleCode);
        this.by = by;
    }

    public String getDeadline() {
        return by;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " - " + super.moduleCode + " "  + " (by: " + by + ")";
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Deadline deadline = (Deadline) o;
        return Objects.equals(deadline.getDate(), this.getDate())
                && Objects.equals(deadline.getModuleCode(), super.getModuleCode())
                && Objects.equals(deadline.getDescription(), super.getDescription());
    }
}
