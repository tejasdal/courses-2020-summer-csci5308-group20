package CSCI5308.GroupFormationTool.SurveyManagement;

import CSCI5308.GroupFormationTool.Question.Question;

import java.util.List;

public interface ISurvey {
    List<Question> getQuestions();

    void setQuestions(List<Question> questions);
}
