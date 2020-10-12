package seedu;

import seedu.task.Deadline;
import seedu.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to keep track of all the Module that the user is taking
 * Whenever an event or task is added into the ScheduleManager, we will add that task or event
 * into the ModuleManager as well according to the Module Code
 */
public class ModuleManager {
    ArrayList<Module> listOfModules;

    public ModuleManager() {
        this.listOfModules = new ArrayList<>();
    }

    public ModuleManager(ArrayList<Module> listOfModules) {
        this.listOfModules = listOfModules;
    }

    public void addModule(Module module){
        listOfModules.add(module);
    }

    public Module getModule(String moduleCode) throws DueQuestException{
        for (Module m:listOfModules) {
            if (m.getModuleCode()==moduleCode){
                return m;
            }
        }
        throw new DueQuestException(DueQuestExceptionType.MISSING_MODULE);
    }
    public int getModuleIndex(String moduleCode) throws DueQuestException{
        int indexCount =0;
        for (Module m:listOfModules) {
            if (m.getModuleCode()==moduleCode){
                return indexCount;
            }
            else{
                indexCount++;
            }
        }
        throw new DueQuestException(DueQuestExceptionType.MISSING_MODULE);
    }


     public void addTaskToModule(String moduleCode, Task task, LocalDate date) throws DueQuestException{

         try {
             listOfModules.get(getModuleIndex(moduleCode)).addTask(task);
             ScheduleManager.updateSchedule(date,task);
         } catch (DueQuestException e) {
             throw new DueQuestException(DueQuestExceptionType.MISSING_MODULE);
         }
     }

    // Display all the task in a module
    public void display(String moduleCode) {
        for (Module m:listOfModules) {
            if (m.getModuleCode().equals(moduleCode)) {
               ArrayList<Task> tasks = m.getListOfTasks();
               Ui.print("The list of task in " + moduleCode + ":");
               Ui.printListGenericType(tasks);
               Ui.showDivider();
               break;
            }
        }
    }
    // Display all the task in a module on a specific date
    public void display(String moduleCode, LocalDate date) {
        for (Module m:listOfModules) {
            if (m.getModuleCode().equals(moduleCode)) {
                ArrayList<Task> filteredTasks = new ArrayList<>();
                for (Task t: m.getListOfTasks()) {
                    if (t instanceof Deadline){
                        if (((Deadline) t).getDate().isEqual(date)){
                            filteredTasks.add(t);
                        }
                    }
                }
                Ui.print("The list of task in " + moduleCode +" on " +date.toString() +" :");
                Ui.printListGenericType(filteredTasks);
                Ui.showDivider();

            }
        }
    }
}
