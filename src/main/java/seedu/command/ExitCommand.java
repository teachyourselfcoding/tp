package seedu.command;

import seedu.ModuleManager;
import seedu.ScheduleManager;
import seedu.Ui;

/**
 * Represents a Command for exiting the application
 */
public class ExitCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(ScheduleManager scheduleManager, ModuleManager moduleManager, Ui ui) {
    }
}
