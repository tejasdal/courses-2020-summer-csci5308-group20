package CSCI5308.GroupFormationTool.survey.matchcriteria.standarddeviation;

import CSCI5308.GroupFormationTool.SurveyManagement.UserAnswer;
import CSCI5308.GroupFormationTool.survey.matchcriteria.IMatchCriteria;

import java.util.List;

class XLesserThanY implements IMatchCriteria {
	private int x, y;

	public XLesserThanY(int x, int y) {
		this.x = x;
		this.y = y;
	}

	int getX() {
		return x;
	}
	
	int getY() {
		return y;
	}
	
	@Override
	public double calculateMatchScore(List<List<UserAnswer>> userAnswers) {
		int count = 0;

		for (List<UserAnswer> userAnswer : userAnswers) {
			Integer answerIndex = userAnswer.get(0).getAnswerIndex();
			if (answerIndex < y) {
				count++;
			}
		}

		if (count == x) {
			return 1d;
		} else if (count < x) {
			int diff = count - x;
			return diff / (double) x;
		} else {
			int diff = x - count;
			double score = diff / (double) x;
			return score / 2;
		}
	}
}
