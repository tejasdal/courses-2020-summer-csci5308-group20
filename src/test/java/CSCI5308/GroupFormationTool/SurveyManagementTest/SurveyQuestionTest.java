package CSCI5308.GroupFormationTool.SurveyManagementTest;

import CSCI5308.GroupFormationTool.SurveyManagement.SurveyQuestion;
import CSCI5308.GroupFormationTool.SurveyManagement.matchcriteria.IMatchCriteria;
import CSCI5308.GroupFormationTool.SystemConfig;

import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SurveyQuestionTest {

	private static final Date CURRENT_DATE = new Date(System.currentTimeMillis());

	@Test
	public void constructorTest() {
		final IMatchCriteria similarityCriteria = SystemConfig.instance().getMatchCriteriaFactory().getSimilarityCriteria();
		final Long surveyId = 1L;
		final SurveyQuestion surveyQuestion = new SurveyQuestion(surveyId, similarityCriteria, 1L, "Test Question", "Testing", 1L,
				1, CURRENT_DATE, new ArrayList<>());
		assertEquals(surveyId, surveyQuestion.getSurveyId());
		assertEquals(similarityCriteria, surveyQuestion.getMatchCriteria());
	}

}
