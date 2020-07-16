package CSCI5308.GroupFormationTool.SurveyManagement;

import java.util.List;
import java.util.Map;

public interface ISurvey {
    List<SurveyQuestion> getQuestions();

    void setQuestions(List<SurveyQuestion> questions);

    public Map<Long, Map<String, List<UserAnswer>>> getUserAnswers();

    public void setUserAnswers(Map<Long, Map<String, List<UserAnswer>>> userAnswers);
}
