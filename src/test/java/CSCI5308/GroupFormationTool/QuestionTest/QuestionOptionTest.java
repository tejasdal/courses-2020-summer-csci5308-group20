package CSCI5308.GroupFormationTool.QuestionTest;

import CSCI5308.GroupFormationTool.Question.IQuestionOption;
import CSCI5308.GroupFormationTool.Question.QuestionOption;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuestionOptionTest {

    private static IQuestionOption emptyQuestionOption;
    private static IQuestionOption questionOption;

    @BeforeAll
    public static void setUp(){
        QuestionTestAbstractFactory questionTestAbstractFactory = new QuestionTestConcreteFactory();
        emptyQuestionOption = questionTestAbstractFactory.makeEmptyQuestionOption();
        questionOption = questionTestAbstractFactory.makeQuestionOption();
        questionOption.setQuestionId(5L);
    }

    @Test
    void ConstructorTest()
    {
        IQuestionOption noArgsQuestionOption = emptyQuestionOption;
        Assert.notNull(noArgsQuestionOption, "Test case for no-args QuestionOption Constructor is failed!");

        IQuestionOption allArgsQuestionOption = questionOption;
        Assert.notNull(allArgsQuestionOption, "Test case for all-args QuestionOption Constructor is failed!");
        Assert.isTrue(1L == allArgsQuestionOption.getId(), "Test case for all-args QuestionOption Constructor is failed!");
    }

    private QuestionOption getAllArgsQuestionOption()
    {
        QuestionOption questionOption = new QuestionOption(1L, "Test Option", 1);
        questionOption.setQuestionId(5L);
        return  questionOption;
    }

    @Test
    void getOption()
    {
        Assert.isTrue("Test Option".equals(questionOption.getOption()), "Test case for Get Option is failed.");
    }

    @Test
    void getQuestionId()
    {
        Assert.isTrue( 5L == questionOption.getQuestionId(), "Test case for Get QuestionID is failed.");
    }

    @Test
    void getValue()
    {
        Assert.isTrue( 1 == questionOption.getValue(), "Test case for Get Value is failed.");
    }

    @Test
    void getId()
    {
        Assert.isTrue(1L == questionOption.getId(), "Test case for Get ID is failed.");
    }

    @Test
    void setId()
    {
        IQuestionOption questionOption = emptyQuestionOption;
        questionOption.setId(1L);
        Assert.isTrue(1L == questionOption.getId(), "Test case for Set ID is failed.");
    }

    @Test
    void setQuestionId()
    {
        IQuestionOption questionOption = emptyQuestionOption;
        questionOption.setQuestionId(5L);
        Assert.isTrue(5L == questionOption.getQuestionId(), "Test case for Set QuestionID is failed.");
    }

    @Test
    void setOption()
    {
        IQuestionOption questionOption = emptyQuestionOption;
        questionOption.setOption("Test Option");
        Assert.isTrue("Test Option".equals(questionOption.getOption()), "Test case for Set Option is failed.");
    }

    @Test
    void setValue()
    {
        IQuestionOption questionOption = emptyQuestionOption;
        questionOption.setValue(1);
        Assert.isTrue(1 == questionOption.getValue(), "Test case for Set Value is failed.");
    }
}