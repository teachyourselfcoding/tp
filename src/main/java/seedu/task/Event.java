package seedu.task;

import java.time.LocalDate;

/**
 *Inherited from Task object, Event class has additional attributes.
 * to store logistical information.
 * FIXME
 *  - What is the members attribute for?
 */
public class Event extends Task {
    protected String at;
    protected String startTimeOfEvent;
    protected String dateOfEvent;
    protected String endTimeOfEvent;
    //TODO add these extra attributes in constructors
    protected String members;

    public Event(String description, String moduleCode, String at, String startTimeOfEvent, String dateOfEvent) {
        super(description, moduleCode);
        super.taskType = "E";
        this.at = at;
        this.startTimeOfEvent = startTimeOfEvent;
        this.dateOfEvent = dateOfEvent;
        this.isDone = false;
    }

    public Event(String description, String moduleCode, String at, String startTimeOfEvent, String endTimeOfEvent, String dateOfEvent) {
        super(description, moduleCode);
        super.taskType = "E";
        this.at = at;
        this.startTimeOfEvent = startTimeOfEvent;
        this.endTimeOfEvent = endTimeOfEvent;
        this.dateOfEvent = dateOfEvent;
        this.isDone = false;
    }

    /**
     * toString method for the Event object.
     * @return a string representing an Event.
     * FIXME
     *  - may want to change how it is being represent.
     */
    @Override
    public String toString() {
        return "[E] " + super.moduleCode + " " + super.description + " (" + this.startTimeOfEvent + " " + this.dateOfEvent + " at " + this.at + ")";
    }

    @Override
    public String getFullDescription() {
        return description + " (at:" + at + ")";
    }

    public String getAt() {
        return at;
    }

    public String getStartTimeOfEvent() {
        return startTimeOfEvent;
    }

    public String getDateOfEvent() {
        return dateOfEvent;
    }

    public String getLocation() {
        return at;
    }

    public String getEndTimeOfEvent() {
        return endTimeOfEvent;
    }

    public LocalDate getDateOfEventInLocalDate() {
        return convertStringToDate(dateOfEvent);
    }

    public LocalDate convertStringToDate(String stringDate) {
        LocalDate date = LocalDate.parse(stringDate.trim());
        return date;
    }
}

