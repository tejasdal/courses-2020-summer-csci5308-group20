package CSCI5308.GroupFormationTool.survey;

import java.sql.Date;
import java.util.List;

import CSCI5308.GroupFormationTool.Question.Question;
import CSCI5308.GroupFormationTool.Question.QuestionOption;
import CSCI5308.GroupFormationTool.survey.matchcriteria.IMatchCriteria;

public class SurveyQuestion extends Question {
	private Long surveyId;
	private IMatchCriteria matchCriteria;

	public SurveyQuestion(Long surveyId, IMatchCriteria matchCriteria, Long id, 
			String title, String description, Long userId, int questionType, 
			Date createdAt, List<QuestionOption> questionOptions) {
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
