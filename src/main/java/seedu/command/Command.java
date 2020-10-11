package seedu.command;

import seedu.ModuleManager;
import seedu.ScheduleManager;
import seedu.Storage;
import seedu.task.TaskList;
import seedu.Ui;


/**
 * the base for all other Command classes to inherit from
 */
public abstract class Command {

    private boolean isExit;
    public abstract boolean isExit();
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * Use this method to execute the commands first for our team project
     */
    public void execute(ScheduleManager scheduleManager, ModuleManager moduleManager, Ui ui) {
    }

}
