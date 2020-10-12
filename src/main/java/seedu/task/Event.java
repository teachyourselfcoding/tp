package seedu.task;

/**
 *Inherited from Task object, Event class has additional attributes.
 * to store logistical information.
 * FIXME
 *  - What is the members attribute for?
 */
public class Event extends Task {
    protected String at; //at is the location
    protected String timeOfEvent;
    protected String dateOfEvent;
    //TODO add these extra attributes in constructors
    protected String members;

    public Event(String description, String moduleCode, String at, String timeOfEvent, String dateOfEvent) {
        super(description, moduleCode);
        super.taskType = "E";
        this.at = at;
        this.timeOfEvent = timeOfEvent;
        this.dateOfEvent = dateOfEvent;
        this.isDone = false;
    }

    /**
     * toString method for the Event object.
     * @return a string representing an Event.
     * FIXME
     *  - may want to change how it is being representd.
     */
    @Override
    public String toString() {
        return super.moduleCode + super.description + " (" + this.timeOfEvent + " " + this.dateOfEvent + " at " + this.at + ")";
    }

    @Override
    public String getFullDescription() {
        return description + " (at:" + at + ")";
    }

    public String getAt() {
        return at;
    }

    public String getTimeOfEvent() {
        return timeOfEvent;
    }

    public String getDateOfEvent() {
        return dateOfEvent;
    }
}
