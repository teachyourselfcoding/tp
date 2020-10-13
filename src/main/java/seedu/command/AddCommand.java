package seedu.command;

import seedu.ModuleManager;
import seedu.ScheduleManager;
import seedu.Storage;
import seedu.task.Event;
import seedu.task.Lesson;
import seedu.task.Task;
import seedu.task.TaskList;
import seedu.Ui;

import java.io.IOException;
import java.time.LocalDate;

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

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.getList().add(tasks.getSize(), task);
        System.out.println("Got it. I've added this task: \n"
                    + tasks.getIndex(tasks.getSize() - 1).getTaskType() + "["
                    + tasks.getIndex(tasks.getSize() - 1).getStatusIcon() + "] "
                    + tasks.getIndex(tasks.getSize() - 1).getFullDescription()
                    + "\nNow you have "+ tasks.getSize() + " tasks in the list."
        );
        Ui.showDivider();
        try{
            storage.appendToFile(task);
        } catch (IOException e){
            System.out.println("Something went wrong: " + e.getMessage());
        }
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
     * TODO
     *  - make this work for adding deadlines and events too.
     *  - add the tasks to the ModuleManager as well.
     */
    @Override
    public void execute(ScheduleManager scheduleManager, ModuleManager moduleManager, Ui ui) {
        if (task.getClass() == Lesson.class) {
            scheduleManager.addLesson((Lesson) task); //add the lesson to the schedule manager
            System.out.println("Got it, added lesson to the schedule manager!");
            String moduleCode = task.getModuleCode();
            // if module code exist in the module manager, simply add the task into the module manager
            moduleManager.addTaskToModule(task, task.getModuleCode());
        } else if (task.getClass() == Event.class) {
            scheduleManager.addEvent((Event) task);
            // now check if the module code exist or is an empty string
            // look at the validateEvent method in Parser to understand that if the module code is invalid,
            // meaning the user didnt key in a module code for his event, the moduleCode will be an empty string.
            if (!task.getModuleCode().equals("")) {
                moduleManager.addTaskToModule(task, task.getModuleCode());
            }
        }
    }
}
