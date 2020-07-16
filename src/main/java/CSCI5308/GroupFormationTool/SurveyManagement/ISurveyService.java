package CSCI5308.GroupFormationTool.SurveyManagement;

import CSCI5308.GroupFormationTool.AccessControl.User;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ISurveyService {
    Map<String, Object> getAllSurveyQuestions(long courseID, ISurveyPersistence surveyPersistence);

    Map<String, Object> addQuestionPage(long courseId, long surveyId, ISurveyPersistence surveyPersistence);

    void addQuestionToSurvey(long surveyId, long questionId, ISurveyPersistence surveyPersistence);

    void deleteQuestionFromSurvey(Long surveyId, Long questionId, ISurveyPersistence surveyPersistence);

    boolean unpublishSurvey(long surveyId, ISurveyPersistence surveyPersistence);

    boolean publishSurvey(long surveyId, ISurveyPersistence surveyPersistence);

    boolean isSurveyPublished(Long surveyId, ISurveyPersistence surveyPersistence);

    Map<String, Object> displaySurveyQuestionsToStudents(Long courseId, ISurveyPersistence surveyPersistence);

    boolean submitAnswers(String bannerId, Long surveyId, Survey survey, ISurveyPersistence surveyPersistence);

    List<List<User>> createGroups(QuestionCriteriaList questionsList, Long surveyId, int maxUsersPerGroup, ISurveyPersistence persistence) throws IOException;
    
    boolean validateQuestionCriteriaList(QuestionCriteriaList questionsList) throws Exception;
}
