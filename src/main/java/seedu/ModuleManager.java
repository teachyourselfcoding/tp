package seedu;


import seedu.exception.ModuleNotExistsException;
import seedu.task.Deadline;
import seedu.task.Task;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to keep track of all the Module that the user is taking.
 * Whenever an event or task is added into the ScheduleManager, we will add that task or event
 * into the ModuleManager as well according to the Module Code.
 */
public class ModuleManager {
    ArrayList<Module> listOfModules;

    public ModuleManager() {
        this.listOfModules = new ArrayList<>();
    }

    public ModuleManager(ArrayList<Module> listOfModules) {
        this.listOfModules = listOfModules;
    }

    /**
     * Add new module to the app.
     * If the module with the same course code exists already, the message will be printed.
     * @param module new Module object to add
     */
    public void addModule(Module module) {
        if (!this.checkIfModuleExist(module)) {
            this.listOfModules.add(module);
        } else {
            Ui.printModuleAlreadyExistMessage(module.getModuleCode());
        }
    }

    /**
     * Check if the module with the same module code already exists in the ModuleManager.
     * This is to check before adding in any modules.
     * @param module module to be check if it already exist.
     * @return ture if the module exist, false if it dosen't.
     */
    public boolean checkIfModuleExist(Module module) {
        return this.getListOfModuleCodes().contains(module.getModuleCode());
    }

    /**
     * Gets the module with specified module code from the list.
     * @param moduleCode module code in string
     * @return the Module object in list
     * @throws ModuleNotExistsException if nothing is found
     */
    public Module getModule(String moduleCode) throws ModuleNotExistsException{
        for (Module m: listOfModules) {
            if (m.getModuleCode() == moduleCode){
                return m;
            }
        }
        throw new ModuleNotExistsException();
    }

    /**
     * Gives the index of the module in the list.
     * @param moduleCode module code in string
     * @return the Module object in list
     * @throws ModuleNotExistsException if nothing is found
     */
    public int getModuleIndex(String moduleCode) throws ModuleNotExistsException{
        int indexCount = 0;

        for (Module m: listOfModules) {
            if (m.getModuleCode() == moduleCode){
                return indexCount;
            }
            else{
                indexCount++;
            }
        }
        throw new ModuleNotExistsException();
    }

    public int getTotalNumberOfModules() {
        return this.listOfModules.size();
    }

    /**
     *
     * @param moduleCode
     * @param task
     * @param date
     * @throws DueQuestException
     */
    public void addTaskToModule(String moduleCode, Task task, LocalDate date) throws DueQuestException{
        try {
            listOfModules.get(getModuleIndex(moduleCode)).addTask(task);
            ScheduleManager.updateSchedule(date,task);
        } catch (ModuleNotExistsException e) {
            Ui.printModuleNotExistMessage(moduleCode);
        }
    }

    /**
     * Display the tasks of a module.
     * @param moduleCode module code's string
     * @throws ModuleNotExistsException
     */
    public void display(String moduleCode) throws ModuleNotExistsException{
        for (Module m: listOfModules) {
            if (m.getModuleCode().equals(moduleCode)) {
                ArrayList<Task> tasks = m.getListOfTasks();
                Ui.print("The list of task in " + moduleCode + ":");
                Ui.printListGenericType(tasks);
                Ui.showDivider();
                break;
            }
        }
        throw new ModuleNotExistsException();
    }
    // Display all the task in a module on a specific date
    public void display(String moduleCode, LocalDate date) throws ModuleNotExistsException{
        for (Module m : listOfModules) {
            if (m.getModuleCode().equals(moduleCode)) {
                ArrayList<Task> filteredTasks = new ArrayList<>();
                for (Task t : m.getListOfTasks()) {
                    if (t instanceof Deadline) {
                        if (((Deadline) t).getDate().isEqual(date)) {
                            filteredTasks.add(t);
                        }
                    }
                }
                Ui.print("The list of task in " + moduleCode + " on " + date.toString() + " :");
                Ui.printListGenericType(filteredTasks);
                Ui.showDivider();
            }
        }
        throw new ModuleNotExistsException();
    }

    public ArrayList<Module> getListOfModules() {
        return listOfModules;
    }

    /**
     * Method to get a list of Module Codes in String form.
     * @return the list of module codes.
     */
    public List<String> getListOfModuleCodes() {
        List<String> listOfModuleCodes = new ArrayList<>();
        for (Module module: listOfModules) {
            listOfModuleCodes.add(module.getModuleCode());
        }
        return listOfModuleCodes;
    }

    /**
     * Method to add a task to the module inside the list of the module manager.
     * This is executed in the AddCommand method, when a task is added to both.
     * the module manager and schedule manager.
     * @param task task to be added into the module manager.
     * @param moduleCode this is the modulecode of the task. Remember, moduleCode is an attribute of task.
     */
    public void addTaskToModule(Task task, String moduleCode) {
        for (int i = 0; i < this.listOfModules.size(); i++) {
            if (this.listOfModules.get(i).getModuleCode().equals(moduleCode)) {
                this.listOfModules.get(i).addTask(task);
                return;
            }
        }
        // if we reach the end of the for loop, it means that the moduleCode does not exist
        // hence, we create this module first, add the task to it and
        // then add it to the module manager
        Module module = new Module(moduleCode);
        module.addTask(task);
        this.listOfModules.add(module);
    }
}
