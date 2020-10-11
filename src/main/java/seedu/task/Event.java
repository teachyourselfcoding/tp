package seedu.task;

/**
 *Inherited from Task object, Event class has additional attributes
 * to store logistical information
 */
public class Event extends Task {
    protected String at;
    //TODO add these extra attributes in constructors
    protected String location;
    protected String members;

    public Event(String description, String at) {
        super(description);
        super.taskType="E";
        this.at = at;
    }
    public Event(String description, String at,Boolean isDone) {
        super(description);
        super.taskType="E";
        this.at = at;
        super.isDone=isDone;
    }

    @Override
    public String getFullDescription() {
        return description + " (at:" + at + ")";
    }
    public String getLocation(){
        return at;
    }
}
