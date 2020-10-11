package seedu.task;
import seedu.Module;

import java.util.ArrayList;

/**
 * Class that needs to be accessed by Schedule object.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;
    protected Module moduleBelongTo;
    protected int[] frequency;
    protected String notes; // TODO add it in the constructor.

    //FIXME add new event and deadlines to this list whenever created

    /**
     * Creates new Task object, without frequency.
     * Error message will be printed if the module with the moduleCode doesn't exist.
     * @param description the task description
     * @param moduleCode the code of the module this task relates to
     * FIXME
     *  - add searching module based on moduleCode(String)
     *  - add error handling
     */
    public Task(String description, String moduleCode) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates new Task object, with frequency.
     * Error message will be printed if the module with the moduleCode doesn't exist or the frequency is invalid.
     * @param description the task description
     * @param moduleCode the code of the module this task relates to
     * @param frequency the frequency of the task (int[2]), e.g. the event happens on Thursday every 2 week
     *                  = [4, 2] = [dayOfWeek, interval]
     * FIXME
     *  - add searching module based on moduleCode(String)
     *  - add error handling
     */
    public Task(String description, Module moduleCode, int[] frequency) {
        this.description = description;
        this.moduleBelongTo = moduleCode;
        this.frequency = frequency;
    }

    public Task() {
        this.description="";
        this.isDone=false;
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

    public void setModuleBelongTo(Module moduleBelongTo) {
        this.moduleBelongTo = moduleBelongTo;
    }

    public void setFrequency(int[] frequency) {
        this.frequency = frequency;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
