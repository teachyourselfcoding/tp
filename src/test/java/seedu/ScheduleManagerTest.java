package seedu;

import org.junit.jupiter.api.Test;
import seedu.exception.InvalidDateException;
import seedu.exception.InvalidStartEndDateException;

import seedu.exception.EmptyArgumentException;
import seedu.exception.InvalidFrequencyException;
import seedu.exception.InvalidModuleCodeException;
import seedu.exception.InvalidTimeFormatException;
import seedu.exception.MissingEventDateAndTimeDetailsException;
import seedu.exception.MissingEventDescriptionException;
import seedu.exception.MissingLessonDescriptionException;
import seedu.exception.MissingLessonTimingException;
import seedu.exception.MissingModuleCodeOrInvalidModuleCodeException;
import seedu.exception.StartAndEndTimeSameException;
import seedu.exception.StartTimeAndEndTimeTooEarlyException;
import seedu.exception.StartTimeIsAfterEndTimeException;
import seedu.exception.WrongDateFormatException;
import seedu.exception.ModuleDoesNotExistException;
import seedu.exception.ModuleAlreadyExistsException;

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
}
