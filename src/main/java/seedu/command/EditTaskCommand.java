package seedu.command;

import seedu.Storage;
import seedu.Ui;
import seedu.task.TaskList;

/**
 * Commands to execute edit the task's information. This may be implemented by setter functions.
 * TODO add code
 */
public class EditTaskCommand extends Command{
    @Override
    public boolean isExit() {
        return false;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {

    }
}
