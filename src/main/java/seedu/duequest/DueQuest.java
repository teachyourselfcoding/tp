package seedu.duequest;

import seedu.DueQuestException;
import seedu.ModuleManager;
import seedu.Parser;
import seedu.ScheduleManager;
import seedu.Storage;
import seedu.Ui;
import seedu.command.Command;
import seedu.task.TaskList;


/**
 * DueQuest class is the main class for running the DueQuest application.
 */
public class DueQuest {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private ScheduleManager scheduleManager;
    private ModuleManager moduleManager;
    static final String FILE_PATH = "data/duequest.txt";

    /**
     * The main function to run the whole Duke app.
     */
    public static void main(String[] args) {
        DueQuest dq = new DueQuest();
        dq.run();
    }

    /**
     * Constructor
     */
    public DueQuest() {
        this.ui = new Ui();
        this.ui.welcomeMessage();
        this.scheduleManager = new ScheduleManager();
        this.moduleManager = new ModuleManager();
    }

    /**
     * The run method to execute the commands and halts the program when exit command is read.
     */
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
            } catch (NullPointerException e) {  // this has been handled within parser
            }
        }
        ui.byeMessage();
    }
}
