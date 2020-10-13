package seedu.command;

import seedu.ModuleManager;
import seedu.ScheduleManager;
import seedu.Storage;
import seedu.task.TaskList;
import seedu.Ui;
/**
 * DeleteCommand is used to delete a task in the list.
 */
public class DeleteCommand extends Command {
    private int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.getSize() != 0) {
            taskList.deleteItemFromList(taskNum, storage);
        } else {
            System.out.println("The list is empty! Nothing to delete!");
        }
    }

    @Override
    public void execute(ScheduleManager scheduleManager, ModuleManager moduleManager, Ui ui) {

    }
}
