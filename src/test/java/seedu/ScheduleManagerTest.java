package seedu;

import org.junit.jupiter.api.Test;
import seedu.exception.InvalidDateException;
import seedu.exception.InvalidDateRangeException;
import seedu.exception.InvalidStartEndDateException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleManagerTest {
    //Three part name for a test: methodUnderTest_inputGiven_expectedOutput
    @Test
    void display_longAgoDate_expectExceptions() {
        ScheduleManager sm = new ScheduleManager();
        assertThrows(InvalidDateException.class,()->{ sm.displayDate(LocalDate.parse("2010-10-10"));});
    }

    @Test
    void display_startAndEndDateSwap_expectExceptions() {
        ScheduleManager sm = new ScheduleManager();
        assertThrows(InvalidStartEndDateException.class,()->{
            sm.display(LocalDate.parse("2020-11-28"),LocalDate.parse("2020-10-20"));});
    }




}