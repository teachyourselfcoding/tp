package seedu;

import org.junit.jupiter.api.Test;
import seedu.exception.EmptyArgumentException;
import seedu.exception.InvalidScoreException;
import seedu.module.Assessment;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class AssessmentTest {
    @Test
    void Constructor_InvalidInput_throwsException() {
        assertThrows(EmptyArgumentException.class, () -> {
            new Assessment(null, 5);
        });
        assertThrows(EmptyArgumentException.class, () -> {
            new Assessment("", 5);
        });
        assertThrows(EmptyArgumentException.class, () -> {
            new Assessment("     ", 5);
        });
        assertThrows(InvalidScoreException.class, () -> {
            new Assessment("test", 0);
        });
        assertThrows(InvalidScoreException.class, () -> {
            new Assessment("test", -5);
        });
    }

    @Test
    void setAttemptScore_InvalidScore_throwsInvalidScoreException() {
        try {
            Assessment a = new Assessment("test", 100);
            assertThrows(InvalidScoreException.class, () -> {
                a.setAttemptScore(-10);
            });
            assertThrows(InvalidScoreException.class, () -> {
                a.setAttemptScore(100.1);
            });
        } catch (EmptyArgumentException e) {
            e.printStackTrace();
        } catch (InvalidScoreException e) {
            e.printStackTrace();
        }
    }

    @Test
    void setAttemptScore_validScore_successfullyAdded() {
        try {
            Assessment a = new Assessment("test", 100);
            a.setAttemptScore(0);
            a.setAttemptScore(100.0);
        } catch (EmptyArgumentException e) {
            e.printStackTrace();
        } catch (InvalidScoreException e) {
            e.printStackTrace();
        }
    }
}
