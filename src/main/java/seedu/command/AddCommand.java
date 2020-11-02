package seedu.command;

import seedu.ModuleManager;
import seedu.ScheduleManager;
import seedu.Storage;
import seedu.exception.ModuleDoesNotExistException;
import seedu.task.Task;
import seedu.task.Event;
import seedu.task.Lesson;
import seedu.task.Deadline;
import seedu.Ui;

/**
 * Represents a command for adding different subclass of tasks
 */
public class AddCommand  extends Command {
    private Task task;

    public AddCommand(Task task){
        this.task = task;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Method to add the lesson into the schedule manager.
     * Exceptions settle later, check if method works first.
     * Main function is to add the lesson into the semesterSchedule of the ScheduleManager.
     * Note that everthing in the Lesson Object if it is a Lesson object is still in String form.
     * Convert it to LocalTime if necessary.
     * @param scheduleManager scheduleManager that handles tasks.
     * @param moduleManager moduleManager that handles modules where we need to add task into module.
     * @param ui ui that helps with ui stuff.
     */
    @Override
    public void execute(ScheduleManager scheduleManager, ModuleManager moduleManager, Ui ui) throws ModuleDoesNotExistException {
        try {
            if (task instanceof Lesson) {
                scheduleManager.addLesson((Lesson) task, moduleManager, ui); //add the lesson to the schedule manager
                // if module code exist in the module manager, simply add the task into the module manager
            } else if (task instanceof Event) {
                scheduleManager.addEvent((Event) task, moduleManager, ui);
                //System.out.println(moduleManager.getListOfModuleCodes());
            } else if (task instanceof Deadline) {
                scheduleManager.addDeadline((Deadline) task, moduleManager);
                System.out.println("Got it, added deadline to Schedule Manager and Module Manager");
            } else {
                return;
            }
            String moduleCode = task.getModuleCode();
            Storage.getStorage().exportData(moduleManager, moduleCode);
            Ui.printSeparator();
        } catch (ModuleDoesNotExistException e) {
            Ui.printModuleDoesNotExistMessage();
        }
    }
}

