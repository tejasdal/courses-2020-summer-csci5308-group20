package CSCI5308.GroupFormationTool.QuestionTest;

import CSCI5308.GroupFormationTool.Question.IQuestionPersistence;
import CSCI5308.GroupFormationTool.Question.Question;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

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
        boolean isCreated = questionPersistence.addQuestion(question);
        Assert.isTrue(isCreated, "Failed to create a new Question!");
    }

    @Test
    void getAllQuestionOfInstructor() {
        IQuestionPersistence questionPersistence = new QuestionPersistenceMock();
        List<Question> questionList = questionPersistence.getAllQuestionsForUser(1L,"date");
        assertEquals(3,questionList.size());

    }


    @Test
    void deleteQuestion() {
        IQuestionPersistence questionPersistence = new QuestionPersistenceMock();
        boolean status = questionPersistence.deleteQuestion(1L);
        assertTrue(status);


    }
}