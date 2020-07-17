package CSCI5308.GroupFormationTool.SurveyManagement.matchcriteria.standarddeviation;

import CSCI5308.GroupFormationTool.SurveyManagement.UserAnswer;
import CSCI5308.GroupFormationTool.SurveyManagement.matchcriteria.IMatchCriteria;

import java.util.*;

class Dissimilar implements IMatchCriteria {

	@Override
	public double calculateMatchScore(List<List<UserAnswer>> userAnswers) {
		boolean oneDimensional = true;
		for (List<UserAnswer> userAnswer : userAnswers) {
			if (userAnswer.size() > 1) {
				oneDimensional = false;
				break;
			}
		}
		if (oneDimensional) {
			return calculateMatchScore1D(userAnswers);
		}
		return calculateMatchScoreND(userAnswers);
	}

	private double calculateMatchScoreND(List<List<UserAnswer>> userAnswers) {
		final Set<Integer> indices = new TreeSet<>();
		final Map<Integer, Integer> optionCount = new HashMap<>();
		userAnswers.forEach(userOptions -> {
			userOptions.forEach(option -> {
				indices.add(option.getAnswerIndex());
				int count = optionCount.getOrDefault(option.getAnswerIndex(), 0);
				optionCount.put(option.getAnswerIndex(), count+1);
			});
		});
		final Map<Integer, Integer> indexRankMap = new HashMap<>();
		int rank = 0;
		for (Integer index : indices) {
			indexRankMap.put(index, ++rank);
		}
		double score = 0;
		for (Integer optionIndex : optionCount.keySet()) {
			int[] values = new int[userAnswers.size()];
			for (int i = 0; i < userAnswers.size(); i++) {
				if (i < optionCount.get(optionIndex)) {
					values[i] = indexRankMap.get(optionIndex);
				} else {
					values[i] = 0;
				}
			}
			double maxDeviation = StandardDeviation.getMaxDeviation(values);
			double deviation = StandardDeviation.getDeviation(values);
			if (maxDeviation != 0) {
				score += deviation / maxDeviation;
			}
		}
		return score/optionCount.size();
	}

	private double calculateMatchScore1D(List<List<UserAnswer>> userAnswers) {
		int[] values = new int[userAnswers.size()];
		for (int i = 0; i < userAnswers.size(); i++) {
			values[i] = 0;
			for (UserAnswer option : userAnswers.get(i)) {
				values[i] += option.getAnswerIndex();
			}
		}
		double maxDeviation = StandardDeviation.getMaxDeviation(values);
		double deviation = StandardDeviation.getDeviation(values);
		if (maxDeviation == 0) {
			return 0;
		}
		return deviation / maxDeviation;
	}

}
