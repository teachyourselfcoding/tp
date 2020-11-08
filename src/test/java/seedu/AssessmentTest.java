package seedu;

import org.junit.jupiter.api.Test;
import seedu.command.AddAssessmentCommand;
import seedu.command.DeleteAssessmentCommand;
import seedu.exception.EmptyArgumentException;
import seedu.exception.InvalidArgumentsException;
import seedu.exception.InvalidScoreException;
import seedu.exception.ModuleAlreadyExistsException;
import seedu.exception.ModuleDoesNotExistException;
import seedu.module.Assessment;
import seedu.module.Module;

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

    @Test
    void AddAssessmentCommandExecute_invalidModule_throwsModuleNotExistsException() {
        ModuleManager moduleManager = new ModuleManager();
        ScheduleManager scheduleManager = new ScheduleManager();
        Ui ui = new Ui();
        try {
            AddAssessmentCommand a = new AddAssessmentCommand("test", "100", "CZ2003");
            assertThrows(ModuleDoesNotExistException.class, () -> a.execute(scheduleManager, moduleManager, ui));
        } catch (InvalidArgumentsException e) {
            e.printStackTrace();
        }
    }

    @Test
    void DeleteAssessmentCommandExecute_NoSuchAssessmentAndModule() {
        ModuleManager moduleManager = new ModuleManager();
        ScheduleManager scheduleManager = new ScheduleManager();
        Ui ui = new Ui();
        Module module = new Module("CS2113");
        try {
            moduleManager.addModule(module);
            DeleteAssessmentCommand d1 = new DeleteAssessmentCommand("CS2113", "test");
            d1.execute(scheduleManager, moduleManager, ui);
            DeleteAssessmentCommand d2 = new DeleteAssessmentCommand("CS2113T", "test");
            assertThrows(ModuleDoesNotExistException.class, () -> d2.execute(scheduleManager, moduleManager, ui));
        } catch (ModuleAlreadyExistsException | ModuleDoesNotExistException | EmptyArgumentException |
                InvalidScoreException e) {
            e.printStackTrace();
        }
    }


    @Test
    void ParserValidateAddCommand_wrongSyntax_throwsInvalidArgumentsException() {
        assertThrows(InvalidArgumentsException.class, () -> Parser.validateAddAssessmentCommand("assessment "));
        assertThrows(InvalidArgumentsException.class, () -> Parser.validateAddAssessmentCommand("assessment 100"));
    }

    @Test
    void ParserValidateDeleteCommand_wrongSyntax_throwsInvalidArgumentsException() {
        assertThrows(InvalidArgumentsException.class, () -> Parser.validateDeleteAssessmentCommand("delete_assessment "));
    }
}
