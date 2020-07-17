package CSCI5308.GroupFormationTool.SurveyManagement.matchcriteria.standarddeviation;

import CSCI5308.GroupFormationTool.SurveyManagement.UserAnswer;
import CSCI5308.GroupFormationTool.SurveyManagement.matchcriteria.IMatchCriteriaFactory;
import CSCI5308.GroupFormationTool.SurveyManagement.matchcriteria.standarddeviation.SDMatchCriteriaFactory;
import CSCI5308.GroupFormationTool.SurveyManagement.matchcriteria.standarddeviation.XLesserThanY;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class XLesserThanYTest {
	
	@Test
	public void constructorTest() {
		final int x = 1, y = 4;
		final XLesserThanY object = new XLesserThanY(x, y);
		assertEquals(x, object.getX());
		assertEquals(y, object.getY());
	}
	
	@Test
	public void calculateMatchScoreTest() {
		final int[] values = {1, 2, 3, 4, 5};
		final IMatchCriteriaFactory factory = new SDMatchCriteriaFactory();
		assertEquals(-1.0d, factory.getXLesserThanYCriteria(2, 1).calculateMatchScore(getUserAnswers(values)));
		assertEquals(1.0d, factory.getXLesserThanYCriteria(1, 2).calculateMatchScore(getUserAnswers(values)));
		assertEquals(-0.5d, factory.getXLesserThanYCriteria(2, 5).calculateMatchScore(getUserAnswers(values)));
	}
	
	private static List<List<UserAnswer>> getUserAnswers(int[] values) {
		List<List<UserAnswer>> list = new ArrayList<>();
		for (int value : values) {
			List<UserAnswer> userAnswer = new ArrayList<>();
			list.add(userAnswer);
			userAnswer.add(new UserAnswer("hello", value));
		}
		return list;
	}
}
