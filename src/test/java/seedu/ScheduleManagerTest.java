package seedu;

import org.junit.jupiter.api.Test;
import seedu.exception.EmptyArgumentException;
import seedu.exception.InvalidDateException;
import seedu.exception.InvalidFrequencyException;
import seedu.exception.InvalidModuleCodeException;
import seedu.exception.InvalidTimeFormatException;
import seedu.exception.MissingDeadlineDescriptionException;
import seedu.exception.MissingDeadlineTimingDetailsException;
import seedu.exception.MissingEventDateAndTimeDetailsException;
import seedu.exception.MissingEventDescriptionException;
import seedu.exception.MissingLessonDescriptionException;
import seedu.exception.MissingLessonTimingException;
import seedu.exception.MissingModuleCodeOrInvalidModuleCodeException;
import seedu.exception.StartAndEndTimeSameException;
import seedu.exception.StartTimeAndEndTimeTooEarlyException;
import seedu.exception.StartTimeIsAfterEndTimeException;
import seedu.exception.WrongDateFormatException;
import seedu.exception.InvalidStartEndDateException;
import seedu.exception.ModuleAlreadyExistsException;
import seedu.exception.ModuleDoesNotExistException;


import seedu.module.Module;
import seedu.task.Event;
import seedu.task.Lesson;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ScheduleManagerTest {
    @Test
    public void display_longAgoDate_expectExceptions() {
        ScheduleManager sm = new ScheduleManager();
        assertThrows(InvalidDateException.class, () -> sm.displayDate(LocalDate.parse("2010-10-10")));
    }

    @Test
    public void display_TooFarAhead_expectExceptions() {
        ScheduleManager sm = new ScheduleManager();
        assertThrows(InvalidDateException.class, () -> sm.displayDate(LocalDate.parse("2100-10-10")));
    }

    @Test
    public void display_startAndEndDateSwap_expectExceptions() {
        ScheduleManager sm = new ScheduleManager();
        assertThrows(InvalidStartEndDateException.class, () -> sm.display(LocalDate.parse("2020-11-28"),
                LocalDate.parse("2020-10-20")));
    }

    @Test
    public void checkIfLessonToBeAddedClashesWithCurrentTimetable_validLesson_true() throws
            MissingLessonTimingException, EmptyArgumentException, InvalidModuleCodeException,
            InvalidTimeFormatException, InvalidFrequencyException, StartAndEndTimeSameException,
            MissingLessonDescriptionException, StartTimeAndEndTimeTooEarlyException, StartTimeIsAfterEndTimeException,
            ModuleDoesNotExistException, ModuleAlreadyExistsException {
        String input1 = "lesson online lecture CS2113 /on 5 16:00 18:00";
        Lesson lesson = Parser.parseLesson(input1);
        ScheduleManager scheduleManager = new ScheduleManager();
        ModuleManager moduleManager = new ModuleManager();
        moduleManager.addModule(new Module("CS2113"));
        Ui ui = new Ui();
        scheduleManager.addLesson(lesson, moduleManager, ui);
        boolean result = scheduleManager.checkIfLessonToBeAddedClashesWithCurrentTimetable(lesson);
        assertEquals(result, true);
    }

    @Test
    public void checkIfLessonToBeAddedClashesInADate_validLessonAndDate_true() throws
            MissingLessonTimingException, EmptyArgumentException, InvalidModuleCodeException,
            InvalidTimeFormatException, InvalidFrequencyException, InvalidDateException,
            MissingEventDateAndTimeDetailsException, WrongDateFormatException,
            StartAndEndTimeSameException, MissingLessonDescriptionException,
            MissingEventDescriptionException, StartTimeAndEndTimeTooEarlyException, StartTimeIsAfterEndTimeException,
            MissingModuleCodeOrInvalidModuleCodeException, ModuleDoesNotExistException, ModuleAlreadyExistsException {
        String input1 = "lesson online lecture CS2113 /on 5 16:00 18:00";
        String input2 = "event CS2113 final exam /at 2021-01-15 14:00 16:00 LT14";
        Lesson lesson = Parser.parseLesson(input1);
        Event event = Parser.validateEvent(input2);
        ScheduleManager scheduleManager = new ScheduleManager();
        ModuleManager moduleManager = new ModuleManager();
        moduleManager.addModule(new Module("CS2113"));
        Ui ui = new Ui();
        scheduleManager.addLesson(lesson, moduleManager, ui);
        scheduleManager.addEvent(event, moduleManager, ui);
        String input3 = "lesson tutorial CS2113 /on 5 14:00 16:00";
        Lesson lesson2 = Parser.parseLesson(input3);
        assertEquals(true, scheduleManager.checkIfLessonToBeAddedClashesInADate(
                lesson2, LocalDate.of(2021, 1, 15)));
        String input4 = "lesson tutorial CS2113 /on 5 14:00 17:00";
        Lesson lesson3 = Parser.parseLesson(input4);
        assertEquals(true, scheduleManager.checkIfLessonToBeAddedClashesInADate(
                lesson3, LocalDate.of(2021, 1, 15)));
        String input5 = "lesson tutorial CS2113 /on 5 13:00 17:00";
        Lesson lesson4 = Parser.parseLesson(input5);
        assertEquals(true, scheduleManager.checkIfLessonToBeAddedClashesInADate(
                lesson4, LocalDate.of(2021, 1, 15)));
    }

    @Test
    void deleteTask_deleteTaskViaDescription_returnsTrue() throws ModuleAlreadyExistsException,
            StartTimeAndEndTimeTooEarlyException, MissingLessonDescriptionException, StartTimeIsAfterEndTimeException,
            InvalidTimeFormatException, EmptyArgumentException, InvalidModuleCodeException, InvalidFrequencyException,
            StartAndEndTimeSameException, MissingLessonTimingException, ModuleDoesNotExistException,
            InvalidDateException {
        ScheduleManager scheduleManager = new ScheduleManager();
        ModuleManager moduleManager = new ModuleManager();
        Ui ui = new Ui();
        moduleManager.addModule(new Module("CS2113"));
        String input1 = "lesson online lecture CS2113 /on 5 16:00 18:00";
        scheduleManager.addLesson(Parser.parseLesson(input1), moduleManager, ui);
        assertEquals(1, scheduleManager.getSemesterSchedule().get(LocalDate
                .of(2021, 1, 15)).size());
        scheduleManager.deleteTask("online lectur");
        assertEquals(1, scheduleManager.getSemesterSchedule().get(LocalDate
                .of(2021, 1, 15)).size());
        scheduleManager.deleteTask("online lecture");
        assertEquals(0, scheduleManager.getSemesterSchedule().get(LocalDate
                .of(2021, 1, 15)).size());
    }

    @Test
    void deleteTask_deleteTaskViaDescriptionAndModuleCode_returnsTrue() throws ModuleAlreadyExistsException,
            StartTimeAndEndTimeTooEarlyException, MissingLessonDescriptionException, StartTimeIsAfterEndTimeException,
            InvalidTimeFormatException, EmptyArgumentException, InvalidModuleCodeException, InvalidFrequencyException,
            StartAndEndTimeSameException, MissingLessonTimingException, ModuleDoesNotExistException,
            InvalidDateException {
        ScheduleManager scheduleManager = new ScheduleManager();
        ModuleManager moduleManager = new ModuleManager();
        Ui ui = new Ui();
        moduleManager.addModule(new Module("CS2113"));
        String input1 = "lesson online lecture CS2113 /on 5 16:00 18:00";
        scheduleManager.addLesson(Parser.parseLesson(input1), moduleManager, ui);
        assertEquals(1, scheduleManager.getSemesterSchedule().get(LocalDate
                .of(2021, 1, 15)).size());
        scheduleManager.deleteTask("online lecture", "CS2115");
        assertEquals(1, scheduleManager.getSemesterSchedule().get(LocalDate
                .of(2021, 1, 15)).size());
        scheduleManager.deleteTask("online lecture", "CS2113");
        assertEquals(0, scheduleManager.getSemesterSchedule().get(LocalDate
                .of(2021, 1, 15)).size());
    }

    @Test
    void deleteTask_deleteTaskViaModuleCodeAndDate_returnsTrue() throws ModuleAlreadyExistsException,
            StartTimeAndEndTimeTooEarlyException, StartTimeIsAfterEndTimeException, InvalidTimeFormatException,
            EmptyArgumentException, StartAndEndTimeSameException,  ModuleDoesNotExistException,
            InvalidDateException, MissingEventDateAndTimeDetailsException, WrongDateFormatException,
            MissingModuleCodeOrInvalidModuleCodeException, MissingEventDescriptionException {
        ScheduleManager scheduleManager = new ScheduleManager();
        ModuleManager moduleManager = new ModuleManager();
        Ui ui = new Ui();
        moduleManager.addModule(new Module("CS2113"));
        String input1 = "event CS2113 final exam /at 2021-05-03 14:00 16:00 LT14";
        scheduleManager.addEvent(Parser.validateEvent(input1), moduleManager, ui);
        assertEquals(1, scheduleManager.getSemesterSchedule().get(LocalDate
                .of(2021, 5, 3)).size());
        scheduleManager.deleteTask(LocalDate.of(2021, 5, 3), "CS2112");
        assertEquals(1, scheduleManager.getSemesterSchedule().get(LocalDate
                .of(2021, 5, 3)).size());
        scheduleManager.deleteTask(LocalDate.of(2021, 5, 3), "CS2113");
        assertEquals(0, scheduleManager.getSemesterSchedule().get(LocalDate
                .of(2021, 5, 3)).size());
    }

    @Test
    void deleteTask_deleteTaskViaModuleCodeDescriptionAndDate_returnsTrue() throws ModuleAlreadyExistsException,
            StartTimeAndEndTimeTooEarlyException, StartTimeIsAfterEndTimeException, InvalidTimeFormatException,
            EmptyArgumentException, StartAndEndTimeSameException,  ModuleDoesNotExistException,
            InvalidDateException, MissingEventDateAndTimeDetailsException, WrongDateFormatException,
            MissingModuleCodeOrInvalidModuleCodeException, MissingEventDescriptionException {
        ScheduleManager scheduleManager = new ScheduleManager();
        ModuleManager moduleManager = new ModuleManager();
        Ui ui = new Ui();
        moduleManager.addModule(new Module("CS2113"));
        String input1 = "event CS2113 final exam /at 2021-05-03 14:00 16:00 LT14";
        scheduleManager.addEvent(Parser.validateEvent(input1), moduleManager, ui);
        assertEquals(1, scheduleManager.getSemesterSchedule().get(LocalDate
                .of(2021, 5, 3)).size());
        scheduleManager.deleteTask("CS2113", "final exam", LocalDate
                .of(2021, 5, 2));
        assertEquals(1, scheduleManager.getSemesterSchedule().get(LocalDate
                .of(2021, 5, 3)).size());
        scheduleManager.deleteTask("CS2113", "final exam", LocalDate
                .of(2021, 5, 3));
        assertEquals(0, scheduleManager.getSemesterSchedule().get(LocalDate
                .of(2021, 5, 3)).size());
    }

    @Test
    void deleteTask_deleteTaskViaDescriptionAndDate_returnsTrue() throws ModuleAlreadyExistsException,
            StartTimeAndEndTimeTooEarlyException, StartTimeIsAfterEndTimeException, InvalidTimeFormatException,
            EmptyArgumentException, StartAndEndTimeSameException, ModuleDoesNotExistException,
            InvalidDateException, MissingEventDateAndTimeDetailsException, WrongDateFormatException,
            MissingModuleCodeOrInvalidModuleCodeException, MissingEventDescriptionException,
            MissingDeadlineTimingDetailsException, MissingDeadlineDescriptionException,
            InvalidModuleCodeException {
        ScheduleManager scheduleManager = new ScheduleManager();
        ModuleManager moduleManager = new ModuleManager();
        Ui ui = new Ui();
        moduleManager.addModule(new Module("CS2113"));
        String input1 = "event CS2113 final exam /at 2021-05-03 14:00 16:00 LT14";
        scheduleManager.addEvent(Parser.validateEvent(input1), moduleManager, ui);
        String input2 = "deadline CS2113 TP version 1 /by 2021-05-03";
        scheduleManager.addDeadline(Parser.validateDeadline(input2), moduleManager);
        assertEquals(2, scheduleManager.getSemesterSchedule().get(LocalDate
                .of(2021, 5, 3)).size());
        scheduleManager.deleteTask("TP version 1", LocalDate.of(2021, 5, 3));
        assertEquals(1, scheduleManager.getSemesterSchedule().get(LocalDate
                .of(2021, 5, 3)).size());
    }

    @Test
    void editTask_editDateOfTask_returnsTrue() throws ModuleAlreadyExistsException,
            MissingEventDateAndTimeDetailsException, MissingEventDescriptionException,
            MissingModuleCodeOrInvalidModuleCodeException, InvalidDateException,
            StartTimeIsAfterEndTimeException, InvalidTimeFormatException, EmptyArgumentException,
            StartAndEndTimeSameException, StartTimeAndEndTimeTooEarlyException, WrongDateFormatException,
            ModuleDoesNotExistException, MissingDeadlineTimingDetailsException, MissingDeadlineDescriptionException,
            InvalidModuleCodeException {
        ScheduleManager scheduleManager = new ScheduleManager();
        ModuleManager moduleManager = new ModuleManager();
        Ui ui = new Ui();
        moduleManager.addModule(new Module("CS2113"));
        String input1 = "event CS2113 final exam /at 2021-05-03 14:00 16:00 LT14";
        scheduleManager.addEvent(Parser.validateEvent(input1), moduleManager, ui);
        String input2 = "deadline CS2113 TP version 1 /by 2021-05-03";
        scheduleManager.addDeadline(Parser.validateDeadline(input2), moduleManager);
        scheduleManager.editTask("final exam", LocalDate.of(2021, 5, 3),
                "", LocalDate.of(2021, 5, 5));
        assertEquals(1, scheduleManager.getSemesterSchedule().get(LocalDate
                .of(2021, 5, 3)).size());
        assertEquals(1, scheduleManager.getSemesterSchedule().get(LocalDate
                .of(2021, 5, 5)).size());
    }

    @Test
    void editTask_editDateOfModuleTask_returnsTrue() throws ModuleAlreadyExistsException,
            MissingEventDateAndTimeDetailsException, MissingEventDescriptionException,
            MissingModuleCodeOrInvalidModuleCodeException, InvalidDateException,
            StartTimeIsAfterEndTimeException, InvalidTimeFormatException, EmptyArgumentException,
            StartAndEndTimeSameException, StartTimeAndEndTimeTooEarlyException, WrongDateFormatException,
            ModuleDoesNotExistException, MissingDeadlineTimingDetailsException, MissingDeadlineDescriptionException,
            InvalidModuleCodeException, InvalidDateException {
        ScheduleManager scheduleManager = new ScheduleManager();
        ModuleManager moduleManager = new ModuleManager();
        Ui ui = new Ui();
        moduleManager.addModule(new Module("CS2113"));
        String input1 = "event CS2113 final exam /at 2021-05-03 14:00 16:00 LT14";
        scheduleManager.addEvent(Parser.validateEvent(input1), moduleManager, ui);
        String input2 = "deadline CS2113 TP version 1 /by 2021-05-03";
        scheduleManager.addDeadline(Parser.validateDeadline(input2), moduleManager);
        scheduleManager.editTask("final exam", LocalDate.of(2021, 5, 3),
                "", LocalDate.of(2021, 5, 5), "CS2112");
        assertEquals(2, scheduleManager.getSemesterSchedule().get(LocalDate
                .of(2021, 5, 3)).size());
        assertEquals(0, scheduleManager.getSemesterSchedule().get(LocalDate
                .of(2021, 5, 5)).size());
        scheduleManager.editTask("final exam", LocalDate.of(2021, 5, 3),
                "", LocalDate.of(2021, 5, 5), "CS2113");
        assertEquals(1, scheduleManager.getSemesterSchedule().get(LocalDate
                .of(2021, 5, 3)).size());
        assertEquals(1, scheduleManager.getSemesterSchedule().get(LocalDate
                .of(2021, 5, 5)).size());
    }
}
