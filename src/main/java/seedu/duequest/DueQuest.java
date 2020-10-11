package seedu.duequest;

import seedu.*;
import seedu.command.Command;
import seedu.task.TaskList;
import java.io.FileNotFoundException;

/**
 * DueQuest class is the main class for running the DueQuest application
 */
public class DueQuest {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private ScheduleManager scheduleManager;
    private ModuleManager moduleManager;
    static final String FILE_PATH = "data/duequest.txt";

    /**
     * Constructor
     * for testing out purposes before saving stuff into a txt file.
     */
    public DueQuest() {
        this.ui = new Ui();
        this.ui.welcomeMessage();
        this.scheduleManager = new ScheduleManager();
        this.moduleManager = new ModuleManager();
    }

    public DueQuest(String FILE_PATH) {
        ui = new Ui();
        ui.welcomeMessage();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
            tasks.listContents();
        } catch (FileNotFoundException e) {
            System.out.println("DueQuest couldn't find duke.txt to load saved data from");
            tasks = new TaskList();
            storage.createSavedFile();
        }
    }

    /**
     * Used to keep the Duke programme running on repeat until a Exit command
     * is detected whereby the program will then exit
     * THE RUN METHOD FOR THE IP!!!
     */
    public void run() {
        boolean isExit = false;
        System.out.println("RUNNNN");
        while (!isExit) {
            String fullCommand = ui.readCommand();
            try {
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DueQuestException e) {
                ui.showError(e.getExceptionType());
            }
        }
        ui.byeMessage();
    }

    /**
     * The run method to execute the commands FOR OUR TP!!!!
     */
    public void runn() {
        System.out.println("RUNNNNNNN");
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
     * The main function to run the whole Duke app
     */
    public static void main(String[] args) throws DueQuestException {
        DueQuest dq = new DueQuest();
        dq.runn();
        //new DueQuest(FILE_PATH).run();
    }
}
