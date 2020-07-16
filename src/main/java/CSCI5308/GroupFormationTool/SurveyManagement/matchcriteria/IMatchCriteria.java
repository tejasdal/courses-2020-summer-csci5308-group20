package CSCI5308.GroupFormationTool.SurveyManagement.matchcriteria;

import CSCI5308.GroupFormationTool.SurveyManagement.UserAnswer;

import java.util.List;

public interface IMatchCriteria {

	public double calculateMatchScore(List<List<UserAnswer>> userAnswers);
}
