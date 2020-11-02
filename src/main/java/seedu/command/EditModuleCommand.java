package seedu.command;

import seedu.ModuleManager;
import seedu.ScheduleManager;
import seedu.Ui;

import java.time.LocalDate;


/**
 * Commands to execute edit the module's information. This may be implemented by setter functions.
 * TODO add code
 */
public class EditModuleCommand extends editCommand{
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
    public EditModuleCommand(String moduleCode,  String task, LocalDate date, String property, String newProperty){
        this.moduleCode = moduleCode;
        this.description = task;
        this.date = date;
        this.type = property;
        this.newProperty = newProperty;
    }//edit module's property

    public EditModuleCommand(String moduleCode,  String task, LocalDate date, String property, int newFrequency){
        this.moduleCode = moduleCode;
        this.description = task;
        this.date = date;
        this.type = property;
        this.newFrequency = newFrequency;
    }//edit module's property

    public EditModuleCommand(String moduleCode,  String task, LocalDate date, String property, LocalDate newDate){
        this.moduleCode = moduleCode;
        this.description = task;
        this.date = date;
        this.type = property;
        this.newDate = newDate;
    }//edit module's property


    public EditModuleCommand(String moduleCode, String property, String newProperty){
        this.moduleCode = moduleCode;
        this.task = module;
        this.type = property;
        this.newProperty = newProperty;
    }



    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(ScheduleManager scheduleManager, ModuleManager moduleManager, Ui ui) {
//        System.out.println(moduleCode);
        if(description.equals("au")) {
            moduleManager.editModuleAu(moduleCode, newProperty);
        }
        else if(description.equals("staff")) {
            moduleManager.editModuleStaff(moduleCode, newProperty);
        }
        else if(description.equals("modulecode")) {
            moduleManager.editModuleCode(moduleCode, newProperty);
            scheduleManager.editModulecode(moduleCode,newProperty);
        } else{
                switch(type){
                    case "type":
                        //Fallthrough
                    case "description":
                        //Fallthrough
                    case "time":
                        scheduleManager.editTask(description, date, type, newProperty,moduleCode);
                        moduleManager.editTask(description, date , type, newProperty, moduleCode);

                        break;
                    case "frequency":
                        scheduleManager.editTask(description, date, type, newFrequency,moduleCode);
                        moduleManager.editTask(description, date , type, newFrequency, moduleCode);
                        break;
                    case "date":
                        scheduleManager.editTask(description, date, type, newDate,moduleCode);
                        moduleManager.editTask(description, date , type, newDate, moduleCode);
                        break;
                    default:
                        System.out.println("Invalid type");

            }
        }
        Ui.printSuccessfulEdit();
    }
}
