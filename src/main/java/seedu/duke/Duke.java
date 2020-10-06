package seedu.duke;

import seedu.DukeException;
import seedu.DukeExceptionType;
import seedu.Parser;
import seedu.Storage;
import seedu.Ui;
import seedu.command.Command;
import seedu.task.TaskList;
import java.io.FileNotFoundException;


/**
 * Duke class is the main class for running the Duke application
 */
public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    static final String FILE_PATH = "data/duke.txt";

    public Duke(String FILE_PATH) {
        ui = new Ui();
        ui.welcomeMessage();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
            tasks.listContents();
        } catch (FileNotFoundException e) {
            System.out.println("Duke couldn't find duke.txt to load saved data from");
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

        while (!isExit) {
            String fullCommand = ui.readCommand();
            try {
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getExceptionType());
            }
        }
        ui.byeMessage();
    }

    /**
     * The main function to run the whole Duke app
     */
    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }
}
