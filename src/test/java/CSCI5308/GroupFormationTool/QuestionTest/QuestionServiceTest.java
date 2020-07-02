package CSCI5308.GroupFormationTool.QuestionTest;

import CSCI5308.GroupFormationTool.Question.IQuestionPersistence;
import CSCI5308.GroupFormationTool.Question.Question;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class QuestionServiceTest {

    @Test
    void createQuestion() throws SQLException {
        IQuestionPersistence questionPersistence = new QuestionPersistenceMock();
        Question question = new Question(1L, "Test Question", "What is Test Question?", 1L,
                Question.NUMERIC, new Date(System.currentTimeMillis()), new ArrayList<>());
        boolean isCreated = questionPersistence.createQuestion(question);
        assertTrue(isCreated, "Test case failed!");
        assertTrue(isCreated,"Negative test case failed");
    }

    @Test
    void getAllUserQuestionsTest() {
        IQuestionPersistence questionPersistence = new QuestionPersistenceMock();
        List<Question> questionList = questionPersistence.getAllUserQuestions(1L);
        assertEquals(3,questionList.size());

    }


    @Test
    void deleteQuestionTest() {
        IQuestionPersistence questionPersistence = new QuestionPersistenceMock();
        boolean status = questionPersistence.deleteQuestion(1L);
        assertTrue(status);


    }
}