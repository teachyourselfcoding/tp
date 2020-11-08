package seedu.command;

import seedu.ModuleManager;
import seedu.ScheduleManager;
import seedu.Storage;
import seedu.Ui;
import seedu.exception.EmptyArgumentException;
import seedu.exception.InvalidScoreException;
import seedu.exception.ModuleDoesNotExistException;
import seedu.exception.ModuleNotExistsException;
import seedu.module.Module;

public class ScoreAssessmentCommand extends Command {
    private final String moduleCode;
    private final String title;
    private final float attemptScore;

    public ScoreAssessmentCommand(String moduleCode, String title, String attemptScore) throws InvalidScoreException {
        this.moduleCode = moduleCode;
        this.title = title;
        try {
            this.attemptScore = Float.parseFloat(attemptScore);
        } catch (NumberFormatException e) {
            throw new InvalidScoreException();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(ScheduleManager scheduleManager, ModuleManager moduleManager, Ui ui)
            throws ModuleDoesNotExistException, EmptyArgumentException, InvalidScoreException {
        try {
            Module module = moduleManager.getModule(this.moduleCode);
            for (int i = 0; i < module.assessments.size(); i++) {
                if (module.assessments.get(i).title.equals(this.title)) {
                    module.assessments.get(i).setAttemptScore(this.attemptScore);
                    Storage.getStorage().exportData(moduleManager, module.getModuleCode());
                    return;
                }
            }
            Ui.printNoSuchAssessment(title, moduleCode);
        } catch (ModuleNotExistsException e) {
            throw new ModuleDoesNotExistException();
        }
    }
}
