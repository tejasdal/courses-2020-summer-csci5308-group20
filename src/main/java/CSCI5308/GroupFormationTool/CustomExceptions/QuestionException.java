package CSCI5308.GroupFormationTool.CustomExceptions;

public class QuestionException extends Exception {

    public QuestionException() {
        super();
    }

    public QuestionException(String message) {
        super(message);
    }

    public QuestionException(String message, Throwable cause) {
        super(message, cause);
    }

    public QuestionException(Throwable cause) {
        super(cause);
    }

    protected QuestionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
