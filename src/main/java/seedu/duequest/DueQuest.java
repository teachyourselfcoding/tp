package seedu.duequest;

import seedu.DueQuestException;
import seedu.ModuleManager;
import seedu.Parser;
import seedu.ScheduleManager;
import seedu.Storage;
import seedu.Ui;
import seedu.command.Command;
import seedu.exception.EmptyArgumentException;
import seedu.exception.InvalidScoreException;
import seedu.exception.ModuleDoesNotExistException;


/**
 * DueQuest class is the main class for running the DueQuest application.
 */
public class DueQuest {
    private Ui ui;
    private ScheduleManager scheduleManager;
    private ModuleManager moduleManager;
    static final String FILE_PATH = "data/duequest.txt";
    static Storage storage;

    /**
     * The main function to run the whole Duke app.
     */
    public static void main(String[] args) {
        //assert false : "dummy assertion set to fail";
        DueQuest dq = new DueQuest();
        try {
            storage = Storage.setUpStorage(args[0]);
        } catch (IndexOutOfBoundsException e) {
            storage = Storage.setUpStorage(null);
        }
        storage.loadData(dq.scheduleManager, dq.moduleManager, dq.ui);
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
     * The run method to execute the commands FOR OUR TP!!!!
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
            } catch (ModuleDoesNotExistException e) {
            } catch (EmptyArgumentException e) {
                Ui.printEmptyArgumentMessage();
            } catch (InvalidScoreException e) {
                Ui.printInvalidScoreErrorMessage();
            }
        }
        ui.byeMessage();
    }

    public Ui getUi() {
        return ui;
    }

    public ScheduleManager getScheduleManager() {
        return scheduleManager;
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }
}
