package seedu.command;

import seedu.ModuleManager;
import seedu.ScheduleManager;
import seedu.Storage;
import seedu.Ui;

import java.time.LocalDate;


/**
 * Commands to execute edit the module's information. This may be implemented by setter functions.
 */
public class EditModuleCommand extends EditCommand {
    String moduleCode;
    String description;
    LocalDate date = null;
    LocalDate newDate = null;
    String task;
    String type;
    String module = "module";
    String newProperty;
    int newFrequency;


    //edit module's tasks
    public EditModuleCommand(String moduleCode, String task, LocalDate date, String property, String newProperty) {
        this.moduleCode = moduleCode;
        this.description = task;
        this.date = date;
        this.type = property;
        this.newProperty = newProperty;
    } //edit module's property

    public EditModuleCommand(String moduleCode, String task, LocalDate date, String property, int newFrequency) {
        this.moduleCode = moduleCode;
        this.description = task;
        this.date = date;
        this.type = property;
        this.newFrequency = newFrequency;
    } //edit module's property

    public EditModuleCommand(String moduleCode, String task, LocalDate date, String property, LocalDate newDate) {
        this.moduleCode = moduleCode;
        this.description = task;
        this.date = date;
        this.type = property;
        this.newDate = newDate;
    } //edit module's property


    public EditModuleCommand(String moduleCode, String property, String newProperty) {
        this.moduleCode = moduleCode;
        this.task = module;
        this.description = property;
        this.newProperty = newProperty;
    }


    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(ScheduleManager scheduleManager, ModuleManager moduleManager, Ui ui) {
            switch (type) {
            case "type":
                //Fallthrough
            case "description":
                //Fallthrough
            case "time":
                scheduleManager.editTask(description, date, type, newProperty, moduleCode);
                moduleManager.editTask(description, date, type, newProperty, moduleCode);
                break;
            case "frequency":
                scheduleManager.editTask(description, date, type, newFrequency, moduleCode);
                moduleManager.editTask(description, date, type, newFrequency, moduleCode);
                break;
            case "date":
                scheduleManager.editTask(description, date, type, newDate, moduleCode);
                moduleManager.editTask(description, date, type, newDate, moduleCode);
                break;
            default:
                Ui.printInvalidEditTypeMessage();
            }
        Ui.printSuccessfulEdit();
        Storage.getStorage().exportData(moduleManager, moduleCode);
    }
}
