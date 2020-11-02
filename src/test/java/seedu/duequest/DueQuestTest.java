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
    void addModule_addAModuleThatALreadyExists_expectModuleAlreadyExistsException() {
        DueQuest dq = new DueQuest();
        String addModuleInput1 = "module c/CS2113 a/4 s/Dr.Akshay s/ChengChen";
        try {
            Command c = Parser.parse(addModuleInput1);
            c.execute(dq.getScheduleManager(), dq.getModuleManager(), dq.getUi());
        } catch (DueQuestException e) {
        } catch (NullPointerException e) {
        } catch (ModuleDoesNotExistException e) {
        }
        String addModuleInput2 = "module c/CS2113 a/4 s/Dr.Akshay s/ChengChen";
        try {
            Command c = Parser.parse(addModuleInput2);
            c.execute(dq.getScheduleManager(), dq.getModuleManager(), dq.getUi());
        } catch (DueQuestException e) {
        } catch (NullPointerException e) {
        } catch (ModuleDoesNotExistException e) {
        }
        assertEquals(1, dq.getModuleManager().getNumberOfModules());
    }

    @Test
    void addEvent_addAValidEvent_returnsTrue() throws ModuleAlreadyExistsException {
        DueQuest dq = new DueQuest();
        String addModuleInput = "module c/CS2113 a/4 s/Dr.Akshay s/ChengChen";
        try {
            Command c = Parser.parse(addModuleInput );
            c.execute(dq.getScheduleManager(), dq.getModuleManager(), dq.getUi());
        } catch (DueQuestException e) {
        } catch (NullPointerException e) {
        } catch (ModuleDoesNotExistException e) {
        }
        String fullCommand = "event CS2113 final exam /at 2021-05-03 14:00 16:00 LT14";
        LocalDate date = LocalDate.of(2021, 5, 3);
        try {
            Command c =  Parser.parse(fullCommand);
            c.execute(dq.getScheduleManager(), dq.getModuleManager(), dq.getUi());
        } catch (DueQuestException e) {
        } catch (NullPointerException e) {
        } catch (ModuleDoesNotExistException e) {
        }
        assertEquals(1, dq.getScheduleManager().getNumberOfTaskInADay(date));
    }

    @Test
    void addingDeadline_addAValidDeadline_returnsTrue() throws ModuleAlreadyExistsException {
        DueQuest dq = new DueQuest();
        String addModuleInput = "module c/CS2113 a/4 s/Dr.Akshay s/ChengChen";
        try {
            Command c = Parser.parse(addModuleInput );
            c.execute(dq.getScheduleManager(), dq.getModuleManager(), dq.getUi());
        } catch (DueQuestException e) {
        } catch (NullPointerException e) {
        } catch (ModuleDoesNotExistException e) {
        }
        String fullCommand = "deadline CS2113 TP version 1 /by 2021-04-04";
        LocalDate date = LocalDate.of(2021, 4, 4);
        try {
            Command c =  Parser.parse(fullCommand);
            c.execute(dq.getScheduleManager(), dq.getModuleManager(), dq.getUi());
        } catch (DueQuestException e) {
        } catch (NullPointerException e) {
        } catch (ModuleDoesNotExistException e) {
        }
        assertEquals(1, dq.getScheduleManager().getNumberOfTaskInADay(date));
    }

    @Test
    void addLesson_addLessonWhenModuleHasNotBeenCreated_expectModuleDoesNotExistException() throws ModuleNotExistsException {
        DueQuest dq = new DueQuest();
        String input1 = "lesson online lecture CS2113 /on 5 16:00 18:00";
        try {
            Command c = Parser.parse(input1);
            c.execute(dq.getScheduleManager(), dq.getModuleManager(), dq.getUi());
        } catch (DueQuestException e) {
        } catch (NullPointerException e) {
        } catch (ModuleDoesNotExistException e) {
        }
        assertEquals(0, dq.getModuleManager().getNumberOfModules());
        String input2 = "module c/CS2113 a/4 s/Dr.Akshay s/ChengChen";
        try {
            Command c = Parser.parse(input2);
            c.execute(dq.getScheduleManager(), dq.getModuleManager(), dq.getUi());
        } catch (DueQuestException e) {
        } catch (NullPointerException e) {
        } catch (ModuleDoesNotExistException e) {
        }
        assertEquals(1, dq.getModuleManager().getNumberOfModules());
        try {
            Command c = Parser.parse(input1);
            c.execute(dq.getScheduleManager(), dq.getModuleManager(), dq.getUi());
        } catch (DueQuestException e) {
        } catch (NullPointerException e) {
        } catch (ModuleDoesNotExistException e) {
        }
        assertEquals(1, dq.getModuleManager().getModule("CS2113").getListOfTasks().size());
    }
}
