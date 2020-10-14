package seedu.command;

import seedu.ModuleManager;
import seedu.ScheduleManager;
import seedu.Storage;
import seedu.Ui;
import seedu.task.TaskList;
import seedu.Module;

import java.util.Arrays;
import java.util.Objects;

/**
 * Represents a command for adding module.
 */
public class AddModuleCommand extends Command {
    private Module module;

    public AddModuleCommand(Module module) {
        this.module = module;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {

    }

    @Override
    public void execute(ScheduleManager scheduleManager, ModuleManager moduleManager, Ui ui) {
        if (moduleManager.checkIfModuleExist(this.module)) {
            Ui.print(this.module + " is already inside the Module Manager!");
        } else {
            moduleManager.addModule(this.module);
            Ui.print("Successfully added to Module Manager! Have fun suffering from " + this.module);
        }
        System.out.println("Here are your modules you are currently taking! " +
                "\n" + Arrays.toString(moduleManager.getListOfModuleCodes().toArray()));
        Ui.showDivider();
    }
}
