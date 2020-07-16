package CSCI5308.GroupFormationTool.survey.matchcriteria.standarddeviation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import CSCI5308.GroupFormationTool.survey.UserAnswer;
import CSCI5308.GroupFormationTool.survey.matchcriteria.IMatchCriteria;

public class SimilarTest {

	@Test
	public void calculateMatchScoreTest() {
		final IMatchCriteria similarCriteria = new SDMatchCriteriaFactory().getSimilarityCriteria();
		final int[] dissimilarValues = { 1, 3, 3, 4, 5 };
		final double dissimilarityScore = 0.5416666666666667d;
		final int[] similarValues = { 1, 1, 1, 1, 1 };
		final double similarityScore = 1d;
		assertEquals(dissimilarityScore, similarCriteria.calculateMatchScore(getUserAnswers(dissimilarValues)));
		assertEquals(similarityScore, similarCriteria.calculateMatchScore(getUserAnswers(similarValues)));

		final int[][] twoDDissimilarValues = { { 2 }, { 1, 2 } };
		final int[][] twoDSsimilarValues = { { 2, 1 }, { 1, 2 } };
		assertEquals(0.5d, similarCriteria.calculateMatchScore(getUserAnswers(twoDDissimilarValues)));
		assertEquals(1d, similarCriteria.calculateMatchScore(getUserAnswers(twoDSsimilarValues)));
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

	private static List<List<UserAnswer>> getUserAnswers(int[][] values) {
		List<List<UserAnswer>> list = new ArrayList<>();
		for (int[] valueArray : values) {
			List<UserAnswer> userAnswer = new ArrayList<>();
			list.add(userAnswer);
			for (int value : valueArray) {
				userAnswer.add(new UserAnswer("hello", value));
			}
		}
		return list;
	}

}
