package CSCI5308.GroupFormationTool.QuestionTest;

import CSCI5308.GroupFormationTool.Question.Question;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.sql.Date;
import java.util.ArrayList;

@SpringBootTest
public class QuestionTest {

    public static final Date CURRENT_DATE = new Date(System.currentTimeMillis());

    @Test
    public void ConstructorTest()
    {
        Question noArgsQuestion = new Question();
        Assert.notNull(noArgsQuestion, "Test case for no-args Question Constructor failed!");

        Question allArgsQuestion = this.getAllArgsQuestion();
        Assert.notNull(allArgsQuestion, "Test case for all-args Question Constructor failed!");
        Assert.isTrue(1L == allArgsQuestion.getId(), "Test case for all-args Question Constructor failed!");
    }

    private Question getAllArgsQuestion(){
        return new Question(1L, "Test Question", "Testing",
                1L, 1, CURRENT_DATE, new ArrayList<>());
    }

    @Test
    void getId()
    {
        Question question = this.getAllArgsQuestion();
        Assert.isTrue(1L == question.getId(), "Test Case for GetID is failed.");
    }

    @Test
    void getTitle()
    {
        Question question = this.getAllArgsQuestion();
        Assert.isTrue("Test Question".equals(question.getTitle()), "Test Case for Get Title is failed.");
    }

    @Test
    void getDescription()
    {
        Question question = this.getAllArgsQuestion();
        Assert.isTrue("Testing".equals(question.getDescription()), "Test Case for Get Description is failed.");
    }

    @Test
    void getUserId()
    {
        Question question = this.getAllArgsQuestion();
        Assert.isTrue(1L == question.getUserId(), "Test Case for Get UserID is failed.");
    }

    @Test
    void getQuestionType()
    {
        Question question = this.getAllArgsQuestion();
        Assert.isTrue(1 == question.getQuestionType(), "Test Case for Get Question Type is failed.");
    }

    @Test
    void getQuestionOptions()
    {
        Question question = this.getAllArgsQuestion();
        Assert.notNull(question.getQuestionOptions(), "Test Case for Get Question Options is failed.");
    }

    @Test
    void getCreatedAt()
    {
        Question question = this.getAllArgsQuestion();
        Assert.isTrue(CURRENT_DATE.getTime() == question.getCreatedAt().getTime(), "Test Case for Get Created At is failed.");
    }

    @Test
    void setId()
    {
        Question question = new Question();
        question.setUserId(1L);
        Assert.isTrue(1L == question.getUserId(), "Test Case for Set UserID is failed.");
    }

    @Test
    void setTitle()
    {
        Question question = new Question();
        question.setTitle("Test Question");
        Assert.isTrue("Test Question".equals(question.getTitle()), "Test Case for Set Title is failed.");
    }

    @Test
    void setDescription()
    {
        Question question = new Question();
        question.setDescription("Testing");
        Assert.isTrue("Testing".equals(question.getDescription()), "Test Case for Set Description is failed.");
    }

    @Test
    void setUserId()
    {
        Question question = new Question();
        question.setUserId(1L);
        Assert.isTrue(1L == question.getUserId(), "Test Case for Set UserID is failed.");
    }

    @Test
    void setQuestionType()
    {
        Question question = new Question();
        question.setQuestionType(1);
        Assert.isTrue(1 == question.getQuestionType(), "Test Case for Set Question Type is failed.");
    }

    @Test
    void setQuestionOptions()
    {
        Question question = new Question();
        question.setQuestionOptions(new ArrayList<>());
        Assert.notNull(question.getQuestionOptions(), "Test Case for Set Question Options is failed.");
    }

    @Test
    void setCreatedAt()
    {
        Question question = new Question();
        question.setCreatedAt(CURRENT_DATE);
        Assert.isTrue(CURRENT_DATE.getTime() == question.getCreatedAt().getTime(), "Test Case for Set Created At is failed.");
    }

}
