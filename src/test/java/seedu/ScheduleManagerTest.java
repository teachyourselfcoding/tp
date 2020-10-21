package seedu;

import org.junit.jupiter.api.Test;
import seedu.exception.InvalidDateRangeException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleManagerTest {
    //Three part name for a test: methodUnderTest_inputGiven_expectedOutput
    @Test
    void display_longAgoDate_expectExceptions() {
        ScheduleManager sm = new ScheduleManager();
        assertThrows(InvalidDateRangeException.class,()->{ sm.displayDate(LocalDate.parse("2010-10-10"));});
    }


}