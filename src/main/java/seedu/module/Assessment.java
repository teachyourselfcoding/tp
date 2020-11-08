package seedu.module;

import seedu.exception.EmptyArgumentException;
import seedu.exception.InvalidScoreException;

/**
 * Class represents assessments, including the title and score.
 */
public class Assessment {
    public String title;
    private double attemptScore = 0;
    private final double fullScore;

    /**
     * Constructs assessment instance.
     *
     * @param title     the title of the assessment (e.g. Assignment 3)
     * @param fullScore the full score of the assessment, which cannot be modified.
     * @throws EmptyArgumentException if the title is empty string
     * @throws InvalidScoreException  if the full score is non-positive
     */
    public Assessment(String title, double fullScore) throws EmptyArgumentException, InvalidScoreException {
        if (title == null || title.isEmpty() || title.trim().isEmpty()) {
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
     *
     * @param attemptScore the actual score
     * @throws InvalidScoreException if the attempt score is larger than the full score or negative
     */
    public void setAttemptScore(double attemptScore) throws InvalidScoreException {
        if (attemptScore < 0 || attemptScore > this.fullScore) {
            throw new InvalidScoreException();
        } else {
            this.attemptScore = attemptScore;
        }
    }

    public double getAttemptScore() {
        return attemptScore;
    }

    public double getFullScore() {
        return fullScore;
    }

    @Override
    public String toString() {
        return String.format("%s(%.2f/%.2f)", this.title, this.attemptScore, this.fullScore);
    }
}
