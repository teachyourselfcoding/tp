package seedu;
/**
 * DukeException is used to represent duke-specific exceptions that are likely
 * to occur
 */
public class DueQuestException extends Exception {
    DueQuestExceptionType exceptionType;

    public DueQuestExceptionType getExceptionType() {
        return exceptionType;
    }

    public DueQuestException(DueQuestExceptionType exceptionType){
        this.exceptionType=exceptionType;
    }
}
