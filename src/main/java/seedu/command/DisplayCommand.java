package seedu.command;

import seedu.ModuleManager;
import seedu.ScheduleManager;
import seedu.Ui;
import seedu.exception.ModuleNotExistsException;

import java.time.LocalDate;

public class DisplayCommand extends Command {
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate specificDate;
    private String moduleCode;
    private String displayOptions; // used to determine what kind of information to display

    public DisplayCommand() {}

    public DisplayCommand(String moduleCode){
        this.moduleCode = moduleCode.trim();
        displayOptions = "module";
    }

    public DisplayCommand(String moduleCode, LocalDate specificDate){
        this.moduleCode = moduleCode;
        this.specificDate = specificDate;
        displayOptions = "module with date";
    }

    public DisplayCommand(LocalDate specificDate) {
        this.specificDate = specificDate;
        displayOptions = "date";
    }

    public DisplayCommand(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        displayOptions = "date with range";
    }


    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(ScheduleManager scheduleManager, ModuleManager moduleManager, Ui ui) {
        try {
            switch (displayOptions) {
                case "module": {
                    moduleManager.display(moduleCode);
                    break;
                }
                case "module with date": {
                    moduleManager.display(moduleCode, specificDate);
                    break;
                }
                case "date": {
                    if (specificDate.equals(LocalDate.now())) {
                        scheduleManager.displayTodaySchedule();
                    } else {
                        scheduleManager.displayDate(specificDate);
                    }
                    break;
                }
                case "date with range": {
                    scheduleManager.display(startDate, endDate);
                    break;
                }
            }
        } catch (ModuleNotExistsException e) {
            Ui.printModuleNotExistMessage();
        }
    }
}
