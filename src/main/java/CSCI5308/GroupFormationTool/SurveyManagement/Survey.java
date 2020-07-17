package CSCI5308.GroupFormationTool.SurveyManagement;

import java.util.List;

import CSCI5308.GroupFormationTool.Question.Question;

public class Survey implements ISurvey {

    private List<Question> questions;

    @Override
    public List<Question> getQuestions() {
		return questions;
	}

    @Override
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

}