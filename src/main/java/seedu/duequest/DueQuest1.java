package seedu.duequest;

import seedu.*;
import seedu.command.Command;
import seedu.task.TaskList;
import java.io.FileNotFoundException;

/**
 * Class just for codetesting purposes for our ScheduleManager
 * Handle the UI, Storage later. Try to make the other commands work first.
 */
public class DueQuest1 {
    ScheduleManager scheduleManager = new ScheduleManager();
    ModuleManager moduleManager = new ModuleManager();
    Ui ui = new Ui();

    public void run() {
        boolean isExit = false;

        while (!isExit) {
            String fullCommand = ui.readCommand();
            try {
                Command c = Parser.parse(fullCommand);
                c.execute(scheduleManager, moduleManager, ui);
                isExit = c.isExit();
            } catch (DueQuestException e) {
                ui.showError(e.getExceptionType());
            }
        }
        ui.byeMessage();
    }

    /**
     * The main function to run the whole DueQuest app
     */
    public static void main(String[] args) {
        new DueQuest1().run();
    }
}
