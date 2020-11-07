package seedu.command;

import seedu.ModuleManager;
import seedu.ScheduleManager;
import seedu.Ui;
import seedu.exception.EmptyArgumentException;
import seedu.exception.InvalidScoreException;
import seedu.exception.ModuleDoesNotExistException;

/**
 * The base for all other Command classes to inherit from.
 */
public abstract class Command {
    public abstract boolean isExit();

    /**
     * Use this method to execute the commands first for our team project.
     */
    public abstract void execute(ScheduleManager scheduleManager, ModuleManager moduleManager, Ui ui) throws ModuleDoesNotExistException, EmptyArgumentException, InvalidScoreException;
}
