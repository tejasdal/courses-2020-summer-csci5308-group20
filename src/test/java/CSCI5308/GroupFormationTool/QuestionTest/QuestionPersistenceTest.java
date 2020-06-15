package CSCI5308.GroupFormationTool.QuestionTest;

import CSCI5308.GroupFormationTool.Question.IQuestionPersistence;
import CSCI5308.GroupFormationTool.Question.Question;
import CSCI5308.GroupFormationTool.Question.QuestionOption;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuestionPersistenceMock implements IQuestionPersistence {

    @Override
    public boolean addQuestion(Question question) throws SQLException {
        return true;
    }

    @Override
    public boolean deleteQuestion(Long questionId) {
        return true;
    }

    @Override
    public List<Question> getAllQuestionsForUser(Long userId, String sortBy) {
        List<Question> questions = new ArrayList<>();
        List<QuestionOption> questionOptions = new ArrayList<>();
        questionOptions.add(new QuestionOption(1L, "Option 1", 1));
        questionOptions.add(new QuestionOption(2L, "Option 2", 2));
        questionOptions.add(new QuestionOption(3L, "Option 3", 3));
        questions.add(new Question(1L, "Test Question 1", "What is Test Question 1?", 1L,
                Question.NUMERIC, new Date(System.currentTimeMillis()), new ArrayList<>()));
        questions.add(new Question(2L, "Test Question 2", "What is Test Question 2?", 1L,
                Question.FREE_TEXT, new Date(System.currentTimeMillis()), new ArrayList<>()));
        questions.add(new Question(3L, "Test Question 3", "What is Test Question 3?", 1L,
                Question.MULTIPLE_CHOICE_CHOOSE_ONE, new Date(System.currentTimeMillis()), questionOptions));
        return questions;
    }
}