package seedu;

import org.junit.jupiter.api.Test;
import seedu.exception.EmptyArgumentException;
import seedu.exception.InvalidDateException;
import seedu.exception.InvalidModuleCodeException;
import seedu.exception.InvalidTimeFormatException;
import seedu.exception.MissingDeadlineDescriptionException;
import seedu.exception.MissingDeadlineTimingDetailsException;
import seedu.exception.MissingEventDateAndTimeDetailsException;
import seedu.exception.MissingEventDescriptionException;
import seedu.exception.ModuleDoesNotExistException;
import seedu.exception.ModuleNotExistsException;
import seedu.exception.MissingModuleCodeOrInvalidModuleCodeException;
import seedu.exception.StartAndEndTimeSameException;
import seedu.exception.StartTimeAndEndTimeTooEarlyException;
import seedu.exception.StartTimeIsAfterEndTimeException;
import seedu.exception.WrongDateFormatException;
import seedu.exception.ModuleAlreadyExistsException;

import seedu.module.Module;

import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ModuleManagerTest {

    @Test
    void checkIfModuleExist_module_trueIfExist() throws ModuleAlreadyExistsException {
        ModuleManager moduleManager = new ModuleManager();
        Module module1 = new Module("CS2113");
        Module module2 = new Module("MA4270");
        try {
            moduleManager.addModule(module1);
            assertEquals(moduleManager.checkIfModuleExist(module1), true);
            assertEquals(moduleManager.checkIfModuleExist(module2), false);
        } catch (Exception e) {  // ignore exception because this is a valid case
        }
    }

    @Test
    void getTotalNumberOfModules_modules_numberOfModulesInModuleManager() throws ModuleAlreadyExistsException {
        ModuleManager moduleManager = new ModuleManager();
        Module module1 = new Module("CS2113");
        Module module2 = new Module("MA4270");
        Module module3 = new Module("CS2102");
        assertEquals(moduleManager.getNumberOfModules(), 0);
        try {
            moduleManager.addModule(module1);
            assertEquals(moduleManager.getNumberOfModules(), 1);
            moduleManager.addModule(module1);
            assertEquals(moduleManager.getNumberOfModules(), 1);
            moduleManager.addModule(module2);
            assertEquals(moduleManager.getNumberOfModules(), 2);
            moduleManager.addModule(module2);
            assertEquals(moduleManager.getNumberOfModules(), 2);
            moduleManager.addModule(module1);
            assertEquals(moduleManager.getNumberOfModules(), 2);
            moduleManager.addModule(module3);
            assertEquals(moduleManager.getNumberOfModules(), 3);
            moduleManager.addModule(module3);
            assertEquals(moduleManager.getNumberOfModules(), 3);
            moduleManager.addModule(module2);
            assertEquals(moduleManager.getNumberOfModules(), 3);
            moduleManager.addModule(module1);
            assertEquals(moduleManager.getNumberOfModules(), 3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void display_displayNonExistentModule_expectException() {
        ModuleManager mm = new ModuleManager();
        assertThrows(ModuleNotExistsException.class,() -> mm.display("CS2113"));
    }

    @Test
    void display_displayNonExistentModuleWithDate_expectException() {
        ModuleManager mm = new ModuleManager();
        assertThrows(ModuleNotExistsException.class,() -> mm.display("CS2113", LocalDate.now()));
    }

    @Test
    void deleteModuleTasks_deleteModuleTasks_returnsTrue() throws ModuleAlreadyExistsException,
            MissingEventDateAndTimeDetailsException, MissingEventDescriptionException,
            MissingModuleCodeOrInvalidModuleCodeException, InvalidDateException,
            StartTimeIsAfterEndTimeException, InvalidTimeFormatException, EmptyArgumentException,
            StartAndEndTimeSameException, StartTimeAndEndTimeTooEarlyException, WrongDateFormatException,
            ModuleDoesNotExistException, MissingDeadlineTimingDetailsException, MissingDeadlineDescriptionException,
            InvalidModuleCodeException, ModuleNotExistsException {
        ScheduleManager scheduleManager = new ScheduleManager();
        ModuleManager moduleManager = new ModuleManager();
        Ui ui = new Ui();
        moduleManager.addModule(new Module("CS2113"));
        moduleManager.addModule(new Module("CS2102"));
        String input1 = "event CS2113 final exam /at 2021-05-03 14:00 16:00 LT14";
        scheduleManager.addEvent(Parser.validateEvent(input1), moduleManager, ui);
        String input2 = "deadline CS2102 TP version 1 /by 2021-05-03";
        scheduleManager.addDeadline(Parser.validateDeadline(input2), moduleManager);
        moduleManager.deleteModuleTasks("TP version 1");
        assertEquals(0, moduleManager.getModule("CS2102").getListOfTasks().size());
    }

    @Test
    void deleteModuleTasks_deleteModuleTasksAtDate_returnsTrue() throws ModuleAlreadyExistsException,
            MissingEventDateAndTimeDetailsException, MissingEventDescriptionException,
            MissingModuleCodeOrInvalidModuleCodeException, InvalidDateException,
            StartTimeIsAfterEndTimeException, InvalidTimeFormatException, EmptyArgumentException,
            StartAndEndTimeSameException, StartTimeAndEndTimeTooEarlyException, WrongDateFormatException,
            ModuleDoesNotExistException, MissingDeadlineTimingDetailsException, MissingDeadlineDescriptionException,
            InvalidModuleCodeException, ModuleNotExistsException {
        ScheduleManager scheduleManager = new ScheduleManager();
        ModuleManager moduleManager = new ModuleManager();
        Ui ui = new Ui();
        moduleManager.addModule(new Module("CS2113"));
        moduleManager.addModule(new Module("CS2102"));
        String input1 = "event CS2113 final exam /at 2021-05-03 14:00 16:00 LT14";
        scheduleManager.addEvent(Parser.validateEvent(input1), moduleManager, ui);
        String input2 = "deadline CS2102 final exam /by 2021-05-03";
        scheduleManager.addDeadline(Parser.validateDeadline(input2), moduleManager);
        moduleManager.deleteModuleTasks("final exam", LocalDate
                .of(2021, 5, 3));
        assertEquals(0, moduleManager.getModule("CS2102").getListOfTasks().size());
        assertEquals(0, moduleManager.getModule("CS2113").getListOfTasks().size());
    }
    /*
    @Test
    void deleteModuleTasks_deleteTasksWithDate_returnsTrue() throws ModuleAlreadyExistsException,
            MissingEventDateAndTimeDetailsException, MissingEventDescriptionException,
            MissingModuleCodeOrInvalidModuleCodeException, InvalidDateException,
            StartTimeIsAfterEndTimeException, InvalidTimeFormatException, EmptyArgumentException,
            StartAndEndTimeSameException, StartTimeAndEndTimeTooEarlyException, WrongDateFormatException,
            ModuleDoesNotExistException, MissingDeadlineTimingDetailsException, MissingDeadlineDescriptionException,
            InvalidModuleCodeException, ModuleNotExistsException {
        ScheduleManager scheduleManager = new ScheduleManager();
        ModuleManager moduleManager = new ModuleManager();
        Ui ui = new Ui();
        moduleManager.addModule(new Module("CS2113"));
        moduleManager.addModule(new Module("CS2102"));
        String input1 = "event CS2113 final exam /at 2021-05-03 14:00 16:00 LT14";
        scheduleManager.addEvent(Parser.validateEvent(input1), moduleManager, ui);
        String input2 = "deadline CS2102 final exam /by 2021-05-03";
        scheduleManager.addDeadline(Parser.validateDeadline(input2), moduleManager);
        moduleManager.delete("CS2102", LocalDate.of(2021, 5, 3));
        moduleManager.delete("CS2113", LocalDate.of(2021, 5, 4));
        assertEquals(0, moduleManager.getModule("CS2102").getListOfTasks().size());
        assertEquals(1, moduleManager.getModule("CS2113").getListOfTasks().size());
    }
    
     */
}
