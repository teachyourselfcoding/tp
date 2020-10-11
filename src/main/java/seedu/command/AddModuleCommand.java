package seedu.command;

import seedu.Storage;
import seedu.Ui;
import seedu.task.TaskList;

/**
 * Represents a command for adding module.
 */
public class AddModuleCommand extends Command {
    @Override
    public boolean isExit() {
        return false;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {

    }
}
