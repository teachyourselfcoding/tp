package seedu;

import org.junit.jupiter.api.Test;
import seedu.exception.*;
import seedu.task.Event;
import seedu.task.Lesson;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleManagerTest {

    @Test
    public void checkIfLessonToBeAddedClashesWithCurrentTimetable_validLesson_true() throws
            MissingLessonTimingException, EmptyArgumentException, InvalidModuleCodeException,
            InvalidTimeFormatException, InvalidFrequencyException {
        String input1 = "lesson online lecture CS2113 /on 5 16:00 18:00";
        Lesson lesson = Parser.parseLesson(input1);
        ScheduleManager scheduleManager = new ScheduleManager();
        ModuleManager moduleManager = new ModuleManager();
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
            MissingDeadlineTimingDetailsException {
        String input1 = "lesson online lecture CS2113 /on 5 16:00 18:00";
        String input2 = "event CS2113 final exam /at 2021-01-15 14:00 16:00 LT14";
        Lesson lesson = Parser.parseLesson(input1);
        Event event = Parser.validateEvent(input2);
        ScheduleManager scheduleManager = new ScheduleManager();
        ModuleManager moduleManager = new ModuleManager();
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