package CSCI5308.GroupFormationTool.survey;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.survey.matchcriteria.IMatchCriteria;

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
