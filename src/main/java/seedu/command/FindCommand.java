package seedu.command;

import seedu.ModuleManager;
import seedu.ScheduleManager;
import seedu.Storage;
import seedu.task.Task;
import seedu.task.TaskList;
import seedu.Ui;

import java.util.ArrayList;

/**
 * Represents a Command for searching tasks that match the given keywords.
 */
public class FindCommand extends Command {
    private String keywords;
    public FindCommand(String keywords){
        this.keywords = keywords;
    }
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(ScheduleManager scheduleManager, ModuleManager moduleManager, Ui ui) {
    }
}
