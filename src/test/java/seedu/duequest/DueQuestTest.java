package seedu.duequest;

import org.junit.jupiter.api.Test;
import seedu.DueQuestException;
import seedu.module.Module;
import seedu.Parser;
import seedu.command.Command;
import seedu.exception.EmptyArgumentException;
import seedu.exception.ModuleDoesNotExistException;
import seedu.exception.ModuleAlreadyExistsException;
import seedu.exception.ModuleNotExistsException;
import seedu.exception.InvalidScoreException;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


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
            System.out.println(e);
        } catch (NullPointerException e) {  // this has been handled within parser
            System.out.println(e);
        } catch (ModuleDoesNotExistException | EmptyArgumentException | InvalidScoreException e) {
            System.out.println(e);
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
            System.out.println(e);
        } catch (NullPointerException e) {
            System.out.println(e);
        } catch (ModuleDoesNotExistException e) {
            System.out.println(e);
        } catch (EmptyArgumentException e) {
            System.out.println(e);
        } catch (InvalidScoreException e) {
            System.out.println(e);
        }
        String addModuleInput2 = "module c/CS2113 a/4 s/Dr.Akshay s/ChengChen";
        try {
            Command c = Parser.parse(addModuleInput2);
            c.execute(dq.getScheduleManager(), dq.getModuleManager(), dq.getUi());
        } catch (DueQuestException e) {
            System.out.println(e);
        } catch (NullPointerException | EmptyArgumentException | InvalidScoreException e) {
            System.out.println(e);
        } catch (ModuleDoesNotExistException e) {
            System.out.println(e);
        }
        assertEquals(1, dq.getModuleManager().getNumberOfModules());
    }

    @Test
    void addEvent_addAValidEvent_returnsTrue() throws ModuleAlreadyExistsException {
        DueQuest dq = new DueQuest();
        String addModuleInput = "module c/CS2113 a/4 s/Dr.Akshay s/ChengChen";
        try {
            Command c = Parser.parse(addModuleInput);
            c.execute(dq.getScheduleManager(), dq.getModuleManager(), dq.getUi());
        } catch (DueQuestException | EmptyArgumentException | InvalidScoreException e) {
            System.out.println(e);
        } catch (NullPointerException e) {
            System.out.println(e);
        } catch (ModuleDoesNotExistException e) {
            System.out.println(e);
        }
        String fullCommand = "event CS2113 final exam /at 2021-05-03 14:00 16:00 LT14";
        LocalDate date = LocalDate.of(2021, 5, 3);
        try {
            Command c =  Parser.parse(fullCommand);
            c.execute(dq.getScheduleManager(), dq.getModuleManager(), dq.getUi());
        } catch (DueQuestException e) {
            System.out.println(e);
        } catch (NullPointerException e) {
            System.out.println(e);
        } catch (ModuleDoesNotExistException | EmptyArgumentException | InvalidScoreException e) {
            System.out.println(e);
        }
        assertEquals(1, dq.getScheduleManager().getNumberOfTaskInADay(date));
    }

    @Test
    void addingDeadline_addAValidDeadline_returnsTrue() throws ModuleAlreadyExistsException {
        DueQuest dq = new DueQuest();
        String addModuleInput = "module c/CS2113 a/4 s/Dr.Akshay s/ChengChen";
        try {
            Command c = Parser.parse(addModuleInput);
            c.execute(dq.getScheduleManager(), dq.getModuleManager(), dq.getUi());
        } catch (DueQuestException e) {
            System.out.println(e);
        } catch (NullPointerException e) {
            System.out.println(e);
        } catch (ModuleDoesNotExistException | EmptyArgumentException | InvalidScoreException e) {
            System.out.println(e);
        }
        String fullCommand = "deadline CS2113 TP version 1 /by 2021-04-04";
        LocalDate date = LocalDate.of(2021, 4, 4);
        try {
            Command c =  Parser.parse(fullCommand);
            c.execute(dq.getScheduleManager(), dq.getModuleManager(), dq.getUi());
        } catch (DueQuestException e) {
            System.out.println(e);
        } catch (NullPointerException e) {
            System.out.println(e);
        } catch (ModuleDoesNotExistException | EmptyArgumentException | InvalidScoreException e) {
            System.out.println(e);
        }
        assertEquals(1, dq.getScheduleManager().getNumberOfTaskInADay(date));
    }

    @Test
    void addLesson_addLessonWhenModuleHasNotBeenCreated_expectModuleDoesNotExistException()
            throws ModuleNotExistsException {
        DueQuest dq = new DueQuest();
        String input1 = "lesson online lecture CS2113 /on 5 16:00 18:00";
        try {
            Command c = Parser.parse(input1);
            c.execute(dq.getScheduleManager(), dq.getModuleManager(), dq.getUi());
        } catch (DueQuestException e) {
            System.out.println(e);
        } catch (NullPointerException | EmptyArgumentException | InvalidScoreException e) {
            System.out.println(e);
        } catch (ModuleDoesNotExistException e) {
            System.out.println(e);
        }
        assertEquals(0, dq.getModuleManager().getNumberOfModules());
        String input2 = "module c/CS2113 a/4 s/Dr.Akshay s/ChengChen";
        try {
            Command c = Parser.parse(input2);
            c.execute(dq.getScheduleManager(), dq.getModuleManager(), dq.getUi());
        } catch (DueQuestException e) {
            System.out.println(e);
        } catch (NullPointerException e) {
            System.out.println(e);
        } catch (ModuleDoesNotExistException | EmptyArgumentException | InvalidScoreException e) {
            System.out.println(e);
        }
        assertEquals(1, dq.getModuleManager().getNumberOfModules());
        try {
            Command c = Parser.parse(input1);
            c.execute(dq.getScheduleManager(), dq.getModuleManager(), dq.getUi());
        } catch (DueQuestException e) {
            System.out.println(e);
        } catch (NullPointerException e) {
            System.out.println(e);
        } catch (ModuleDoesNotExistException | EmptyArgumentException | InvalidScoreException e) {
            System.out.println(e);
        }
        assertEquals(1, dq.getModuleManager().getModule("CS2113").getListOfTasks().size());
    }

    @Test
    void addLesson_addLessonWithClashes_returnsTrue() throws ModuleAlreadyExistsException {
        DueQuest dq = new DueQuest();
        dq.getModuleManager().addModule(new Module("CS2113"));
        String input1 = "lesson online lecture CS2113 /on 5 16:00 18:00";
        try {
            Command c = Parser.parse(input1);
            c.execute(dq.getScheduleManager(), dq.getModuleManager(), dq.getUi());
        } catch (DueQuestException e) {
            System.out.println(e);
        } catch (NullPointerException | EmptyArgumentException | InvalidScoreException e) {
            System.out.println(e);
        } catch (ModuleDoesNotExistException e) {
            System.out.println(e);
        }
        assertEquals(1, dq.getScheduleManager().getSemesterSchedule()
                .get(LocalDate.of(2021, 1, 15)).size());
        String input2 = "lesson online lecture CS2113 /on 5 14:00 18:00";
        try {
            Command c = Parser.parse(input2);
            c.execute(dq.getScheduleManager(), dq.getModuleManager(), dq.getUi());
        } catch (DueQuestException e) {
            System.out.println(e);
        } catch (NullPointerException | EmptyArgumentException | InvalidScoreException e) {
            System.out.println(e);
        } catch (ModuleDoesNotExistException e) {
            System.out.println(e);
        }
        assertEquals(1, dq.getScheduleManager().getSemesterSchedule()
                .get(LocalDate.of(2021, 1, 15)).size());
        String input3 = "lesson online lecture CS2113 /on 5 16:00 20:00";
        try {
            Command c = Parser.parse(input3);
            c.execute(dq.getScheduleManager(), dq.getModuleManager(), dq.getUi());
        } catch (DueQuestException e) {
            System.out.println(e);
        } catch (NullPointerException | EmptyArgumentException | InvalidScoreException e) {
            System.out.println(e);
        } catch (ModuleDoesNotExistException e) {
            System.out.println(e);
        }
        assertEquals(1, dq.getScheduleManager().getSemesterSchedule()
                .get(LocalDate.of(2021, 1, 15)).size());
        String input4 = "lesson extra class CS2113 /on 5 20:00 22:00";
        try {
            Command c = Parser.parse(input4);
            c.execute(dq.getScheduleManager(), dq.getModuleManager(), dq.getUi());
        } catch (DueQuestException e) {
            System.out.println(e);
        } catch (NullPointerException | EmptyArgumentException | InvalidScoreException e) {
            System.out.println(e);
        } catch (ModuleDoesNotExistException e) {
            System.out.println(e);
        }
        assertEquals(2, dq.getScheduleManager().getSemesterSchedule()
                .get(LocalDate.of(2021, 1, 15)).size());
        String input5 = "event CS2113 final exam /at 2021-01-15 14:00 17:00 LT14";
        try {
            Command c = Parser.parse(input5);
            c.execute(dq.getScheduleManager(), dq.getModuleManager(), dq.getUi());
        } catch (DueQuestException e) {
            System.out.println(e);
        } catch (NullPointerException | EmptyArgumentException | InvalidScoreException e) {
            System.out.println(e);
        } catch (ModuleDoesNotExistException e) {
            System.out.println(e);
        }
        assertEquals(2, dq.getScheduleManager().getSemesterSchedule()
                .get(LocalDate.of(2021, 1, 15)).size());
        String input6 = "event CS2113 final exam /at 2021-01-15 14:00 16:00 LT14";
        try {
            Command c = Parser.parse(input6);
            c.execute(dq.getScheduleManager(), dq.getModuleManager(), dq.getUi());
        } catch (DueQuestException e) {
            System.out.println(e);
        } catch (NullPointerException | EmptyArgumentException | InvalidScoreException e) {
            System.out.println(e);
        } catch (ModuleDoesNotExistException e) {
            System.out.println(e);
        }
        assertEquals(3, dq.getScheduleManager().getSemesterSchedule()
                .get(LocalDate.of(2021, 1, 15)).size());
    }

    /*
    @Test
    void deleteTask_deleteTaskWithDescription_returnTrue() throws ModuleAlreadyExistsException,
    ModuleNotExistsException {
        DueQuest dq = new DueQuest();
        dq.getModuleManager().addModule(new Module("CS2113"));
        String input1 = "deadline CS2113 tp /by 2020-10-16";
        try {
            Command c = Parser.parse(input1);
            c.execute(dq.getScheduleManager(), dq.getModuleManager(), dq.getUi());
        } catch (DueQuestException e) {
        } catch (NullPointerException | EmptyArgumentException | InvalidScoreException e) {
        } catch (ModuleDoesNotExistException e) {
        }
        assertEquals(1, dq.getModuleManager().getNumberOfModules());
        String input2 = "delete tp";
        try {
            Command c = Parser.parse(input2);
            c.execute(dq.getScheduleManager(), dq.getModuleManager(), dq.getUi());
        } catch (DueQuestException e) {
        } catch (NullPointerException | EmptyArgumentException | InvalidScoreException e) {
        } catch (ModuleDoesNotExistException e) {
        }
        assertEquals(0, dq.getModuleManager().getModule("CS2113").getListOfTasks().size());
    }
    */
}
