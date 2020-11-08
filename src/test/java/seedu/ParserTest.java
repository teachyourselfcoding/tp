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
import seedu.exception.EmptyArgumentException;

import seedu.task.Deadline;
import seedu.task.Event;
import seedu.task.Lesson;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {
    @Test
    void parseLesson_emptyArgumentLessonInput_expectEmptyArgumentException() {
        String input1 = "lesson";
        assertThrows(EmptyArgumentException.class, () -> Parser.parseLesson(input1));
        String input2 = "lesson  ";
        assertThrows(EmptyArgumentException.class, () -> Parser.parseLesson(input2));
    }

    @Test
    void parseLesson_validLesson_returnsLesson() {
        String input1 = "lesson online lecture CS2113 /on 5 16:00 18:00";
        String input2 = "lesson online lecture CS2113 5 16:00 18:00";
        try {
            Lesson lesson = Parser.parseLesson(input1);
            assertEquals(lesson.getLessonDayInDayOfWeek(), DayOfWeek.FRIDAY);
            assertEquals(lesson.getDescription(), "online lecture");
            assertEquals(lesson.getModuleCode(), "CS2113");
            assertEquals(lesson.getStartTime(), "16:00");
            assertEquals(lesson.getEndTime(), "18:00");
        } catch (Exception e) {  // ignore exceptions because this is a valid case 
        }
        assertThrows(MissingLessonTimingException.class, () -> Parser.parseLesson(input2));
    }

    @Test
    void parseLesson_lessonWithInvalidModuleCode_expectInvalidModuleCodeException() {
        String input1 = "lesson online lecture CS2113TTTT /on 5 16:00 18:00";
        assertThrows(InvalidModuleCodeException.class, () -> Parser.parseLesson(input1));
        String input2 = "lesson online lecture CS2113a /on 5 16:00 18:00";
        assertThrows(InvalidModuleCodeException.class, () -> Parser.parseLesson(input2));
        String input3 = "lesson online lecture ka /on 5 16:00 18:00";
        assertThrows(InvalidModuleCodeException.class, () -> Parser.parseLesson(input3));
        String input4 = "lesson online lecture CAKSKS /on 5 16:00 18:00";
        assertThrows(InvalidModuleCodeException.class, () -> Parser.parseLesson(input4));
        String input5 = "lesson online lecture 000000 /on 5 16:00 18:00";
        assertThrows(InvalidModuleCodeException.class, () -> Parser.parseLesson(input5));
        String input6 = "lesson online lecture aa2222 /on 5 16:00 18:00";
        assertThrows(InvalidModuleCodeException.class, () -> Parser.parseLesson(input6));
        String input7 = "lesson online lecture aa2222s /on 5 16:00 18:00";
        assertThrows(InvalidModuleCodeException.class, () -> Parser.parseLesson(input7));

    }

    @Test
    void parseLesson_lessonWithMissingTimingDetails_expectMissingLessonTimingException() {
        String input1 = "lesson online lecture CS2113T /on 5 18:00";
        String input2 = "lesson online lecture CS2113T /on 1 20:00";
        assertThrows(MissingLessonTimingException.class, () -> Parser.parseLesson(input1));
        assertThrows(MissingLessonTimingException.class, () -> Parser.parseLesson(input2));
    }

    @Test
    void parseLesson_lessonWithMissingDescription_expectMissingLessonDescriptionException() {
        String input1 = "lesson  CS2113T /on 5 18:00 20:00";
        String input2 = "lesson CS2113T/on 5 18:00 20:00";
        assertThrows(MissingLessonDescriptionException.class, () -> Parser.parseLesson(input1));
        assertThrows(MissingLessonDescriptionException.class, () -> Parser.parseLesson(input2));
    }

    @Test
    void parseLesson_lessonWithWrongFrequency_expectInvalidFrequencyException() {
        String input1 = "lesson online lecture CS2113T /on 9 18:00 20:00";
        String input2 = "lesson online lecture CS2113T /on a 18:00 20:00";
        assertThrows(InvalidFrequencyException.class, () -> Parser.parseLesson(input1));
        assertThrows(InvalidFrequencyException.class, () -> Parser.parseLesson(input2));
    }

    @Test
    void parseLesson_lessonWithSameStartTimeAndEndTime_expectStartAndEndTimeSameException() {
        String input1 = "lesson online lecture CS2113T /on 2 08:00 08:00";
        String input2 = "lesson online lecture CS2113T /on 2 23:00 23:00";
        assertThrows(StartAndEndTimeSameException.class, () -> Parser.parseLesson(input1));
        assertThrows(StartAndEndTimeSameException.class, () -> Parser.parseLesson(input2));
    }

    @Test
    void parseLesson_lessonWithStartTimeAfterEndTime_expectStartTimeIsAfterEndTimeException() {
        String input1 = "lesson online lecture CS2113T /on 2 09:00 08:00";
        String input2 = "lesson online lecture CS2113T /on 2 23:00 00:00";
        String input3 = "lesson online lecture CS2113T /on 2 23:00 01:00";
        assertThrows(StartTimeIsAfterEndTimeException.class, () -> Parser.parseLesson(input1));
        assertThrows(StartTimeIsAfterEndTimeException.class, () -> Parser.parseLesson(input2));
        assertThrows(StartTimeIsAfterEndTimeException.class, () -> Parser.parseLesson(input3));
    }

    @Test
    void parseLesson_lessonWithStartOrEndTimeTooEarly_expectStartTimeAndEndTimeTooEarlyException() {
        String input1 = "lesson online lecture CS2113T /on 2 00:00 08:00";
        String input2 = "lesson online lecture CS2113T /on 2 01:00 08:00";
        String input3 = "lesson online lecture CS2113T /on 2 04:00 05:00";
        assertThrows(StartTimeAndEndTimeTooEarlyException.class, () ->  Parser.parseLesson(input1));
        assertThrows(StartTimeAndEndTimeTooEarlyException.class, () ->  Parser.parseLesson(input2));
        assertThrows(StartTimeAndEndTimeTooEarlyException.class, () ->  Parser.parseLesson(input3));
    }

    @Test
    void parseLesson_inputWithInvalidTime_expectInvalidTimeFormatException() {
        String input1 = "lesson online lecture CS2113T /on 2 25:00 20:00";
        assertThrows(InvalidTimeFormatException.class, () -> Parser.parseLesson(input1));
        String input2 = "lesson online lecture CS2113T /on 2 10:000 20:00";
        assertThrows(InvalidTimeFormatException.class, () -> Parser.parseLesson(input2));
        String input3 = "lesson online lecture CS2113T /on 2 2s:aa 20:00";
        assertThrows(InvalidTimeFormatException.class, () -> Parser.parseLesson(input3));
        String input4 = "lesson online lecture CS2113T /on 2 10:00 aa:aa";
        assertThrows(InvalidTimeFormatException.class, () -> Parser.parseLesson(input4));
        String input5 = "lesson online lecture CS2113T /on 2 12:69 20:00";
        assertThrows(InvalidTimeFormatException.class, () -> Parser.parseLesson(input5));
        String input6 = "lesson online lecture CS2113T /on 2 12:00 20:69";
        assertThrows(InvalidTimeFormatException.class, () -> Parser.parseLesson(input6));
        String input7 = "lesson online lecture CS2113T /on 2 12:00 20:00a";
        assertThrows(InvalidTimeFormatException.class, () -> Parser.parseLesson(input7));
        String input8 = "lesson online lecture CS2113T /on 2 12:00 30:00";
        assertThrows(InvalidTimeFormatException.class, () -> Parser.parseLesson(input8));
        String input9 = "lesson online lecture CS2113T /on 2 12:00 20:10";
        assertThrows(InvalidTimeFormatException.class, () -> Parser.parseLesson(input9));
        String input10 = "lesson online lecture CS2113T /on 2 12:01 20:00";
        assertThrows(InvalidTimeFormatException.class, () -> Parser.parseLesson(input10));
    }

    @Test
    void parseLesson_lessonWithWrongTimeFormat_expectException() {
        String input = "lesson online lecture CS2113T /on 4 18:000 20:00";
        assertThrows(InvalidTimeFormatException.class, () -> Parser.parseLesson(input));
    }


    @Test
    void verifyModuleCode_validModule_returnsTrue() {
        String moduleCode1 = "MA2101";
        assertTrue(Parser.verifyModuleCode(moduleCode1));
        String moduleCode2 = "CS2113T";
        assertTrue(Parser.verifyModuleCode(moduleCode2));
        String moduleCode3 = "DSA4211";
        assertTrue(Parser.verifyModuleCode(moduleCode3));
    }

    @Test
    void verifyModuleCode_invalidModuleWithAt_returnsFalse() {
        String moduleCode = "MA2101/at";
        assertFalse(Parser.verifyModuleCode(moduleCode));
    }

    @Test
    void verifyModuleCode_invalidModule_returnsFalse() {
        String moduleCode1 = "A1234";
        assertFalse(Parser.verifyModuleCode(moduleCode1));
        String moduleCode2 = "CS2113TTTT";
        assertFalse(Parser.verifyModuleCode(moduleCode2));
        String moduleCode3 = "CS99999";
        assertFalse(Parser.verifyModuleCode(moduleCode3));
        String moduleCode4 = "CS21a3T";
        assertFalse(Parser.verifyModuleCode(moduleCode4));
        String moduleCode5 = "dsa4211";
        assertFalse(Parser.verifyModuleCode(moduleCode5));
    }

    @Test
    void parseLesson_validLesson_returnsTrue() throws
            EmptyArgumentException, MissingLessonTimingException, InvalidModuleCodeException,
            InvalidTimeFormatException, InvalidFrequencyException, StartAndEndTimeSameException,
            MissingLessonDescriptionException, StartTimeAndEndTimeTooEarlyException,
            StartTimeIsAfterEndTimeException {
        String input = "lesson online lecture CS2113 /on 5 16:00 18:00";
        Lesson expectedLesson = Parser.parseLesson(input);
        Lesson actualLesson = new Lesson("online lecture", "CS2113",
                5, "16:00", "18:00");
        Lesson wrongLesson = new Lesson("online lecture ", "CS2113",
                5, "16:00", "18:00");
        assertEquals(expectedLesson, actualLesson);
        assertFalse(expectedLesson.equals(wrongLesson));
    }

    @Test
    void validateEvent_validEvent_returnsTrue() throws MissingEventDateAndTimeDetailsException,
            MissingEventDescriptionException, MissingModuleCodeOrInvalidModuleCodeException, InvalidDateException,
            StartTimeIsAfterEndTimeException, InvalidTimeFormatException, EmptyArgumentException, StartAndEndTimeSameException,
            StartTimeAndEndTimeTooEarlyException, WrongDateFormatException {
        String input1 = "event CS2113 final exam /at 2021-05-03 14:00 16:00 LT14";
        Event expectedEvent = Parser.validateEvent(input1);
        Event actualEvent = new Event("final exam", "CS2113",
                "LT14", "14:00", "16:00", "2021-05-03");
        assertEquals(LocalDate.of(2021, 5, 3), expectedEvent.getDate());
        assertEquals(expectedEvent.getDescription(), actualEvent.getDescription());
        assertEquals(expectedEvent, actualEvent);
    }

    @Test
    void validateDeadline_validDeadlineLineInput_returnsDeadline() throws InvalidDateException,
            WrongDateFormatException, MissingDeadlineTimingDetailsException,
            EmptyArgumentException, InvalidModuleCodeException, MissingDeadlineDescriptionException {
        String input = "deadline CS2113 TP version 1 /by 2021-04-04";
        Deadline deadline = Parser.validateDeadline(input);
        assertEquals(deadline.getModuleCode(), "CS2113");
        assertEquals(LocalDate.of(2021, 4, 4), deadline.getDate());
        assertEquals(deadline.getDescription(), "TP version 1");
    }

    @Test
    void validateDeadline_deadlineInputWithoutDescription_exceptException() {
        String input = "deadline   ";
        assertThrows(EmptyArgumentException.class, () -> Parser.validateDeadline(input));
    }

    @Test
    void validateDeadline_deadlineWithoutDescription_expectMissingDeadlineDescriptionException() {
        String input1 = "deadline CS2113 /by 2021-04-04";
        assertThrows(MissingDeadlineDescriptionException.class, () -> Parser.validateDeadline(input1));
        String input2 = "deadline CS2113/by 2021-04-04";
        assertThrows(MissingDeadlineDescriptionException.class, () -> Parser.validateDeadline(input2));
        String input3 = "deadline /by 2021-04-04";
        assertThrows(MissingDeadlineDescriptionException.class, () -> Parser.validateDeadline(input3));
    }

    @Test
    void validateDeadline_deadlineWithInvalidModuleCode_expectInvalidModuleCodeException() {
        String input1 = "deadline CS2113TTTT TP /by 2021-04-04";
        assertThrows(InvalidModuleCodeException.class, () -> Parser.validateDeadline(input1));
        String input2 = "deadline CS2113a TP /by 2021-04-04";
        assertThrows(InvalidModuleCodeException.class, () -> Parser.validateDeadline(input2));
        String input3 = "deadline ka TP /by 2021-04-04";
        assertThrows(InvalidModuleCodeException.class, () -> Parser.validateDeadline(input3));
        String input4 = "deadline CAKSKS TP /by 2021-04-04";
        assertThrows(InvalidModuleCodeException.class, () -> Parser.validateDeadline(input4));
        String input5 = "deadline 000000 TP /by 2021-04-04";
        assertThrows(InvalidModuleCodeException.class, () -> Parser.validateDeadline(input5));
        String input6 = "deadline aa2222 TP /by 2021-04-04";
        assertThrows(InvalidModuleCodeException.class, () -> Parser.validateDeadline(input6));
        String input7 = "deadline aa2222s TP /by 2021-04-04";
        assertThrows(InvalidModuleCodeException.class, () -> Parser.validateDeadline(input7));

    }

    @Test
    void validateDeadline_deadlineWithWrongDateFormat_expectWrongDateFormatException() {
        String input1 = "deadline CS2113 TP /by 2021-00-00";
        assertThrows(WrongDateFormatException.class, () -> Parser.validateDeadline(input1));
        String input2 = "deadline CS2113 TP /by 2021-13-01";
        assertThrows(WrongDateFormatException.class, () -> Parser.validateDeadline(input2));
        String input3 = "deadline CS2113 TP /by 2021-12-32";
        assertThrows(WrongDateFormatException.class, () -> Parser.validateDeadline(input3));
        String input4 = "deadline CS2113 TP /by 20211-04-04";
        assertThrows(WrongDateFormatException.class, () -> Parser.validateDeadline(input4));
        String input5 = "deadline CS2113 TP /by 202-04-04";
        assertThrows(WrongDateFormatException.class, () -> Parser.validateDeadline(input5));
        String input6 = "deadline CS2113 TP /by 2021-04-0a";
        assertThrows(WrongDateFormatException.class, () -> Parser.validateDeadline(input6));
        String input7 = "deadline CS2113 TP /by 2021-0a-04";
        assertThrows(WrongDateFormatException.class, () -> Parser.validateDeadline(input7));
        String input8 = "deadline CS2113 TP /by 202a-0a-04";
        assertThrows(WrongDateFormatException.class, () -> Parser.validateDeadline(input8));
        String input9 = "deadline CS2113 TP /by abcdefg";
        assertThrows(WrongDateFormatException.class, () -> Parser.validateDeadline(input9));
        String input10 = "deadline CS2113 TP /by 0";
        assertThrows(WrongDateFormatException.class, () -> Parser.validateDeadline(input10));
    }

    @Test
    void validateDeadline_inputWithoutProperDeadline_expectMissingDeadlineTimingDetailsException() {
        String input = "deadline MA3333 20-04-04";
        assertThrows(MissingDeadlineTimingDetailsException.class, () -> Parser.validateDeadline(input));
    }

    @Test
    void validateDeadline_inputWithDateOutsideRange_expectInvalidDateException() {
        String input1 = "deadline CS2113 TP version 1 /by 2021-10-10";
        String input2 = "deadline CS2113 TP version 1 /by 2019-10-10";
        assertThrows(InvalidDateException.class, () -> Parser.validateDeadline(input1));
        assertThrows(InvalidDateException.class, () -> Parser.validateDeadline(input2));
    }

    @Test
    void validateDeadline_validDeadline_returnsTrue() throws
            WrongDateFormatException, InvalidDateException,
            EmptyArgumentException, MissingDeadlineTimingDetailsException, InvalidModuleCodeException,
            MissingDeadlineDescriptionException {
        String input = "deadline CS2113 TP version 1 /by 2021-04-04";
        Deadline expectedDeadline = Parser.validateDeadline(input);
        Deadline actualDeadline = new Deadline("CS2113", "TP version 1", "2021-04-04");
        assertEquals(expectedDeadline, actualDeadline);
    }

    @Test
    void validateEvent_validEventLineInput_returnsEvent() throws InvalidDateException,
            MissingEventDateAndTimeDetailsException, WrongDateFormatException, InvalidTimeFormatException,
            EmptyArgumentException, StartAndEndTimeSameException,
            MissingEventDescriptionException, StartTimeIsAfterEndTimeException, StartTimeAndEndTimeTooEarlyException,
            MissingModuleCodeOrInvalidModuleCodeException {
        String input = "event CS2113 final exam /at 2021-05-03 14:00 16:00 LT14";
        Event event = Parser.validateEvent(input);
        assertEquals(event.getDateOfEvent(), "2021-05-03");
        assertEquals(event.getDateOfEventInLocalDate(), LocalDate.of(2021, 5, 3));
        assertEquals(event.getEndTimeOfEvent(), "16:00");
        assertEquals(event.getAt(), "LT14");
        assertEquals(event.getModuleCode(), "CS2113");
        assertEquals(event.getDescription(), "final exam");
    }

    @Test
    void validateEvent_emptyEventInput_expectEmptyArgumentException() {
        String input = "event   ";
        assertThrows(EmptyArgumentException.class, () -> Parser.validateEvent(input));
    }

    @Test
    void validateEvent_eventWithInvalidModuleCode_expectMissingModuleCodeOrInvalidModuleCodeException() {
        String input1 = "event CS2113TTTT final exam /at 2021-05-03 14:00 16:00 LT14";
        assertThrows(MissingModuleCodeOrInvalidModuleCodeException.class, () -> Parser.validateEvent(input1));
        String input2 = "event CS2113a final exam /at 2021-05-03 14:00 16:00 LT14";
        assertThrows(MissingModuleCodeOrInvalidModuleCodeException.class, () -> Parser.validateEvent(input2));
        String input3 = "event k final exam /at 2021-05-03 14:00 16:00 LT14";
        assertThrows(MissingModuleCodeOrInvalidModuleCodeException.class, () -> Parser.validateEvent(input3));
        String input4 = "event 00000 final exam /at 2021-05-03 14:00 16:00 LT14";
        assertThrows(MissingModuleCodeOrInvalidModuleCodeException.class, () -> Parser.validateEvent(input4));
        String input5 = "event aa2222 final exam /at 2021-05-03 14:00 16:00 LT14";
        assertThrows(MissingModuleCodeOrInvalidModuleCodeException.class, () -> Parser.validateEvent(input5));
        String input6 = "event aa2222s final exam /at 2021-05-03 14:00 16:00 LT14";
        assertThrows(MissingModuleCodeOrInvalidModuleCodeException.class, () -> Parser.validateEvent(input6));
        String input7 = "event CAHSAS2 final exam /at 2021-05-03 14:00 16:00 LT14";
        assertThrows(MissingModuleCodeOrInvalidModuleCodeException.class, () -> Parser.validateEvent(input7));
    }

    @Test
    void validateEvent_eventWithoutDescription_expectMissingEventDescriptionException() {
        String input1 = "event CS2113T /at 2021-05-03 14:00 16:00 LT14";
        assertThrows(MissingEventDescriptionException.class, () ->  Parser.validateEvent(input1));
        String input2 = "event CS2113T/at 2021-05-03 14:00 16:00 LT14";
        assertThrows(MissingEventDescriptionException.class, () ->  Parser.validateEvent(input2));
    }

    @Test
    void validateEvent_eventWithWrongDateFormat_expectWrongDateFormatException() {
        String input1 = "event CS2113T final exam /at 2021-00-00 14:00 16:00 LT14";
        assertThrows(WrongDateFormatException.class, () -> Parser.validateEvent(input1));
        String input2 = "event CS2113T final exam /at 2021-13-01 14:00 16:00 LT14";
        assertThrows(WrongDateFormatException.class, () -> Parser.validateEvent(input2));
        String input3 = "event CS2113T final exam /at 2021-12-32 14:00 16:00 LT14";
        assertThrows(WrongDateFormatException.class, () -> Parser.validateEvent(input3));
        String input4 = "event CS2113T final exam /at 20211-04-04 14:00 16:00 LT14";
        assertThrows(WrongDateFormatException.class, () -> Parser.validateEvent(input4));
        String input5 = "event CS2113T final exam /at 202-04-04 14:00 16:00 LT14";
        assertThrows(WrongDateFormatException.class, () -> Parser.validateEvent(input5));
        String input6 = "event CS2113T final exam /at 2021-04-0a 14:00 16:00 LT14";
        assertThrows(WrongDateFormatException.class, () -> Parser.validateEvent(input6));
        String input7 = "event CS2113T final exam /at 2021-0a-04 14:00 16:00 LT14";
        assertThrows(WrongDateFormatException.class, () -> Parser.validateEvent(input7));
        String input8 = "event CS2113T final exam /at 202a-0a-04 14:00 16:00 LT14";
        assertThrows(WrongDateFormatException.class, () -> Parser.validateEvent(input8));
        String input9 = "event CS2113T final exam /at abcdefg 14:00 16:00 LT14";
        assertThrows(WrongDateFormatException.class, () -> Parser.validateEvent(input9));
        String input10 = "event CS2113T final exam /at 0 14:00 16:00 LT14";
        assertThrows(WrongDateFormatException.class, () -> Parser.validateEvent(input10));
    }

    @Test
    void validateEvent_eventWithInvalidStartOrEndTime_expectInvalidTimeFormatException() {
        String input1 = "event CS2113 final exam /at 2021-05-03 25:00 20:00 LT14";
        assertThrows(InvalidTimeFormatException.class, () -> Parser.validateEvent(input1));
        String input2 = "event CS2113 final exam /at 2021-05-03 10:000 20:00 LT14";
        assertThrows(InvalidTimeFormatException.class, () -> Parser.validateEvent(input2));
        String input3 = "event CS2113 final exam /at 2021-05-03 2s:aa 20:00 LT14";
        assertThrows(InvalidTimeFormatException.class, () -> Parser.validateEvent(input3));
        String input4 = "event CS2113 final exam /at 2021-05-03 10:00 aa:aa LT14";
        assertThrows(InvalidTimeFormatException.class, () -> Parser.validateEvent(input4));
        String input5 = "event CS2113 final exam /at 2021-05-03 12:69 20:00 LT14";
        assertThrows(InvalidTimeFormatException.class, () -> Parser.validateEvent(input5));
        String input6 = "event CS2113 final exam /at 2021-05-03 12:00 20:69 LT14";
        assertThrows(InvalidTimeFormatException.class, () -> Parser.validateEvent(input6));
        String input7 = "event CS2113 final exam /at 2021-05-03 12:00 20:00a LT14";
        assertThrows(InvalidTimeFormatException.class, () -> Parser.validateEvent(input7));
        String input8 = "event CS2113 final exam /at 2021-05-03 12:00 30:00 LT14";
        assertThrows(InvalidTimeFormatException.class, () -> Parser.validateEvent(input8));
        String input9 = "event CS2113 final exam /at 2021-05-03 12:00 20:60 LT14";
        assertThrows(InvalidTimeFormatException.class, () -> Parser.validateEvent(input9));
        String input10 = "event CS2113 final exam /at 2021-05-03 12:01 20:00 LT14";
        assertThrows(InvalidTimeFormatException.class, () -> Parser.validateEvent(input10));
        String input11 = "event CS2113 final exam /at 2021-05-03 12:00 20:10 LT14";
        assertThrows(InvalidTimeFormatException.class, () -> Parser.validateEvent(input11));
    }

    @Test
    void validateEvent_eventWithoutModuleCOde_expectMissingModuleCodeOrInvalidModuleCodeException() {
        String input1 = "event final exam/at 2021-05-03 14:00 16:00 LT14";
        assertThrows(MissingModuleCodeOrInvalidModuleCodeException.class, () -> Parser.validateEvent(input1));
        String input2 = "event final exam /at 2021-05-03 14:00 16:00 LT14";
        assertThrows(MissingModuleCodeOrInvalidModuleCodeException.class, () -> Parser.validateEvent(input2));
    }

    @Test
    void validateEvent_missingAtEventInput_expectMissingEventDateAndTimeDetailsException() {
        String input1 = "event CS2113 final exam 2021-05-03 14:00 16:00 LT14";
        assertThrows(MissingEventDateAndTimeDetailsException.class, () -> Parser.validateEvent(input1));
        String input2 = "event CS2113final exam2021-05-03 14:00 16:00 LT14";
        assertThrows(MissingEventDateAndTimeDetailsException.class, () -> Parser.validateEvent(input2));
        String input3 = "event CS2113 final exam /at 2021-05-03 14:00 16:00";
        assertThrows(MissingEventDateAndTimeDetailsException.class, () -> Parser.validateEvent(input3));
    }

    @Test
    void validateEvent_missingEventDateTimeDetails_expectMissingEventDateAndTimeDetailsException() {
        String input = "event CS2113 final exam /at 2021-05-03 14:00 LT14";
        assertThrows(MissingEventDateAndTimeDetailsException.class, () -> Parser.validateEvent(input));
    }

    @Test
    void validateEvent_eventWithSameStartAndEndTime_expectStartAndEndTimeSameException() {
        String input1 = "event CS2113 final exam /at 2021-05-03 14:00 14:00 LT14";
        assertThrows(StartAndEndTimeSameException.class, () -> Parser.validateEvent(input1));
        String input2 = "event CS2113 final exam /at 2021-05-03 10:00 10:00 LT14";
        assertThrows(StartAndEndTimeSameException.class, () -> Parser.validateEvent(input2));
    }

    @Test
    void validateEvent_eventWithStartTimeAfterEndTime_expectStartTimeIsAfterEndTimeException() {
        String input1 = "event CS2113 final exam /at 2021-05-03 09:00 08:00 LT14";
        assertThrows(StartTimeIsAfterEndTimeException.class, () -> Parser.validateEvent(input1));
        String input2 = "event CS2113 final exam /at 2021-05-03 23:00 00:00 LT14";
        assertThrows(StartTimeIsAfterEndTimeException.class, () -> Parser.validateEvent(input2));
        String input3 = "event CS2113 final exam /at 2021-05-03 23:00 01:00 LT14";
        assertThrows(StartTimeIsAfterEndTimeException.class, () -> Parser.validateEvent(input3));
    }

    @Test
    void validateEvent_eventWithStartOrEndTimeTooEarly_expectStartTimeAndEndTimeTooEarlyException() {
        String input1 = "event CS2113 final exam /at 2021-05-03 00:00 08:00 LT14";
        assertThrows(StartTimeAndEndTimeTooEarlyException.class, () -> Parser.validateEvent(input1));
        String input2 = "event CS2113 final exam /at 2021-05-03 01:00 08:00 LT14";
        assertThrows(StartTimeAndEndTimeTooEarlyException.class, () -> Parser.validateEvent(input2));
        String input3 = "event CS2113 final exam /at 2021-05-03 04:00 05:00 LT14";
        assertThrows(StartTimeAndEndTimeTooEarlyException.class, () -> Parser.validateEvent(input3));
    }

    @Test
    void validateEvent_invalidEventDate_expectException() {
        String input1 = "event CS2113 final exam /at 2021-10-03 12:00 14:00 LT14";
        assertThrows(InvalidDateException.class, () -> Parser.validateEvent(input1));
        String input2 = "event CS2113 final exam /at 2018-10-03 12:00 14:00 LT14";
        assertThrows(InvalidDateException.class, () -> Parser.validateEvent(input2));
    }

    @Test
    void validateEvent_invalidEventTime_expectInvalidTimeFormatException() {
        String input1 = "event CS2113 final exam /at 2021-05-03 12:002 14:00 LT14";
        assertThrows(InvalidTimeFormatException.class, () -> Parser.validateEvent(input1));
        String input2 = "event CS2113 final exam /at 2021-05-03 12:00 14:003 LT14";
        assertThrows(InvalidTimeFormatException.class, () -> Parser.validateEvent(input2));
        String input3 = "event CS2113 final exam /at 2021-05-03 25:19 14:00 LT14";
        assertThrows(InvalidTimeFormatException.class, () -> Parser.validateEvent(input3));
        String input4 = "event CS2113 final exam /at 2021-05-03 12:00 bbbbs LT14";
        assertThrows(InvalidTimeFormatException.class, () -> Parser.validateEvent(input4));
    }
}
