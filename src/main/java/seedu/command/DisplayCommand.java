package seedu.command;

import seedu.ModuleManager;
import seedu.ScheduleManager;
import seedu.Ui;
import seedu.exception.InvalidDateException;
import seedu.exception.InvalidStartEndDateException;
import seedu.exception.ModuleNotExistsException;
import seedu.exception.StartAndEndTimeSameException;

import java.time.LocalDate;

/**
 * DisplayCommand class is used to help display the tasks in the ScheduleManager and ModuleManager.
 */
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
    public void execute(ScheduleManager scheduleManager, ModuleManager moduleManager, Ui ui)  {
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
        } catch (InvalidStartEndDateException e){
            Ui.printInvalidStartEndDate();
        } catch (InvalidDateException e){
            Ui.printInvalidDateMessage();
        } catch (StartAndEndTimeSameException e){
            Ui.printStartAndEndTimeCannotBeTheSameMessage();
        }
    }
}
