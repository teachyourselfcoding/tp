package seedu.command;

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

}
