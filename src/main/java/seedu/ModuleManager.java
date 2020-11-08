package seedu;

import seedu.exception.ModuleAlreadyExistsException;
import seedu.exception.ModuleNotExistsException;
import seedu.module.Module;
import seedu.task.Deadline;
import seedu.task.Lesson;
import seedu.task.Task;

import java.lang.reflect.Array;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to keep track of all the Module that the user is taking.
 * Whenever an event or task is added into the ScheduleManager, we will add that task or event
 * into the ModuleManager as well according to the Module Code.
 */
public class ModuleManager {
    public ArrayList<Module> listOfModules;

    public ModuleManager() {
        this.listOfModules = new ArrayList<>();
    }

    public ModuleManager(ArrayList<Module> listOfModules) {
        this.listOfModules = listOfModules;
    }

    public int getNumberOfModules() {
        return this.listOfModules.size();
    }

    /**
     * Add new module to the app.
     * If the module with the same course code exists already, the message will be printed.
     * @param module new Module object to add
     */
    public void addModule(Module module) throws ModuleAlreadyExistsException {
        if (!this.checkIfModuleExist(module)) {
            this.listOfModules.add(module);
            Ui.print("added: " + module.getModuleCode().toString());
        } else {
            throw new ModuleAlreadyExistsException();
        }
    }

    /**
     * Check if the module with the same module code already exists in the ModuleManager.
     * This is to check before adding in any modules.
     * @param module module to be check if it already exist.
     * @return ture if the module exist, false if it dosen't.
     */
    public boolean checkIfModuleExist(Module module) {
        return this.getListOfModuleCodes().contains(module.getModuleCode().toUpperCase());
    }

    /**
     * Gets the module with specified module code from the list.
     * @param moduleCode module code in string
     * @return the Module object in list
     * @throws ModuleNotExistsException if nothing is found
     */
    public Module getModule(String moduleCode) throws ModuleNotExistsException {
        for (Module m: listOfModules) {
            if (m.getModuleCode().equals(moduleCode)) {  // '==' cannot be used.
                return m;
            }
        }
        throw new ModuleNotExistsException();
    }


    public void editTask(String description, LocalDate date, String type, String newProperty, String moduleCode) {
        boolean edit = false;
        for (Module m : listOfModules) {
            if (m.getModuleCode().equals(moduleCode)) {
                Ui.printModuleCode(m);
                for (Task task : m.getListOfTasks()) {
                    if (task.getDate().isEqual(date)) {
                        switch (type) {
                        case "description":
                            if (task.getDescription().equals(description)) {
                                task.setDescription(newProperty);
                                edit = true;
                            }
                            break;
                        case "tasktype":
                            if (task.getDescription().equals(description)) {
                                task.setTasktype(newProperty);
                                edit = true;
                            }
                            break;
                        case "module code":
                            if (task.getDescription().equals(description)) {
                                task.setModulecode(newProperty);
                                edit = true;
                            }
                            break;
                        case "time":
                            if (task.getDescription().equals(description)) {
                                task.setTime(newProperty);
                                edit = true;
                            }
                            break;
                        default:
                            Ui.printInvalidEditTypeMessage();
                        }
                    }
                }
            }
        }
        if (edit) {
            Ui.printModulePropertyEditedMessage();
        }
    }

    /**
     * TODO - add the description
     * Method do edit module to set a new frequency.
     * @param description description.
     * @param date date.
     * @param property property.
     * @param newFrequency new frequency.
     * @param moduleCode module code.
     */
    public void editTask(String description, LocalDate date, String property, int newFrequency, String moduleCode) {
        boolean edited = false;
        for (Module m: listOfModules) {
            if (m.getModuleCode().equals(moduleCode)) {
                for (Task task : m.getListOfTasks()) {
                    if (task.getDescription().equals(description)) {
                        task.setFrequency(newFrequency);
                        edited = true;
                    }
                }
            }
        }
        if (edited) {
            Ui.printModuleFrequencyEditedMessage();
        }
    }

    /**
     * TODO - add the description
     * Edit module to set new date.
     * @param description description.
     * @param date date.
     * @param property property.
     * @param newDate new date.
     * @param moduleCode module code.
     */
    public void editTask(String description, LocalDate date, String property, LocalDate newDate, String moduleCode) {
        boolean edited = false;
        for (Module m: listOfModules) {
            if (m.getModuleCode().equals(moduleCode)) {
                for (Task task : m.getListOfTasks()) {
                    if (task.getDescription().equals(description)) {
                        Task newTask = task;
                        newTask.setDate(newDate.toString()); //need to change later
                        m.addTask(newTask);
                        m.getListOfTasks().remove(task);
                        edited = true;
                    }
                }
            }
        }
        if (edited) {
            Ui.printModuleDateEditedMessage();
        } Ui.printModuleDateNotEditedMessage();
    }

    /**
     * Method to delete the entire module.
     * @param moduleCode module code of module to be deleted.
     */
    public void delete(String moduleCode) {
        for (Module m: listOfModules) {
            if (m.getModuleCode().equals(moduleCode)) {
                Ui.printModuleDeletedMessage();
            }
        }
        listOfModules.removeIf(m -> m.getModuleCode().equals(moduleCode));
    }

    /**
     * Method to delete a task in a module on a specific date.
     * @param moduleCode module code of the task.
     * @param description description of the task.
     * @param date date of the task.
     */
    public void delete(String moduleCode, String description, LocalDate date) {
        boolean deleted = false;
        for (Module m : listOfModules) {
            if (m.getModuleCode().equals(moduleCode)) {
                for (Task task : m.getListOfTasks()) {
                    if (task.getDate().equals(date)) {
                        if (task.getDescription().equals(description)) {
                            deleted = true;
                        }
                    }
                }
                if (deleted) {
                    Ui.printTaskDeletedMessage();
                    m.getListOfTasks().removeIf(task -> task.getDate() == date);
                    return;
                } Ui.printTaskNotDeletedMessage();

            }
        }
    }

    /**
     * Method to delete all the task of a module on a certain date.
     * @param moduleCode module code of the tasks.
     * @param date date chosen.
     */
    public void delete(String moduleCode, LocalDate date) {
        boolean edited = false;
        for(Module m: listOfModules){
            if(m.getModuleCode().equals(moduleCode)) {
                for (Task task : m.getListOfTasks()) {
                    if (task.getDate().equals(date)){
                        edited = true;
                    }
                }
            }
        }if(edited) {
            for (Module m : listOfModules) {
                if (m.getModuleCode().equals(moduleCode)) {
                    m.getListOfTasks().removeIf(t -> t.getDate().isEqual(date));
                }
            }
            Ui.printModuleTaskDateDeletedMessage();
            return;
        }Ui.printModuleTaskDateNotDeletedMessage();
    }

    public void deleteModuleTasks(String description, LocalDate date) {
        for (Module m : listOfModules) {

            ArrayList<Task> ls = new ArrayList<>();
            for (Task task: m.getListOfTasks()) {
                if (!task.getDescription().equals(description) && task.getDate().equals(date)) {
                    ls.add(task);
                }
            }
            m.setListOfTasks(ls);

        }
    }

    public void deleteModuleTasks(String description){
        for (Module m : listOfModules) {
            ArrayList<Task> ls = new ArrayList<>();
            for (Task task: m.getListOfTasks()) {
                if (!task.getDescription().equals(description)) {
                    ls.add(task);
                }
            }
            m.setListOfTasks(ls);
        }
    }

    /**
     * Display the tasks of a module.
     * @param moduleCode module code's string.
     * @throws ModuleNotExistsException when the module does not exist.
     */
    public void display(String moduleCode) throws ModuleNotExistsException {
        for (Module m: listOfModules) {
            if (m.getModuleCode().equals(moduleCode)) {
                ArrayList<Task> tasks = m.getListOfTasks();
                Ui.printModuleInformation(m); // print the module's information
                Ui.print("The list of task in " + moduleCode + ":");
                Ui.printListGenericType(tasks,"events,deadline or lessons");
                Ui.printSeparator();
                return;
            }
        }
        throw new ModuleNotExistsException();
    }

    /**
     * Method to display all the task in a module on a specific date.
     * @param moduleCode to have tasks to be displayed.
     * @param date date to be displayed.
     * @throws ModuleNotExistsException when module does not exist.
     */
    public void display(String moduleCode, LocalDate date) throws ModuleNotExistsException {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        ArrayList<Lesson> lessons = new ArrayList<>();
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        for (Module m : listOfModules) {
            if (m.getModuleCode().equals(moduleCode)) {

                for (Task t : m.getListOfTasks()) {
                    if (t instanceof Deadline) {
                        if (((Deadline) t).getDate().isEqual(date)) {
                            filteredTasks.add(t);
                        }
                    } else if (t instanceof Lesson) {
                        if (((Lesson) t).getLessonDayInDayOfWeek() == dayOfWeek) {
                            lessons.add((Lesson)t);
                        }
                    }
                }
                Ui.print(moduleCode + " - " + Ui.convertDateToString(date));
                Ui.print("Events & Deadlines :");
                Ui.printListGenericType(filteredTasks,"events and deadlines");
                Ui.print("Lessons :");
                Ui.printListGenericType(lessons,"lessons");
                Ui.printSeparator();
                return;
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
    }

    /**
     * Finds the module contains the task with the specified description.
     * @param description the description in string.
     * @return Module found.
     */
    private Module findModuleContainsTask(String description) {
        for (Module m: this.listOfModules) {
            for (Task t: m.getListOfTasks()) {
                if (t.getDescription().equals(description)) {
                    return m;
                }
            }
        }
        return null;
    }

    private Module findModuleContainsTask(String description, LocalDate date) {
        for (Module m: this.listOfModules) {
            for (Task t: m.getListOfTasks()) {
                if (t.getDescription().equals(description) && date.isEqual(t.getDate())) {
                    return m;
                }
            }
        }
        return null;
    }

    public void deleteTask(String description) {
        Module module = findModuleContainsTask(description);
        if (module != null) {
            module.deleteTask(description);
        }
    }

    public void deleteTask(String description, LocalDate date) {
        Module module = findModuleContainsTask(description, date);
        if (module != null) {
            module.deleteTask(description, date);
        }
    }

    public String export() {
        String export = "";
        for (int i = 0; i < this.listOfModules.size(); i++) {
            export = this.listOfModules.get(i).export() + '\n';
        }
        return export;
    }
}
