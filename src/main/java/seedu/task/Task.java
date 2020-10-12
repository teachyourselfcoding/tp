package seedu.task;
import seedu.Module;


import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Class that needs to be accessed by Schedule object.
 * Task is a parent class for two child classes, Lesson and Deadline
 * Lesson helps to keep track of tasks that have a frequency (weekly)
 * Deadline acts like normal Deadline like in IP.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType; // can be E or D for our project
    protected String moduleCode; // Module this task belongs to
    // I think moduleCode should be a string first. This is just to search for the Module in the ModuleManager
    protected int[] frequency; // frequency of the event. Weekly? Daily?
    protected String time; //Time of the event in HH:MM format
    protected LocalDate date;
    protected String notes; // TODO add it in the constructor. ***Low priority, settle later.

    //FIXME add new event and deadlines to this list whenever created

    /**
     * Creates new Task object, without frequency.
     * Error message will be printed if the module with the moduleCode doesn't exist.
     * @param description the task description
     * @param moduleCode the code of the module this task relates to
     * FIXME
     *  - add searching module based on moduleCode(String). Need to search through the ModuleManager
     *  - add error handling
     */
    public Task(String description, String moduleCode) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description) {
        this.description = description;
    }

    /**
     * Creates new Task object, with frequency.
     * Error message will be printed if the module with the moduleCode doesn't exist or the frequency is invalid.
     * @param description the task description
     * @param moduleCode the code of the module this task relates to
     * @param frequency the frequency of the task (int[2]), e.g. the event happens on Thursday every 2 week
     *                  = [4, 2] = [dayOfWeek, interval]. *** Lets assume that the interval is every week only for now
     */
    public Task(String description, String moduleCode, int[] frequency) {
        this.description = description;
        this.moduleCode = moduleCode;
        this.frequency = frequency;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return this.description;
    }

    public String getFullDescription() {
        return description;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getTaskType() {
        return taskType ;
    }

    public void maskAsDone(){
        this.isDone=true;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFrequency(int[] frequency) {
        this.frequency = frequency;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}
