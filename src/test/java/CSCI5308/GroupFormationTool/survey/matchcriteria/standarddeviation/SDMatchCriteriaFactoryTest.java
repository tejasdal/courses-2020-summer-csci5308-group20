package CSCI5308.GroupFormationTool.survey.matchcriteria.standarddeviation;

import CSCI5308.GroupFormationTool.SurveyManagement.UserAnswer;
import CSCI5308.GroupFormationTool.survey.matchcriteria.IMatchCriteria;
import CSCI5308.GroupFormationTool.survey.matchcriteria.IMatchCriteriaFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SDMatchCriteriaFactoryTest {
	private static final IMatchCriteriaFactory FACTORY = new SDMatchCriteriaFactory();

	@Test
	public void getSimilarityCriteriaTest() {
		final IMatchCriteria similarCriteria = new Similar();
		final int[] values = { 1, 2, 3, 4, 5 };
		final List<List<UserAnswer>> userAnswers = getUserAnswers(values);
		final double score = similarCriteria.calculateMatchScore(userAnswers);

		assertEquals(score, FACTORY.getSimilarityCriteria().calculateMatchScore(userAnswers));
	}

	@Test
	public void getDissimilarityCriteriaTest() {
		final IMatchCriteria dissimilarCriteria = new Dissimilar();
		final int[] values = { 1, 2, 3, 4, 5 };
		final List<List<UserAnswer>> userAnswers = getUserAnswers(values);
		final double score = dissimilarCriteria.calculateMatchScore(userAnswers);

		assertEquals(score, FACTORY.getDissimilarityCriteria().calculateMatchScore(userAnswers));
	}

	@Test
	public void getXGreaterThanYCriteriaTest() {
		final int x = 4, y = 5;
		final IMatchCriteria xGreaterThanY = new XGreaterThanY(x, y);
		final int[] values = { 1, 2, 3, 4, 5 };
		final List<List<UserAnswer>> userAnswers = getUserAnswers(values);
		final double score = xGreaterThanY.calculateMatchScore(userAnswers);

		assertEquals(score, FACTORY.getXGreaterThanYCriteria(x, y).calculateMatchScore(userAnswers));
	}

	@Test
	public void getXLesserThanYCriteriaTest() {
		final int x = 4, y = 5;
		final IMatchCriteria xLesserThanY = new XLesserThanY(x, y);
		final int[] values = { 1, 2, 3, 4, 5 };
		final List<List<UserAnswer>> userAnswers = getUserAnswers(values);
		final double score = xLesserThanY.calculateMatchScore(userAnswers);

		assertEquals(score, FACTORY.getXLesserThanYCriteria(x, y).calculateMatchScore(userAnswers));
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
