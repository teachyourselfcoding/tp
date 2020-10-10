package seedu.command;

import seedu.Storage;
import seedu.task.TaskList;
import seedu.Ui;

/**
 * Represents a Command for listing all the tasks
 */
public class ListCommand extends Command {
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
    taskList.listContents();
    }
}
