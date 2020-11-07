package seedu.command;

import seedu.ModuleManager;
import seedu.ScheduleManager;
import seedu.Storage;
import seedu.Ui;
import seedu.exception.EmptyArgumentException;
import seedu.exception.InvalidArgumentsException;
import seedu.exception.InvalidScoreException;
import seedu.exception.ModuleDoesNotExistException;
import seedu.exception.ModuleNotExistsException;
import seedu.module.Assessment;
import seedu.module.Module;

/**
 * Class represents the command of adding a new assessment.
 */
public class AddAssessmentCommand extends Command {
	public String title;
	private float fullScore;
	private String moduleCode;

	/**
	 * Constructor.
	 * @param title title of the assessment.
	 * @param fullScore score of the assessment
	 * @param moduleCode modue code of the assessment.
	 * @throws InvalidArgumentsException if there is no float number to parse from fullScore.
	 */
	public AddAssessmentCommand(String title, String fullScore, String moduleCode) throws InvalidArgumentsException {
		this.title = title;
		this.moduleCode = moduleCode;
		try {
			this.fullScore = Float.parseFloat(fullScore);
		} catch (NumberFormatException e) {
			throw new InvalidArgumentsException();
		}
	}

	@Override
	public boolean isExit() {
		return false;
	}

	@Override
	public void execute(ScheduleManager scheduleManager, ModuleManager moduleManager, Ui ui)
			throws ModuleDoesNotExistException,
			EmptyArgumentException, InvalidScoreException {
		try {
			Module module = moduleManager.getModule(this.moduleCode);
			Assessment assessment = new Assessment(this.title, this.fullScore);
			module.addAssessment(assessment);
			Storage.getStorage().exportData(moduleManager, this.moduleCode);
		} catch (ModuleNotExistsException e) {
			throw new ModuleDoesNotExistException();
		} catch (EmptyArgumentException e) {
			throw new EmptyArgumentException();
		} catch (InvalidScoreException e) {
			throw new InvalidScoreException();
		}
	}
}
