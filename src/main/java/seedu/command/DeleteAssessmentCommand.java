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


public class DeleteAssessmentCommand extends Command {
    String moduleCode;
    String title;

    public DeleteAssessmentCommand(String moduleCode, String title) {
        this.moduleCode = moduleCode;
        this.title = title.strip();
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
                    module.assessments.remove(i);
                    Storage.getStorage().exportData(moduleManager, module.getModuleCode());
                    Ui.printSuccessfulAssessmentDelete(this.title);
                    return;
                }
            }
            Ui.printNoSuchAssessment(title, moduleCode);
        } catch (ModuleNotExistsException e) {
            throw new ModuleDoesNotExistException();
        }
    }
}
