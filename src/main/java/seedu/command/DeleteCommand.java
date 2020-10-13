package seedu.command;

import seedu.ModuleManager;
import seedu.ScheduleManager;
import seedu.Storage;
import seedu.task.TaskList;
import seedu.Ui;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * DeleteCommand is used to delete a task in the list.
 */
public class DeleteCommand extends Command {
    private int taskNum;
    String description;
    LocalDate date = null;

    public DeleteCommand(String description) {
        this.description = description;
    }

    public DeleteCommand(String description, LocalDate date) {
        this.description = description;
        this.date = date;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.getSize() != 0) {
            taskList.deleteItemFromList(taskNum, storage);
        } else {
            System.out.println("The list is empty! Nothing to delete!");
        }
    }

    @Override
    public void execute(ScheduleManager scheduleManager, ModuleManager moduleManager, Ui ui) {
        if(this.date == null){
            scheduleManager.deleteTask(description);
        }
        else{
            scheduleManager.deleteTask(description,date);
        }
    }
}
