package seedu.duequest;

import seedu.*;
import seedu.Module;
import seedu.command.Command;
import seedu.task.Deadline;
import seedu.task.TaskList;
import java.io.FileNotFoundException;
import java.time.LocalDate;


/**
 * DueQuest class is the main class for running the DueQuest application
 */
public class DueQuest {
    private ScheduleManager scheduleManager = new ScheduleManager();
    private ModuleManager moduleManager = new ModuleManager();
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    static final String FILE_PATH = "data/duequest.txt";

    public DueQuest(String FILE_PATH) {
        ui = new Ui();
        ui.welcomeMessage();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
            tasks.listContents();
        } catch (FileNotFoundException e) {
            System.out.println("DueQuest couldn't find duequest.txt to load saved data from");
            tasks = new TaskList();
            storage.createSavedFile();
        }

    }

    /**
     * Used to keep the Duke programme running on repeat until a Exit command
     * is detected whereby the program will then exit
     */
    public void run() {
        boolean isExit = false;
        preloadData();
        testDisplayFunction();// used to test display function since add functionality not yet done

        while (!isExit) {
            String fullCommand = ui.readCommand();
            try {
                Command c = Parser.parse(fullCommand);
//                c.execute(tasks, ui, storage);
                c.execute(scheduleManager,moduleManager,ui);
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
    public static void main(String[] args) {
        new DueQuest(FILE_PATH).run();
    }

    /**
     * FOR TESTING PURPOSE ONLY.
     * use this to preload a set of data since our storage function not available yet.
     * you can use this for Edit, Delete, add...
     */
    public void preloadData(){
        moduleManager.addModule(new seedu.Module("CS2113"));
        moduleManager.addModule(new seedu.Module("EE2026"));
        moduleManager.addModule(new Module("CS1231"));
        try {
            moduleManager.addTaskToModule("CS2113",new Deadline("v1.0","2021-04-12"),LocalDate.parse("2021-04-12"));
            moduleManager.addTaskToModule("CS2113",new Deadline("v2.0","2021-04-07"),LocalDate.parse("2021-04-07"));
            moduleManager.addTaskToModule("CS2113",new Deadline("v2.1","2021-04-06"),LocalDate.parse("2021-04-06"));

        } catch (DueQuestException e) {
            ui.showError(e.getExceptionType());
        }

    }

    /**
     * FOR TESTING PURPOSE ONLY
     * Used to test that all the 4 display case works.
     */
    public void testDisplayFunction(){
        moduleManager.display("CS2113");
        moduleManager.display("CS2113", LocalDate.parse("2021-04-07"));
        scheduleManager.display(LocalDate.parse("2021-04-07"));
        scheduleManager.display(LocalDate.parse("2021-01-01"),LocalDate.parse("2021-06-01"));
    }
}
