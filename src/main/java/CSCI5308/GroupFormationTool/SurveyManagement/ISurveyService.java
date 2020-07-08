package CSCI5308.GroupFormationTool.SurveyManagement;

import java.util.Map;

public interface ISurveyService {
    Map<String, Object> getAllSurveyQuestions(long courseID);

    Map<String, Object> addQuestionPage(long courseId, long surveyId);

    void addQuestionToSurvey(long surveyId, long questionId);

    void deleteQuestionFromSurvey(Long surveyId, Long questionId);

    boolean unpublishSurvey(long surveyId);

    boolean publishSurvey(long surveyId);

    boolean isSurveyPublished(Long surveyId);

    Map<String, Object> displaySurveyQuestionsToStudents(Long courseId, ISurveyPersistence surveyPersistence);
}
