package CSCI5308.GroupFormationTool.SurveyManagement;

import java.util.List;
import java.util.Map;

import CSCI5308.GroupFormationTool.Question.IQuestion;

public class Survey implements ISurvey {

    private List<IQuestion> questions;
    private Map<Long, Map<String, List<UserAnswer>>> userAnswers;

    @Override
    public List<IQuestion> getQuestions() {
		return questions;
	}

    @Override
    public void setQuestions(List<IQuestion> questions) {
        this.questions = questions;
    }

    @Override
    public Map<Long, Map<String, List<UserAnswer>>> getUserAnswers() {
        return userAnswers;
    }

    @Override
    public void setUserAnswers(Map<Long, Map<String, List<UserAnswer>>> userAnswers) {
        this.userAnswers = userAnswers;
    }
}