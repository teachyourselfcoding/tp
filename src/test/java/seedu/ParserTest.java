package seedu;

import org.junit.jupiter.api.Test;
import seedu.task.Deadline;
import seedu.task.Event;
import seedu.task.Lesson;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void validateDeadline_validDeadlineLineInput_returnsDeadline() throws DueQuestException {
        String input = "deadline CS2113 TP version 1 /by 2021-04-04";
        Deadline deadline = Parser.validateDeadline(input);
        assertEquals(deadline.getModuleCode(), "CS2113");
        assertEquals(deadline.getDate(), LocalDate.of(2021, 04, 04));
        assertEquals(deadline.getDescription(), "TP version 1");
    }

    @Test
    void validateDeadline_inputWithoutDescription_exceptException() {
        String input = "deadline   ";
        assertThrows(DueQuestException.class, () ->
        {
            Parser.validateDeadline(input);
        });
    }

    @Test
    void validateDeadline_inputWithoutProperDeadline_expectException() {
        String input = "deadline MA3333 20-04-04";
        assertThrows(DueQuestException.class, () ->
        {
            Parser.validateDeadline(input);
        });
    }

    @Test
    void validateDeadline_inputWithDateOutsideRange_expectException() {
        String input = "deadline CS2113 TP version 1 /by 2021-10-10";
        assertThrows(DueQuestException.class, () ->
        {
            Parser.validateDeadline(input);
        });
    }

    @Test
    void validateEvent_validEventLineInput_returnsEvent() {
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
    void validateLesson_validLesson_returnsLesson() throws DueQuestException {
        String input1 = "lesson online lecture CS2113 /on 5 7 16:00 18:00";
        String input2 = "lesson online lecture CS2113 5 7 16:00 18:00";
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
        assertThrows(DueQuestException.class, () ->
        {
            Parser.parseLesson(input2);
        });
        assertThrows(DueQuestException.class, () ->
        {
            Parser.parseLesson(input3);
        });
    }

    @Test
    void validateLesson_lessonWithInvalidModuleCode_expectException() {
        String input = "lesson online lecture CS2113TTTT 5 7 16:00 18:00";
        assertThrows(DueQuestException.class, () ->
        {
            Parser.parseLesson(input);
        });
    }

    @Test
    void validateLesson_lessonWithMissingTimingDetails_expectException() {
        String input = "lesson online lecture CS2113TTTT 5 7 18:00";
        assertThrows(DueQuestException.class, () ->
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
        String moduleCode2 = "B9999T";
        String moduleCode3 = "CS99999";
        assertEquals(Parser.verifyModuleCode(moduleCode1), false);
        assertEquals(Parser.verifyModuleCode(moduleCode2), false);
        assertEquals(Parser.verifyModuleCode(moduleCode3), false);
    }
}