package seedu.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 * Inherited from Task object, Event class has additional attributes.
 * to store logistical information.
 */
public class Event extends Task {
    private String at;
    private String startTimeOfEvent;
    private String dateOfEvent;
    private String endTimeOfEvent;

    public Event(String description, String moduleCode, String at, String startTimeOfEvent,
                 String endTimeOfEvent, String dateOfEvent) {
        super(description, moduleCode);
        super.taskType = "E";
        this.at = at;
        this.startTimeOfEvent = startTimeOfEvent;
        this.endTimeOfEvent = endTimeOfEvent;
        this.dateOfEvent = dateOfEvent;
        //this.isDone = false;
    }

    /**
     * toString method for the Event object.
     *
     * @return a string representing an Event.
     */
    @Override
    public String toString() {
        return "[E] "
                + super.moduleCode
                + " "
                + super.description
                + " ("
                + this.startTimeOfEvent
                + " " + this.dateOfEvent
                + " at "
                + this.at
                + ")";
    }
    public LocalDate getDate() {
        return LocalDate.parse(dateOfEvent);
    }

    public String getAt() {
        return at;
    }

    public String getStartTimeOfEvent() {
        return startTimeOfEvent;
    }

    public LocalTime getStartTimeOfEventInLocalTime() {
        return LocalTime.parse(this.startTimeOfEvent);
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

    public LocalTime getEndTimeOfEventInLocalTime() {
        return LocalTime.parse(this.endTimeOfEvent);
    }

    public LocalDate getDateOfEventInLocalDate() {
        return convertStringToDate(dateOfEvent);
    }

    public LocalDate convertStringToDate(String stringDate) {
        LocalDate date = LocalDate.parse(stringDate.trim());
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(at, event.at) &&
                Objects.equals(startTimeOfEvent, event.startTimeOfEvent) &&
                Objects.equals(dateOfEvent, event.dateOfEvent) &&
                Objects.equals(endTimeOfEvent, event.endTimeOfEvent) &&
                Objects.equals(description, event.getDescription());
    }
}

