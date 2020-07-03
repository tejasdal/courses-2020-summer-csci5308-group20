package CSCI5308.GroupFormationTool.SurveyManagement;

import java.util.Map;

public interface ISurveyService {
    Map<String, Object> getAllSurveyQuestions(long courseID);

    Map<String, Object> addQuestionPage(long courseId, long surveyId);

    boolean addQuestionToSurvey(long surveyId, long questionId);

    boolean deleteQuestionFromSurvey(Long surveyId, Long questionId);
}
