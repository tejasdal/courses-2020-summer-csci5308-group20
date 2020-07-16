package CSCI5308.GroupFormationTool.SurveyManagement;

import CSCI5308.GroupFormationTool.Question.Question;

import java.util.ArrayList;
import java.util.List;

public class Survey implements ISurvey {

    private List<Question> questions = new ArrayList<>();

    @Override
    public List<Question> getQuestions() {
        return questions;
    }

    @Override
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}