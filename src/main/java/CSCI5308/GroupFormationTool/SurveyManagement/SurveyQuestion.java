package CSCI5308.GroupFormationTool.SurveyManagement;

import java.sql.Date;
import java.util.List;

import CSCI5308.GroupFormationTool.Question.IQuestionOption;
import CSCI5308.GroupFormationTool.Question.Question;
import CSCI5308.GroupFormationTool.SurveyManagement.matchcriteria.IMatchCriteria;

public class SurveyQuestion extends Question {
	private Long surveyId;
	private IMatchCriteria matchCriteria;

	public SurveyQuestion(Long surveyId, IMatchCriteria matchCriteria, Long id,
						  String title, String description, Long userId, int questionType,
						  Date createdAt, List<IQuestionOption> questionOptions) {
		super(id, title, description, userId, questionType, createdAt, questionOptions);
		this.surveyId = surveyId;
		this.matchCriteria = matchCriteria;
	}

	public Long getSurveyId() {
		return surveyId;
	}

	public IMatchCriteria getMatchCriteria() {
		return matchCriteria;
	}
}
