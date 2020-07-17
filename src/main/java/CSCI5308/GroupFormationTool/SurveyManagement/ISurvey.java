package CSCI5308.GroupFormationTool.SurveyManagement;

import java.util.List;
import java.util.Map;

import CSCI5308.GroupFormationTool.Question.IQuestion;

public interface ISurvey {
    List<IQuestion> getQuestions();

    void setQuestions(List<IQuestion> questions);

    public Map<Long, Map<String, List<UserAnswer>>> getUserAnswers();

    public void setUserAnswers(Map<Long, Map<String, List<UserAnswer>>> userAnswers);
}
