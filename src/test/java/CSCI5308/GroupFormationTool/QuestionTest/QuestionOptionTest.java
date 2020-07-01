package CSCI5308.GroupFormationTool.QuestionTest;

import CSCI5308.GroupFormationTool.Question.QuestionOption;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuestionOptionTest {

    @Test
    void ConstructorTest()
    {
        QuestionOption noArgsQuestionOption = new QuestionOption();
        Assert.notNull(noArgsQuestionOption, "Test case for no-args QuestionOption Constructor is failed!");

        QuestionOption allArgsQuestionOption = this.getAllArgsQuestionOption();
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
        QuestionOption questionOption = this.getAllArgsQuestionOption();
        Assert.isTrue("Test Option".equals(questionOption.getOption()), "Test case for Get Option is failed.");
    }

    @Test
    void getQuestionId()
    {
        QuestionOption questionOption = this.getAllArgsQuestionOption();
        Assert.isTrue( 5L == questionOption.getQuestionId(), "Test case for Get QuestionID is failed.");
    }

    @Test
    void getValue()
    {
        QuestionOption questionOption = this.getAllArgsQuestionOption();
        Assert.isTrue( 1 == questionOption.getValue(), "Test case for Get Value is failed.");
    }

    @Test
    void getId()
    {
        QuestionOption questionOption = this.getAllArgsQuestionOption();
        Assert.isTrue(1L == questionOption.getId(), "Test case for Get ID is failed.");
    }

    @Test
    void setId()
    {
        QuestionOption questionOption = new QuestionOption();
        questionOption.setId(1L);
        Assert.isTrue(1L == questionOption.getId(), "Test case for Set ID is failed.");
    }

    @Test
    void setQuestionId()
    {
        QuestionOption questionOption = new QuestionOption();
        questionOption.setQuestionId(5L);
        Assert.isTrue(5L == questionOption.getQuestionId(), "Test case for Set QuestionID is failed.");
    }

    @Test
    void setOption()
    {
        QuestionOption questionOption = new QuestionOption();
        questionOption.setOption("Test Option");
        Assert.isTrue("Test Option".equals(questionOption.getOption()), "Test case for Set Option is failed.");
    }

    @Test
    void setValue()
    {
        QuestionOption questionOption = new QuestionOption();
        questionOption.setValue(1);
        Assert.isTrue(1 == questionOption.getValue(), "Test case for Set Value is failed.");
    }
}