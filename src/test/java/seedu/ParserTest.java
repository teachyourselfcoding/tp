package seedu;

import org.junit.jupiter.api.Test;

import seedu.exception.*;

import seedu.task.Deadline;
import seedu.task.Event;
import seedu.task.Lesson;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void validateDeadline_validDeadlineLineInput_returnsDeadline() throws InvalidDateException,
            WrongDateFormatException, MissingDeadlineTimingDetailsException,
            EmptyArgumentException, InvalidModuleCodeException, MissingDeadlineDescriptionException, InvalidDateFormatException {
        String input = "deadline CS2113 TP version 1 /by 2021-04-04";
        Deadline deadline = Parser.validateDeadline(input);
        assertEquals(deadline.getModuleCode(), "CS2113");
        assertEquals(deadline.getDate(), LocalDate.of(2021, 04, 04));
        assertEquals(deadline.getDescription(), "TP version 1");
    }

    @Test
    void validateDeadline_inputWithoutDescription_exceptException() {
        String input = "deadline   ";
        assertThrows(EmptyArgumentException.class, () ->
        {
            Parser.validateDeadline(input);
        });
    }

    @Test
    void validateDeadline_inputWithoutProperDeadline_expectException() {
        String input = "deadline MA3333 20-04-04";
        assertThrows(MissingDeadlineTimingDetailsException.class, () ->
        {
            Parser.validateDeadline(input);
        });
    }

    @Test
    void validateDeadline_inputWithDateOutsideRange_expectException() {
        String input = "deadline CS2113 TP version 1 /by 2021-10-10";
        assertThrows(InvalidDateException.class, () ->
        {
            Parser.validateDeadline(input);
        });
        String input2 = "deadline CS2113 TP version 1 /by 2019-10-10";
        assertThrows(InvalidDateException.class, () ->
        {
            Parser.validateDeadline(input);
        });
    }

    @Test

    void parseLesson_validLesson_returnsLesson() {
        String input1 = "lesson online lecture CS2113 /on 5 16:00 18:00";
        String input2 = "lesson online lecture CS2113 5 16:00 18:00";
        String input3 = "lesson        ";
        try {
            Lesson lesson = Parser.parseLesson(input1);
            assertEquals(lesson.getLessonDayInDayOfWeek(), DayOfWeek.FRIDAY);
            assertEquals(lesson.getDescription(), "online lecture");
            assertEquals(lesson.getModuleCode(), "CS2113");
            assertEquals(lesson.getStartTime(), "16:00");
            assertEquals(lesson.getEndTime(), "18:00");
        } catch (Exception e) {  // ignore exceptions because this is a valid case 
        }
        assertThrows(MissingLessonTimingException.class, () ->
        {
            Parser.parseLesson(input2);
        });
        assertThrows(EmptyArgumentException.class, () ->
        {
            Parser.parseLesson(input3);
        });
    }

    @Test
    void parseLesson_lessonWithInvalidModuleCode_expectException() {
        String input = "lesson online lecture CS2113TTTT /on 5 16:00 18:00";
        assertThrows(InvalidModuleCodeException.class, () ->
        {
            Parser.parseLesson(input);
        });
    }

    @Test
    void parseLesson_lessonWithMissingTimingDetails_expectException() {
        String input = "lesson online lecture CS2113T 5 18:00";
        assertThrows(MissingLessonTimingException.class, () ->
        {
            Parser.parseLesson(input);
        });
    }

    @Test
    void parseLesson_lessonWithWrongFrequency_expectException() {
        String input = "lesson online lecture CS2113T /on 9 18:00 20:00";
        assertThrows(InvalidFrequencyException.class, () ->
        {
            Parser.parseLesson(input);
        });
    }

    @Test
    void parseLesson_lessonWithWrongTimeFormat_expectException() {
        String input = "lesson online lecture CS2113T /on 4 18:000 20:00";
        assertThrows(InvalidTimeFormatException.class, () ->
        {
            Parser.parseLesson(input);
        });
    }


    @Test
    void verifyModuleCode_validModule_returnsTrue() {
        String moduleCode1 = "MA2101";
        String moduleCode2 = "CS2113T";
        String moduleCode3 = "DSA4211";
        assertEquals(Parser.verifyModuleCode(moduleCode1), true);
        assertEquals(Parser.verifyModuleCode(moduleCode2), true);
        assertEquals(Parser.verifyModuleCode(moduleCode3), true);
    }

    @Test
    void verifyModuleCode_invalidModule_returnsFalse() {
        String moduleCode1 = "A1234";
        String moduleCode2 = "CS2113TTTT";
        String moduleCode3 = "CS99999";
        assertEquals(Parser.verifyModuleCode(moduleCode1), false);
        assertEquals(Parser.verifyModuleCode(moduleCode2), false);
        assertEquals(Parser.verifyModuleCode(moduleCode3), false);
    }

    @Test
    void parseLesson_validLesson_returnsTrue() throws
            EmptyArgumentException, MissingLessonTimingException, InvalidModuleCodeException,
            InvalidTimeFormatException, InvalidFrequencyException, StartAndEndTimeSameException,
            MissingLessonDescriptionException, StartTimeAndEndTimeTooEarlyException, StartTimeIsAfterEndTimeException {
        String input = "lesson online lecture CS2113 /on 5 16:00 18:00";
        Lesson expectedLesson = Parser.parseLesson(input);
        Lesson actualLesson = new Lesson("online lecture", "CS2113",
                5, "16:00", "18:00");
        Lesson wrongLesson = new Lesson("online lecture", "CS2113T",
                5, "16:00", "18:00");
        assertEquals(expectedLesson, actualLesson);
        assertEquals(expectedLesson.equals(wrongLesson), false);
    }

    @Test
    void validateDeadline_validDeadline_returnsTrue() throws
            WrongDateFormatException, InvalidDateException,
            EmptyArgumentException, MissingDeadlineTimingDetailsException, InvalidModuleCodeException,
            MissingDeadlineDescriptionException, InvalidDateFormatException {
        String input = "deadline CS2113 TP version 1 /by 2021-04-04";
        Deadline expectedDeadline = Parser.validateDeadline(input);
        Deadline actualDeadline = new Deadline("CS2113", "TP version 1", "2021-04-04");
        assertEquals(expectedDeadline, actualDeadline);
    }

    @Test
    void validateEvent_validEventLineInput_returnsEvent() throws InvalidDateException,
            MissingEventDateAndTimeDetailsException, WrongDateFormatException, InvalidTimeFormatException,
            EmptyArgumentException, StartAndEndTimeSameException, InvalidDateFormatException,
            MissingEventDescriptionException, StartTimeIsAfterEndTimeException, StartTimeAndEndTimeTooEarlyException {
        String input = "event CS2113 final exam /at 2021-05-03 14:00 16:00 LT14";
        Event event = Parser.validateEvent(input);
        // check the properties of event
        assertEquals(event.getDateOfEvent(), "2021-05-03");
        assertEquals(event.getDateOfEventInLocalDate(), LocalDate.of(2021, 05, 03));
        assertEquals(event.getEndTimeOfEvent(), "16:00");
        assertEquals(event.getAt(), "LT14");
        assertEquals(event.getModuleCode(), "CS2113");
        assertEquals(event.getDescription(), "final exam");
    }

    @Test
    void validateEvent_emptyEventInput_expectException() {
        String input = "event   ";
        assertThrows(EmptyArgumentException.class, () ->
        {
            Parser.validateEvent(input);
        });
    }

    @Test
    void validateEvent_missingAtEventInput_expectException() {
        String input = "event CS2113 final exam 2021-05-03 14:00 16:00 LT14";
        assertThrows(MissingEventDateAndTimeDetailsException.class, () ->
        {
            Parser.validateEvent(input);
        });
    }

    @Test
    void validateEvent_missingEventDateTimeDetails_expectException() {
        String input = "event CS2113 final exam /at 2021-05-03 14:00 LT14";
        assertThrows(MissingEventDateAndTimeDetailsException.class, () ->
        {
            Parser.validateEvent(input);
        });
    }

    @Test
    void validateEvent_invalidEventDate_expectException() {
        String input1 = "event CS2113 final exam /at 2021-10-03 12:00 14:00 LT14";
        String input2 = "event CS2113 final exam /at 2018-10-03 12:00 14:00 LT14";
        assertThrows(InvalidDateException.class, () ->
        {
            Parser.validateEvent(input1);
        });
        assertThrows(InvalidDateException.class, () ->
        {
            Parser.validateEvent(input2);
        });
    }

    @Test
    void validateEvent_invalidEventTime_expectException() {
        String input1 = "event CS2113 final exam /at 2021-05-03 12:002 14:00 LT14";
        String input2 = "event CS2113 final exam /at 2021-05-03 12:00 14:003 LT14";
        String input3 = "event CS2113 final exam /at 2021-05-03 25:19 14:00 LT14";
        String input4 = "event CS2113 final exam /at 2021-05-03 12:00 bbbbs LT14";
        assertThrows(InvalidTimeFormatException.class, () ->
        {
            Parser.validateEvent(input1);
        });
        assertThrows(InvalidTimeFormatException.class, () ->
        {
            Parser.validateEvent(input2);
        });
    }
}
