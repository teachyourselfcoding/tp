package seedu.module;

import seedu.exception.EmptyArgumentException;
import seedu.exception.InvalidScoreException;

/**
 * Class represents assessments, including the title and score.
 */
public class Assessment {
	public String title;
	private float attemptScore = 0;
	private final float fullScore;

	/**
	 * Constructs assessment instance.
	 * @param title the title of the assessment (e.g. Assignment 3)
	 * @param fullScore the full score of the assessment, which cannot be modified.
	 * @throws EmptyArgumentException if the title is empty string
	 * @throws InvalidScoreException if the full score is non-positive
	 */
	public Assessment(String title, float fullScore) throws EmptyArgumentException, InvalidScoreException{
		if (title.isEmpty()) {
			throw new EmptyArgumentException();
		} else {
			this.title = title;
		}
		if (fullScore <= 0) {
			throw new InvalidScoreException();
		} else {
			this.fullScore = fullScore;
		}
	}

	/**
	 * Set attempt score for the assessment.
	 * @param attemptScore the actual score
	 * @throws InvalidScoreException if the attempt score is larger than the full score or negative
	 */
	public void setAttemptScore(float attemptScore) throws InvalidScoreException{
		if (attemptScore <0 || attemptScore > this.fullScore) {
			throw new InvalidScoreException();
		} else {
			this.attemptScore = attemptScore;
		}
	}

	public float getAttemptScore() {
		return attemptScore;
	}

	public float getFullScore() {
		return fullScore;
	}

	@Override
	public String toString() {
		return  String.format("%s(%f/%f)", this.title, this.attemptScore, this.fullScore);
	}
}
