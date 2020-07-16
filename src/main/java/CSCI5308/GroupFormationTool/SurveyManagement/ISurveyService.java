package CSCI5308.GroupFormationTool.SurveyManagement;

import java.sql.SQLException;
import java.util.Map;

public interface ISurveyService {
    Map<String, Object> getAllSurveyQuestions(long courseID, ISurveyPersistence surveyPersistence) throws SQLException;

    Map<String, Object> addQuestionPage(long courseId, long surveyId, ISurveyPersistence surveyPersistence);

    void addQuestionToSurvey(long surveyId, long questionId, ISurveyPersistence surveyPersistence);

    void deleteQuestionFromSurvey(Long surveyId, Long questionId, ISurveyPersistence surveyPersistence);

    boolean unpublishSurvey(long surveyId, ISurveyPersistence surveyPersistence);

    boolean publishSurvey(long surveyId, ISurveyPersistence surveyPersistence);

    boolean isSurveyPublished(Long surveyId, ISurveyPersistence surveyPersistence);

    Map<String, Object> displaySurveyQuestionsToStudents(Long courseId, ISurveyPersistence surveyPersistence) throws SQLException;

    boolean submitAnswers(String bannerId, Long surveyId, Survey survey, ISurveyPersistence surveyPersistence);
}
