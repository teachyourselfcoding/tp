package seedu.task;
/**
 *Inherited from Task object, Todo class represents a task object with no
 * additional attributes
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
        super.taskType="T";
    }
    public ToDo(String description, boolean isDone) {
        super(description);
        super.taskType="T";
        super.isDone=isDone;
    }
    @Override
    public String getFullDescription() {
        return description;
    }
}
