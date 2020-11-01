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
    private String type;
    private String description;
    private LocalDate date = null;
    private String moduleCode = null;

    public DeleteCommand(String description) {
        this.description = description;
    }

    public DeleteCommand(String description, String type) {
        this.type = type;
        this.description = description;
    }

    public DeleteCommand(String description, LocalDate date) {
        this.description = description;
        this.date = date;
    }


    public DeleteCommand(String moduleCode, LocalDate date, String description) {
        this.description = description;
        this.moduleCode = moduleCode;
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
            if(moduleCode.equals(null)){
//                moduleManager.delete();
            }
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
