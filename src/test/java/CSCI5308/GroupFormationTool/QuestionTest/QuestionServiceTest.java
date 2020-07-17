package CSCI5308.GroupFormationTool.QuestionTest;

import CSCI5308.GroupFormationTool.Question.IQuestion;
import CSCI5308.GroupFormationTool.Question.IQuestionPersistence;
import CSCI5308.GroupFormationTool.Question.Question;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class QuestionServiceTest {

    private static IQuestionPersistence questionPersistence;
    private static IQuestion question;

    @BeforeAll
    public static void setUp(){
        QuestionTestAbstractFactory questionTestAbstractFactory = new QuestionTestConcreteFactory();
        questionPersistence = questionTestAbstractFactory.makeQuestionPersistence();
        question = questionTestAbstractFactory.makeQuestion();
    }

    @Test
    void createQuestion() throws SQLException {
        boolean isCreated = questionPersistence.createQuestion(question);
        assertTrue(isCreated, "Test case failed!");
        assertTrue(isCreated,"Negative test case failed");
    }

    @Test
    void getAllUserQuestionsTest() {
        List<IQuestion> questionList = questionPersistence.getAllUserQuestions(1L);
        assertEquals(3,questionList.size());

    }

    @Test
    void deleteQuestionTest() {
        boolean status = questionPersistence.deleteQuestion(1L);
        assertTrue(status);

    }
}