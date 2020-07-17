package CSCI5308.GroupFormationTool.Question;

import java.sql.SQLException;
import java.util.List;

public interface IQuestionPersistence {

    public boolean createQuestion(IQuestion question) throws SQLException;
    public boolean deleteQuestion(Long questionId);
    public List<IQuestion> getAllUserQuestions(Long userId);
}
