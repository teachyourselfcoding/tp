package seedu.command;

import seedu.ModuleManager;
import seedu.ScheduleManager;
import seedu.Storage;
import seedu.task.TaskList;
import seedu.Ui;

import java.io.IOException;
/**
 * DoneCommand is used when an task gets done and the user want to.
 * indicate the done status on the task.
 */
public class DoneCommand extends Command {
    private int taskNum;

    public DoneCommand(int taskNum){
        this.taskNum = taskNum;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(ScheduleManager scheduleManager, ModuleManager moduleManager, Ui ui) {
    }
}
