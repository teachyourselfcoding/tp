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
    private String type= null;
    private String description = null;
    private LocalDate date = null;
    private String moduleCode = null;

    public DeleteCommand(String description) {
        this.description = description;
    }

    public DeleteCommand(String moduleCode, String type) {
        this.moduleCode = moduleCode.strip();
        this.type = type;
    }

    public DeleteCommand(String description, LocalDate date) {
        this.description = description.strip();
        this.date = date;
    }


    public DeleteCommand(String moduleCode, LocalDate date, String description) {
        this.description = description.strip();
        this.moduleCode = moduleCode;
        this.date = date;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(ScheduleManager scheduleManager, ModuleManager moduleManager, Ui ui) {
        System.out.println(moduleCode);
        if(moduleCode.equals(null)){
            if(this.date == null){                 //delete task with no date
                System.out.println("delete task with no date");
                scheduleManager.deleteTask(description);
                return;
            }
            System.out.println("delete task with date, with description");
            scheduleManager.deleteTask(description, date);//delete task with date, with description
            return;
        }
        if(this.date == null){
            System.out.println("delete entire module");
            moduleManager.delete(moduleCode);
            scheduleManager.deleteTask("module",moduleCode);            //delete entire module
        }
        else{
            if(description.equals(null)){                           //delete all task in module matching date
                System.out.println("delete all task in module matching date");
                moduleManager.delete(moduleCode,date);
                scheduleManager.deleteTask(description, date);
                return;
            }System.out.println("delete all task in module with matching date and description");
            moduleManager.delete(moduleCode,description,date);         //delete all task in module with matching date and description
            scheduleManager.deleteTask(moduleCode, description, date);

        }
    }
}
