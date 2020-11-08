package seedu.module;

import seedu.task.Deadline;
import seedu.task.Event;
import seedu.task.Lesson;
import seedu.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;


/**
 * Class for Module.
 * <p>
 * Examples:
 * moduleCode e.g. ST2132.
 * title e.g. Statistics.
 * aUNumber e.g. 4.
 * teachingStaffs: e.g. ["Dr.Lim(lim@e.nus.sg)",]
 * listOfTasks. e.g.list of tasks in the module.
 * </p>
 */
public class Module {
    private String moduleCode;
    private String title;
    private int auNumber;
    private ArrayList<String> teachingStaffs = new ArrayList<>();
    private ArrayList<Task> listOfTasks;
    public ArrayList<Assessment> assessments = new ArrayList<>();

    /**
     * Constructor when I am adding a task that has a module code that has not exist yet.
     * TODO: check if moduleCode and other info is null.
     */
    public Module(String moduleCode, String title, int auNumber, ArrayList<String> teachingStaffs) {
        this.moduleCode = moduleCode;
        this.title = title;
        this.auNumber = auNumber;
        this.teachingStaffs = teachingStaffs;
        this.listOfTasks = new ArrayList<>();
    }

    public Module(String moduleCode) {
        this.moduleCode = moduleCode;
        this.listOfTasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        listOfTasks.add(task);
    }

    public ArrayList<Task> getListOfTasks() {
        return listOfTasks;

    }

    public String getModuleCode() {
        return this.moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public void setModuleAu(String auNumber) {
        this.auNumber = Integer.parseInt(auNumber);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        String information = "\nCourse: "
                + this.moduleCode
                + "\n"
                + "Title: "
                + this.title
                + "\n"
                + "AU: "
                + this.auNumber
                + "\n"
                + "Teaching Staffs: "
                + teachingStaffs.toString()
                + "\n" + "Assessment : \n"
                + printAssessment();
        return information;
    }

    public boolean equals(Module module) {
        return module.getModuleCode().equals(this.moduleCode);
    }

    /**
     * Deletes the task with the specified description from the list.
     *
     * @param description of the task to be deleted
     */
    public void deleteTask(String description) {
        for (int i = 0; i < this.listOfTasks.size(); i++) {
            Task t = this.listOfTasks.get(i);
            if (t.getDescription().equals(description)) {
                this.listOfTasks.remove(t);
            }
        }
    }

    public void deleteTask(String description, LocalDate date) {
        for (int i = 0; i < this.listOfTasks.size(); i++) {
            Task t = this.listOfTasks.get(i);
            if (t.getDescription().equals(description) && date.isEqual(t.getDate())) {
                this.listOfTasks.remove(t);
            }
        }
    }

    public void addAssessment(Assessment assessment) {
        this.assessments.add(assessment);
    }

    public String printAssessment() {
        String content = "";
        for (Assessment assessment : assessments) {
            content += assessment.toString() + '\n';
        }
        return content;
    }

    public ArrayList<Assessment> getAssessments() {
        return assessments;
    }

    /**.
     * TODO: task list?
     *
     * @return
     */
    public String export() {
        assert !this.moduleCode.isEmpty();
        String export = "module c/" + this.moduleCode;
        if (this.title != null) {
            export += " t/" + this.title;
        }
        if (this.auNumber >= 0) {
            export += " a/" + this.auNumber;
        }
        if (!this.teachingStaffs.isEmpty()) {
            for (int i = 0; i < this.teachingStaffs.size(); i++) {
                export += " s/" + this.teachingStaffs.get(i);
            }
        }
        export += '\n';

        for (int i = 0; i < this.listOfTasks.size(); i++) {
            Task currentTask = this.listOfTasks.get(i);
            if (currentTask instanceof Lesson) {
                export += "lesson "
                        + currentTask.getDescription()
                        + " " + this.moduleCode
                        + " /on "
                        + currentTask.getFrequency()
                        + " "
                        + ((Lesson) currentTask).getStartTime()
                        + " "
                        + ((Lesson) currentTask).getEndTime()
                        + '\n';
            } else if (currentTask instanceof Event) {
                export += "event " + this.moduleCode
                        + " "
                        + currentTask.getDescription()
                        + " /at " + ((Event) currentTask).getDateOfEvent()
                        + " "
                        + ((Event) currentTask).getStartTimeOfEvent()
                        + " " + ((Event) currentTask).getEndTimeOfEvent()
                        + " "
                        + ((Event) currentTask).getAt() + '\n';
            } else if (currentTask instanceof Deadline) {
                export += "deadline "
                        + this.moduleCode
                        + " " + currentTask.getDescription()
                        + " /by "
                        + ((Deadline) currentTask).getDeadline() + '\n';
            }
        }

        for (Assessment assessment : this.assessments) {
            export += "assessment "
                    + this.moduleCode
                    + " "
                    + assessment.title
                    + " "
                    + assessment.getFullScore()
                    + '\n';
        }

        return export;
    }
}
