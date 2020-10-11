package seedu.command;

import seedu.ModuleManager;
import seedu.ScheduleManager;
import seedu.Storage;
import seedu.task.Task;
import seedu.task.TaskList;
import seedu.Ui;

import java.io.IOException;

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
        tasks.getList().add(tasks.getSize(),task);
        System.out.println("Got it. I've added this task: \n"
                    + tasks.getIndex(tasks.getSize() - 1).getTaskType() + "["
                    + tasks.getIndex(tasks.getSize() - 1).getStatusIcon() + "] "
                    + tasks.getIndex(tasks.getSize() - 1).getFullDescription()
                    + "\nNow you have "+ tasks.getSize()+" tasks in the list."
        );
        Ui.showDivider();
        try{
            storage.appendToFile(task);
        } catch (IOException e){
            System.out.println("Something went wrong: "+ e.getMessage());
        }
    }

    @Override
    public void execute(ScheduleManager scheduleManager, ModuleManager moduleManager, Ui ui) {

    }
}
