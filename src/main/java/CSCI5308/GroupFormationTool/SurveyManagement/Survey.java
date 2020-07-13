package CSCI5308.GroupFormationTool.SurveyManagement;

import CSCI5308.GroupFormationTool.Question.Question;

import java.util.ArrayList;
import java.util.List;

public class Survey {

    private List<Question> questions = new ArrayList<>();

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
