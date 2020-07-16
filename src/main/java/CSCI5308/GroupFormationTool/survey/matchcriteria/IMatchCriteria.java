package CSCI5308.GroupFormationTool.survey.matchcriteria;

import java.util.List;

import CSCI5308.GroupFormationTool.survey.UserAnswer;

public interface IMatchCriteria {

	public double calculateMatchScore(List<List<UserAnswer>> userAnswers);
}
