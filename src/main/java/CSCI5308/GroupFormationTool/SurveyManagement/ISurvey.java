package CSCI5308.GroupFormationTool.SurveyManagement;

import java.util.List;

import CSCI5308.GroupFormationTool.Question.Question;

public interface ISurvey {
    List<Question> getQuestions();

    void setQuestions(List<Question> questions);

}
