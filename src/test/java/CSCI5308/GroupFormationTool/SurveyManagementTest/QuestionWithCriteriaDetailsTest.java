package CSCI5308.GroupFormationTool.SurveyManagementTest;

import CSCI5308.GroupFormationTool.Question.Question;
import CSCI5308.GroupFormationTool.SurveyManagement.QuestionWithCriteriaDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionWithCriteriaDetailsTest extends Question {


    private QuestionWithCriteriaDetails questionWithCriteriaDetails;

    @BeforeEach
    public void initiateObjects() {
        questionWithCriteriaDetails = SurveyManagementAbstractFactoryTest.instance().makeQuestionWithCriteriaDetails(new Question(), 1, 1);

    }

    @Test
    public void getCriteriaType() {
        questionWithCriteriaDetails.setCriteriaType(1);
        assertEquals(questionWithCriteriaDetails.getCriteriaType(), 1);
    }

    @Test
    public void setCriteriaType() {
        questionWithCriteriaDetails.setCriteriaType(1);
        assertEquals(questionWithCriteriaDetails.getCriteriaType(), 1);
    }

    @Test
    public void getCriteriaValue() {
        questionWithCriteriaDetails.setCriteriaValue(1);
        assertEquals(questionWithCriteriaDetails.getCriteriaValue(), 1);
    }

    @Test
    public void setCriteriaValue() {
        questionWithCriteriaDetails.setCriteriaValue(1);
        assertEquals(questionWithCriteriaDetails.getCriteriaValue(), 1);
    }
}
