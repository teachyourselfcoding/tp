package seedu.duequest;

import org.junit.jupiter.api.Test;
import seedu.DueQuestException;
import seedu.Module;
import seedu.Parser;
import seedu.ScheduleManager;
import seedu.command.Command;
import seedu.exception.*;

import java.time.LocalDate;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class DueQuestTest {
    @Test
    public void sampleTest() {
        assertTrue(true);
    }

    @Test
    public void addingLesson_addAValidLesson_true() throws ModuleNotExistsException,
            ModuleAlreadyExistsException {
        DueQuest dq = new DueQuest();
        String fullCommand = "lesson online lecture CS2113 /on 5 16:00 18:00";
        LocalDate date = LocalDate.of(2021, 1, 15);
        dq.getModuleManager().addModule(new Module("CS2113"));
        try {
            Command c =  Parser.parse(fullCommand);
            c.execute(dq.getScheduleManager(), dq.getModuleManager(), dq.getUi());
        } catch (DueQuestException e) {

        } catch (NullPointerException e) {  // this has been handled within parser

        } catch (ModuleDoesNotExistException e) {

        }
        assertEquals(dq.getScheduleManager().getNumberOfTaskInADay(date), 1);
        assertEquals(dq.getModuleManager().getNumberOfModules(), 1);
        Module module = dq.getModuleManager().getModule("CS2113");
        assertEquals(1, module.getListOfTasks().size());
    }

    @Test
    void addingEvent_addAValidEvent_true() throws ModuleAlreadyExistsException {
        DueQuest dq = new DueQuest();
        String fullCommand = "event CS2113 final exam /at 2021-05-03 14:00 16:00 LT14";
        LocalDate date = LocalDate.of(2021, 5, 3);
        dq.getModuleManager().addModule(new Module("CS2113"));
        try {
            Command c =  Parser.parse(fullCommand);
            c.execute(dq.getScheduleManager(), dq.getModuleManager(), dq.getUi());
        } catch (DueQuestException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
        } catch (ModuleDoesNotExistException e) {
            e.printStackTrace();
        }
    }

    @Test
    void addingDeadline_addAValidDeadline_true() throws ModuleAlreadyExistsException {
        DueQuest dq = new DueQuest();
        String fullCommand = "deadline CS2113 TP version 1 /by 2021-04-04";
        LocalDate date = LocalDate.of(2021, 4, 4);
        dq.getModuleManager().addModule(new Module("CS2113"));
        try {
            Command c =  Parser.parse(fullCommand);
            c.execute(dq.getScheduleManager(), dq.getModuleManager(), dq.getUi());
        } catch (DueQuestException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
        } catch (ModuleDoesNotExistException e) {
            e.printStackTrace();
        }
        assertEquals(dq.getScheduleManager().getNumberOfTaskInADay(date), 1);
    }
}
