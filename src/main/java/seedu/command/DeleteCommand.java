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
    private String type= " ";
    private String description = " ";
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

    public DeleteCommand(String moduleCode, String type, LocalDate date) {
        this.moduleCode = moduleCode;
        this.type = type;
        this.date = date;
    }

    public DeleteCommand(String moduleCode, LocalDate date, String description) {
        this.description = description.strip();
        this.moduleCode = moduleCode;
        this.date = date;
        this.type = "module";
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(ScheduleManager scheduleManager, ModuleManager moduleManager, Ui ui) {
        System.out.println(moduleCode);
        if(!type.equals("module")){
            if(this.date == null){                 //delete task with no date
                System.out.println("delete task with no date");
                scheduleManager.deleteTask(description);
                return;
            }
            System.out.println("delete task with date, with description");
            scheduleManager.deleteTask(description, date); //delete task with date, with description
            return;
        }
        if(this.date == null){                                                  //delete entire module
            moduleManager.delete(moduleCode);
            scheduleManager.deleteTask("module",moduleCode);
            return;
        }
        else{
            if(description.equals(" ")){                           //delete all task in module matching date
                System.out.println("delete all task in module matching date");
                scheduleManager.deleteTask(date, moduleCode);
                moduleManager.delete(moduleCode,date);
            }else {
                System.out.println("delete all task in module with matching date and description");
                moduleManager.delete(moduleCode, description, date);         //delete all task in module with matching date and description
                scheduleManager.deleteTask(moduleCode, description, date);
            }
        }
    }
}
