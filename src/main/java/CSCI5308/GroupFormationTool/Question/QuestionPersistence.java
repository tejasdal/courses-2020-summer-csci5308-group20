package CSCI5308.GroupFormationTool.Question;

import java.sql.SQLException;
import java.util.List;

public class QuestionPersistence implements IQuestionPersistence {

    @Override
    public boolean addQuestion(Question question) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteQuestion(Long questionId) throws SQLException {
        return false;
    }

    @Override
    public List<Question> getAllQuestionsForUser(Long userId) throws SQLException {
        return null;
    }
}
