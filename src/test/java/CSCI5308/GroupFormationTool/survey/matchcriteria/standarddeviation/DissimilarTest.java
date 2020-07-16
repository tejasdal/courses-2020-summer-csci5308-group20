package CSCI5308.GroupFormationTool.survey.matchcriteria.standarddeviation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import CSCI5308.GroupFormationTool.survey.UserAnswer;
import CSCI5308.GroupFormationTool.survey.matchcriteria.IMatchCriteria;

public class DissimilarTest {

	@Test
	public void calculateMatchScoreTest() {
		final IMatchCriteria dissimilarCriteria = new SDMatchCriteriaFactory().getDissimilarityCriteria();
		final int[] dissimilarValues = {1, 1, 5, 5, 5};
		final double dissimilarityScore = 1d;
		final int[] similarValues = {1, 3, 3, 4, 5};
		final double similarityScore = 0.4583333333333333d;
									
		assertEquals(dissimilarityScore, dissimilarCriteria.calculateMatchScore(getUserAnswers(dissimilarValues)));
		assertEquals(similarityScore, dissimilarCriteria.calculateMatchScore(getUserAnswers(similarValues)));

		final int[][] twoDDissimilarValues = { { 2 }, { 1, 2 } };
		final int[][] twoDSsimilarValues = { { 2, 1 }, { 3, 4 } };
		assertEquals(0.5d, dissimilarCriteria.calculateMatchScore(getUserAnswers(twoDDissimilarValues)));
		assertEquals(1d, dissimilarCriteria.calculateMatchScore(getUserAnswers(twoDSsimilarValues)));
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
