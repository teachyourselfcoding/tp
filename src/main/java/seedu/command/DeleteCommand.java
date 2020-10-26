package seedu.command;

import seedu.ModuleManager;
import seedu.ScheduleManager;
import seedu.Storage;
import seedu.Ui;

import java.time.LocalDate;

/**
 * DeleteCommand is used to delete a task in the list.
 */
public class DeleteCommand extends Command {
    private int taskNum;
    private String description;
    private LocalDate date = null;

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
    public void execute(ScheduleManager scheduleManager, ModuleManager moduleManager, Ui ui) {
        description = description.strip();
        if(this.date == null){
            scheduleManager.deleteTask(description);
            moduleManager.deleteTask(description);
            Storage.getStorage().exportData(moduleManager);
        }
        else{
            scheduleManager.deleteTask(description, date);
            moduleManager.deleteTask(description, date);
            String commandString = "delete " + description + " /date " + this.date;
            Storage.getStorage().exportAdditionalData(commandString);
        }
    }
}
