package CSCI5308.GroupFormationTool.SurveyManagementTest;

import CSCI5308.GroupFormationTool.Question.Question;
import CSCI5308.GroupFormationTool.SurveyManagement.IQuestionCriteriaList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuestionCriteriaListTest {
    IQuestionCriteriaList questionCriteriaList;

    @BeforeEach
    public void initiateObjects() {
        questionCriteriaList = SurveyManagementAbstractFactoryTest.instance().makeQuestionCriteriaList();
    }

    @Test
    public void constructorTestWithArguments() {
        questionCriteriaList = SurveyManagementAbstractFactoryTest.instance().makeQuestionCriteriaListUsingList(new ArrayList<Question>());
        assertTrue(questionCriteriaList != null);
    }

    @Test
    public void getSurveyId() {
        questionCriteriaList.setSurveyId(1L);
        assertEquals(questionCriteriaList.getSurveyId(), 1L);
    }

    @Test
    public void setSurveyId() {
        questionCriteriaList.setSurveyId(1L);
        assertEquals(questionCriteriaList.getSurveyId(), 1L);
    }

    @Test
    public void getMembersPerGroup() {
        questionCriteriaList.setMembersPerGroup(1);
        assertEquals(questionCriteriaList.getMembersPerGroup(), 1L);
    }

    @Test
    public void setMembersPerGroup() {
        questionCriteriaList.setMembersPerGroup(1);
        assertEquals(questionCriteriaList.getMembersPerGroup(), 1L);
    }

    @Test
    public void getList() {
        questionCriteriaList.setList(new ArrayList<>());
        assertEquals(questionCriteriaList.getList(), new ArrayList<>());
    }

    @Test
    public void setList() {
        questionCriteriaList.setList(new ArrayList<>());
        assertEquals(questionCriteriaList.getList(), new ArrayList<>());
    }
}
