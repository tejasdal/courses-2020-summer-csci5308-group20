package CSCI5308.GroupFormationTool.Question;

import java.sql.SQLException;
import java.util.List;

public interface IQuestionPersistence {

    public boolean addQuestion(Question question) throws SQLException;
    public boolean deleteQuestion(Long questionId) throws SQLException;
    public List<Question> getAllQuestionsForUser(Long userId);
}
