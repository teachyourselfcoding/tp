package seedu;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to keep track of all the Module that the user is taking
 * Whenever an event or task is added into the ScheduleManager, we will add that task or event
 * into the ModuleManager as well according to the Module Code
 */
public class ModuleManager {
    List<Module> listOfModules;

    public ModuleManager() {
        this.listOfModules = new ArrayList<>();
    }

    public ModuleManager(List<Module> listOfModules) {
        this.listOfModules = listOfModules;
    }
}
